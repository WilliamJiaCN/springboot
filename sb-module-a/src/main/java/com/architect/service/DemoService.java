/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.architect.service;

import com.architect.dao.OrderItemRepository;
import com.architect.dao.OrderRepository;
import com.architect.entity.Order;
import com.architect.entity.OrderItem;
import com.architect.utils.TransactionMain;
import io.shardingsphere.transaction.api.SoftTransactionManager;
import io.shardingsphere.transaction.api.config.SoftTransactionConfiguration;
import io.shardingsphere.transaction.bed.BEDSoftTransaction;
import io.shardingsphere.transaction.constants.SoftTransactionType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService {
    
    @Resource
    private OrderRepository orderRepository;
    
    @Resource
    private OrderItemRepository orderItemRepository;
    
    public void demo() throws SQLException {
//        orderRepository.createIfNotExistsTable();
//        orderItemRepository.createIfNotExistsTable();
//        orderRepository.truncateTable();
//        orderItemRepository.truncateTable();
        List<Long> orderIds = new ArrayList<>(10);
        System.out.println("1.Insert--------------");
        // 1. 配置SoftTransactionConfiguration
        DataSource dataSource = TransactionMain.getShardingDataSource();
        SoftTransactionConfiguration transactionConfig = TransactionMain.getSoftTransactionConfiguration(dataSource);
        // 2. 初始化SoftTransactionManager
        SoftTransactionManager transactionManager = new SoftTransactionManager(transactionConfig);
        transactionManager.init();
        // 3. 获取BEDSoftTransaction
        BEDSoftTransaction transaction = (BEDSoftTransaction) transactionManager.getTransaction(SoftTransactionType.BestEffortsDelivery);
        // 4. 开启事务
        transaction.begin(dataSource.getConnection());
        try {
            for (int i = 0; i < 5; i++) {
                Order order = new Order();
                order.setUserId(51);
                order.setStatus("INSERT_TEST");
                orderRepository.insert(order);
                long orderId = order.getOrderId();
                orderIds.add(orderId);

                OrderItem item = new OrderItem();
                item.setOrderId(orderId);
                item.setUserId(51);
                orderItemRepository.insert(item);
            }
        } catch (Exception e) {
            //日志
            throw e;
        }finally {
            transaction.end();
        }
        System.out.println(orderItemRepository.selectAll());
//        System.out.println("2.Delete--------------");
//        for (Long each : orderIds) {
//            orderRepository.delete(each);
//            orderItemRepository.delete(each);
//        }
//        System.out.println(orderItemRepository.selectAll());
//        orderItemRepository.dropTable();
//        orderRepository.dropTable();
    }
}
