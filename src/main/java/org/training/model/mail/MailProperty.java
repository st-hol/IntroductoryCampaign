package org.training.model.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class MailProperty {
    private static final Logger LOGGER = LogManager.getLogger(MailProperty.class);

    private static MailProperty instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
    private static Properties properties = new Properties();

    private static final String PROPERTY_MAIL = "mail";//

    private static final String MAIL_SMTP_HOST = "mail.smtp.host";
    private static final String MAIL_SMTP_PORT = "mail.smtp.port";
    private static final String MAIL_USER_NAME = "mail.user.name";
    private static final String MAIL_USER_PASSWORD = "mail.user.password";

    private MailProperty() {}

    public static MailProperty getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (!instanceCreated.get()) {
                    instance = new MailProperty();
                    init();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Properties getProperties() {
        return properties;
    }

    private static void init() {
        ResourceBundle resourceBundle;

        try {
            resourceBundle = ResourceBundle.getBundle(PROPERTY_MAIL);
        } catch (MissingResourceException e) {
            LOGGER.log(Level.FATAL, "No mail.properties found");
            throw new RuntimeException("No mail.properties found");
        }

        final String HOST = resourceBundle.getString("mail.smtp.host");
        final String PORT = resourceBundle.getString("mail.smtp.port");
        final String USERNAME = resourceBundle.getString("mail.user.name");
        final String PASSWORD = resourceBundle.getString("mail.user.password");

        properties.put(MAIL_SMTP_HOST, HOST);
        properties.put(MAIL_SMTP_PORT, PORT);
        properties.put(MAIL_USER_NAME, USERNAME);
        properties.put(MAIL_USER_PASSWORD, PASSWORD);
    }
}