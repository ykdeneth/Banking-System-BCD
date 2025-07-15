package com.ydm.j2ee.core.mail;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import com.ydm.j2ee.core.provider.MailServiceProvider;
import com.ydm.j2ee.core.util.Env;

public abstract class Mailable implements  Runnable{
    private MailServiceProvider mailServiceProvider;

    public Mailable(){
        mailServiceProvider = MailServiceProvider.getInstance();
    }

    @Override
    public void run() {
        try {
            Session session = Session.getInstance(mailServiceProvider.getProperties(),
                    mailServiceProvider.getAuthenticator());
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Env.get("app.email")));
            build(message);
            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void build(Message message) throws Exception;
}
