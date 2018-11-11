package ru.kos.ve;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.io.File;

public class EmailUtil {

    public static void sendMail(File attachment, String mail) {
        EmailAttachment emailAttachment = new EmailAttachment();
        emailAttachment.setPath(attachment.getPath());
        emailAttachment.setDisposition(EmailAttachment.ATTACHMENT);
        emailAttachment.setDescription("Picture Attachment");
        emailAttachment.setName("Picture");
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.yandex.ru");
        email.setSslSmtpPort("465");
        email.setAuthenticator(new DefaultAuthenticator("test411", "Test411!"));
        email.setSSLOnConnect(true);
        try {
            email.setFrom("test411@yandex.ru");
        } catch (EmailException e) {
            e.printStackTrace();
        }
        email.setSubject("Java тестовый Email");
        try {
            email.setMsg("Это тестовая отправка скрина");
        } catch (EmailException e) {
            e.printStackTrace();
        }
        try {
            email.addTo(mail);
        } catch (EmailException e) {
            e.printStackTrace();
        }
        try {
            email.attach(emailAttachment);
        } catch (EmailException e) {
            e.printStackTrace();
        }
        try {
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
