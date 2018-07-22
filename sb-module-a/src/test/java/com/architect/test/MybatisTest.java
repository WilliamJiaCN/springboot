package com.architect.test;

import com.architect.entity.User;
import com.architect.mapper.UserMapper;
import com.architect.startup.SpringBootStart;
import junit.framework.TestCase;
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
    private UserMapper userMapper;

    @Test
    public void testInsert() throws Exception {
        User user = new User();
        user.setName("william");
        user.setAge(28);
        user.setAddress("北京昌平");
        user.setPhone("15353714844");
        int num = userMapper.insert(user);
        TestCase.assertEquals(num, 1);
    }

    @Test
    public void testFindById() throws Exception {
        User user = userMapper.queryByPrimaryKey(1L);
        TestCase.assertNotNull(user);
        System.out.println(user.getName());
    }
}
