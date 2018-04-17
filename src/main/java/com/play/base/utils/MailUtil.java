package com.play.base.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * Created by Administrator on 2018/2/24.
 * 文字邮件、html邮件、带图片带附件等
 * setText中可用StringBuilder
 */
public class MailUtil {

    @Autowired
    private static JavaMailSender javaMailSender;

    /*
    * 单发邮件
    */
    public static void sendMail(String from,String to ,String tittle ,String content) throws GeneralSecurityException {
      /*  Properties props = new Properties();
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable",true);
        props.put("mail.smtp.ssl.socketFactory",sf);*/
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(tittle);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
        System.out.println(">>>>>>>>>>邮件已发送<<<<<<<<<<");
    }
    /*
   * 群发邮件
   */
    public static void sendMail(String from,String[] to ,String tittle ,String content) throws GeneralSecurityException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(tittle);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
        System.out.println(">>>>>>>>>>邮件已发送<<<<<<<<<<");
    }

    /*
* 单发Html邮件
*/
    public static void sendHtmlMail(String from,String to ,String tittle ,String htmlContent) throws GeneralSecurityException, MessagingException {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(tittle);
        mimeMessageHelper.setText(htmlContent,true);
        javaMailSender.send(mimeMailMessage);
        System.out.println(">>>>>>>>>>邮件已发送<<<<<<<<<<");
    }

    /*
  * 群发Html邮件
  */
    public static void sendHtmlMail(String from,String[] to ,String tittle ,String htmlContent) throws GeneralSecurityException, MessagingException {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage,true);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(tittle);
        mimeMessageHelper.setText(htmlContent,true);
        javaMailSender.send(mimeMailMessage);
        System.out.println(">>>>>>>>>>邮件已发送<<<<<<<<<<");
    }

}
