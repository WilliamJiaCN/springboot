package com.architect.utils;

import com.architect.algorithm.ModuloShardingAlgorithm;
import com.google.common.base.Optional;
import io.shardingsphere.core.api.ShardingDataSourceFactory;
import io.shardingsphere.core.api.config.ShardingRuleConfiguration;
import io.shardingsphere.core.api.config.TableRuleConfiguration;
import io.shardingsphere.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.transaction.api.config.NestedBestEffortsDeliveryJobConfiguration;
import io.shardingsphere.transaction.api.config.SoftTransactionConfiguration;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author wenxiong.jia
 * @since 2018/8/29
 */
public class TransactionMain {
    private static boolean useNestedJob = true;

    public static DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("t_order");
        orderTableRuleConfig.setActualDataNodes("ds_${0..1}.t_order_${0..1}");
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        TableRuleConfiguration orderItemTableRuleConfig = new TableRuleConfiguration();
        orderItemTableRuleConfig.setLogicTable("t_order_item");
        orderItemTableRuleConfig.setActualDataNodes("ds_${0..1}.t_order_item_${0..1}");
        shardingRuleConfig.getTableRuleConfigs().add(orderItemTableRuleConfig);

        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");

        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new ModuloShardingAlgorithm()));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", new ModuloShardingAlgorithm()));
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new HashMap<String, Object>(), new Properties());
    }

    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>(2, 1);
        result.put("ds_0", createDataSource("ds_0"));
        result.put("ds_1", createDataSource("ds_1"));
        return result;
    }

    private static DataSource createDataSource(final String dataSourceName) {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        result.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
        result.setUsername("root");
        result.setPassword("root");
        return result;
    }

    private static DataSource createTransactionLogDataSource() {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        result.setUrl("jdbc:mysql://localhost:3306/trans_log");
        result.setUsername("root");
        result.setPassword("root");
        return result;
    }

    public static SoftTransactionConfiguration getSoftTransactionConfiguration(final DataSource dataSource) {
        SoftTransactionConfiguration result = new SoftTransactionConfiguration(dataSource);
        if (useNestedJob) {
            result.setBestEffortsDeliveryJobConfiguration(Optional.of(new NestedBestEffortsDeliveryJobConfiguration()));
        }
//        result.setTransactionLogDataSource(createTransactionLogDataSource());
        return result;
    }
}
