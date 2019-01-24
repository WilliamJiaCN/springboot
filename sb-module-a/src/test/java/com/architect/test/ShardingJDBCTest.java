//package com.architect.test;
//
//import com.architect.dao.OrderItemRepository;
//import com.architect.dao.OrderRepository;
//import com.architect.dao.UserDao;
//import com.architect.entity.Order;
//import com.architect.entity.OrderItem;
//import com.architect.entity.User;
//import com.architect.service.DemoService;
//import com.architect.startup.SpringBootStart;
//import io.shardingsphere.core.api.HintManager;
//import io.shardingsphere.core.hint.HintManagerHolder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.sql.SQLException;
//import java.util.List;
//
///**
// * @author wenxiong.jia
// * @since 2018/8/25
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = SpringBootStart.class)
//public class ShardingJDBCTest {
//
//    @Autowired
//    private UserDao userDao;
//    @Autowired
//    private DemoService demoService;
//    @Autowired
//    private OrderRepository orderRepository;
//    @Autowired
//    private OrderItemRepository orderItemRepository;
//    @Test
//    public void testSharding() {
//        System.out.println("新增。。。");
//        long id = 1;
//        for (int i = 1; i <= 10; i++) {
//            User user = new User();
//            user.setId(id++);
//            user.setPhone("15353714844");
//            user.setAge(20 + i);
//            user.setName("w" + i);
//            user.setAddress("北京昌平");
//            if (i == 3) {
//                HintManagerHolder.clear();
//                HintManager hintManager = HintManager.getInstance();
//                hintManager.addDatabaseShardingValue("t_user", "id", 3L);
//                hintManager.addTableShardingValue("t_user", "id", 3L);
//                System.out.println(id);
//            }
//            userDao.insert(user);
//        }
//        System.out.println("结束。。。");
//    }
//    @Test
//    public void testDemoService() throws SQLException {
//        demoService.demo();
//    }
//    @Test
//    public void testSelect() throws SQLException {
//        Order o = new Order();
//        o.setOrderId(241577493528576000L);
//        o.setUserId(51);
//        Order order = orderRepository.getByOrderId(o);
//        System.out.println(order);
//    }
//    @Test
//    public void testSelectAll() throws SQLException {
//        List<OrderItem> orderItems = orderItemRepository.selectAll();
//        System.out.println(orderItems);
//    }
//    @Test
//    public void testSelectOne() throws SQLException {
//        Order order = new Order();
//        order.setOrderId(241577493528576000L);
//        order.setUserId(51);
//        OrderItem orderItem = orderItemRepository.selectOne(order);
//        System.out.println(orderItem);
//    }
//}
