package com.guli.service_msm.service.impl;

import com.guli.service_msm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author 刘佳俊
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${sender.email}")
    private String sender;


    @Override
    public boolean send(String email, Map<String, Object> param) {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("这是一封测试邮件");
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom(sender);
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        // message.setTo("10*****16@qq.com","12****32*qq.com");
        message.setTo(email);
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
        return true;
    }
}
