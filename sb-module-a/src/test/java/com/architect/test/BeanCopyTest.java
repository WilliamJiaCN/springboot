package com.architect.test;

import com.architect.dto.PersonDTO;
import com.architect.entity.Person;
import com.architect.entity.SourceBean;
import com.architect.entity.Student;
import com.architect.entity.TargetBean;
import com.architect.startup.SpringBootStart;
import com.github.dozermapper.core.Mapper;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

/**
 * @author wenxiong.jia
 * @since 2018/01/04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStart.class)
public class BeanCopyTest {

    @Autowired
    private Mapper dozerMapper;

    @Test
    public void test1() {
        Person p = new Person();
        p.setFirstName("jia");
        p.setLastName("wenxiong");
        p.setJobTitles(Lists.newArrayList("abc", "efg", "iii"));
        p.setSalary(1000L);
        Student stu = new Student(1, "william", "man");
        p.setStu(stu);
        List<Student> stus = Collections.singletonList(stu);
        p.setStus(stus);
        PersonDTO dto = dozerMapper.map(p, PersonDTO.class);
        System.out.println(dto);
    }

    @Test
    public void test2() {
        SourceBean sourceBean = new SourceBean();
        sourceBean.setId(123456L);
        sourceBean.setName("jiawenxiong");
        sourceBean.setData("01010001");
        TargetBean targetBean = dozerMapper.map(sourceBean, TargetBean.class);
        System.out.println(targetBean);
    }
}
