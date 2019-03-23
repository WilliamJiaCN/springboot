package com.architect.test;

import com.architect.bean.InitSequenceBean;
import com.architect.startup.SpringBootStart;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author jiawx
 * @date 2019/3/23
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStart.class)
public class InitSequenceBeanTest {

    @Autowired
    private InitSequenceBean initSequenceBean;

    @Test
    public void initSequenceBeanTest() {
        System.out.println("Finish:" + initSequenceBean.toString());
    }
}
