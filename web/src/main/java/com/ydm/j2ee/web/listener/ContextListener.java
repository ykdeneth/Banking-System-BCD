package com.ydm.j2ee.web.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import com.ydm.j2ee.core.provider.MailServiceProvider;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MailServiceProvider.getInstance().start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MailServiceProvider.getInstance().shutdown();
    }
}
