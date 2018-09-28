package com.architect.test;

import com.architect.service.Sender;
import com.architect.startup.SpringBootStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author wenxiong.jia
 * @since 2018/7/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStart.class)
public class SendMailTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void testSend() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        // 发送方邮箱
        helper.setFrom("569838709@qq.com");
        // 接收方邮箱
        helper.setTo("15353714844@163.com");
        helper.setCc("");
        helper.setBcc("");
        // 主题
        helper.setSubject("主题：测试邮件");
        // 内容
        helper.setText("测试发送邮件");
        javaMailSender.send(mimeMessage);

    }
}
