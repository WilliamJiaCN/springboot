package com.architect.test;

import com.architect.dto.PersonDTO;
import com.architect.entity.Person;
import com.architect.startup.SpringBootStart;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wenxiong.jia
 * @since 2018/12/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStart.class)
public class EasyMapperTest {

    @Test
    public void testEasyMapper() {
        Person p = new Person();
        p.setFirstName("jia");
        p.setLastName("wenxiong");
        p.setJobTitles(Lists.newArrayList("abc", "efg", "iii"));
        p.setSalary(1000L);
        PersonDTO dto = MapperFactory.getCopyByRefMapper()
                .mapClass(Person.class, PersonDTO.class)
                .registerAndMap(p, PersonDTO.class);
        System.out.println(dto);
    }
}
