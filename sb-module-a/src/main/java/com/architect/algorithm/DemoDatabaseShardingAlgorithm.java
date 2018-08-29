//package com.architect.algorithm;
//
//import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
//import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
//
//import java.util.Collection;
//
///**
// * @author wenxiong.jia
// * @since 2018/8/25
// */
//public class DemoDatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
//    @Override
//    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
//        for (String each : collection) {
//            if (each.endsWith(Long.parseLong(preciseShardingValue.getValue().toString()) % 2 + "")) {
//                return each;
//            }
//        }
//        throw new IllegalArgumentException();
//    }
//}
