package com.test;

import com.guli.service_msm.config.EmailConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author 刘佳俊
 */
@SpringBootTest(classes = EmailConfig.class)
@RunWith(SpringRunner.class)
public class EmailTest {
    @Autowired
    JavaMailSender javaMailSender;
    /**
     * 普通邮件发送
     */
    @Test
    public void sendSimpleMail() {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("这是一封测试邮件");
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom("3142467441@qq.com");
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        // message.setTo("10*****16@qq.com","12****32*qq.com");
        message.setTo("3033909361@qq.com","3249850281@qq.com");
        // 设置邮件抄送人，可以有多个抄送人
//        message.setCc("3142467441@qq.com");
        // 设置隐秘抄送人，可以有多个
//        message.setBcc("3142467441@qq.com");
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        message.setText("www.7k7k.com");

        // 发送邮件
        javaMailSender.send(message);
    }
}
