package com.kepai.app.config;

import com.alibaba.fastjson.JSON;
import com.kepai.base.utils.KLogger;
import com.kepai.base.utils.ThreadHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

/**
 * @author huang
 * @ProjectName base
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2018/4/4
 * @note 这里写文件的详细功能和改动
 * @note
 */
@Configuration
public class JavaMailConfig {

    /**
     * 邮件发送者
     */
    private final static String MAIL_FROM = "980287353@qq.com";

    /**
     * 邮件接受者
     */
    private final static String MAIL_TO = "980287353@qq.com";

    /**
     * 邮件标题
     */
    private final static String MAIL_TITLE = "SpringBoot模板";


    private static JavaMailSender mailSender;

    /**
     * 邮件发送配置
     *
     * @return
     */
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");
        mailSender.setUsername("980287353@qq.com");
        mailSender.setPassword("abybsluofxwibfhi");

        Properties mailProps = new Properties();
        mailProps.setProperty("mail.smtp.port", "4650");
        mailProps.setProperty("mail.smtp.socketFactory.port", "465");
        mailProps.setProperty("mail.smtp.auth", "true");
        mailProps.setProperty("mail.smtp.starttls.enable", "true");
        mailProps.setProperty("mail.smtp.starttls.required", "true");
        mailProps.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailSender.setJavaMailProperties(mailProps);
        return mailSender;
    }

    /**
     * 发送错误信息给开发者
     */
    public static void sendBugMail(HttpServletRequest request, Exception e) {
        if (mailSender == null) {
            mailSender = SpringContext.getBean(JavaMailSender.class);
        }
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(MAIL_FROM);
        message.setTo(MAIL_TO);
        message.setSubject(MAIL_TITLE);
        String buffer = "请求参数：\r\n" +
                JSON.toJSON(request.getParameterMap()) +
                "\r\n异常信息：\r\n" +
                JSON.toJSONString(e);
        message.setText(buffer);

        ThreadHelper.newThread().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    mailSender.send(message);
                } catch (Exception e1) {
                    KLogger.getLogger().info(e1);
                }
            }
        });
    }


}
