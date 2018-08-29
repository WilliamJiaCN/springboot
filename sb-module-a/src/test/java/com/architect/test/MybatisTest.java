package com.architect.test;

import com.architect.entity.User;
import com.architect.dao.UserDao;
import com.architect.startup.SpringBootStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author wenxiong.jia
 * @since 2018/7/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStart.class)
public class MybatisTest {

    @Autowired
    private UserDao userMapper;

    @Test
    public void testInsert() throws Exception {
        User user = new User();
        user.setId(3L);
        user.setName("william");
        user.setAge(28);
        user.setAddress("北京昌平");
        user.setPhone("15353714844");
        userMapper.insert(user);
        System.out.println("新增用户信息成功");
    }

    @Test
    public void testFindById() throws Exception {
        User userQuery = new User();
        userQuery.setId(1L);
        User user = userMapper.getByPrimaryKey(userQuery);
        System.out.println(user.getName());
    }
}
