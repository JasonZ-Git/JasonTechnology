package org.jason.util.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public final class JasonMailUtil {
  private JasonMailUtil() {
    throw new AssertionError();
  }

  public static void sendMail(String sentEmail, String messageBody) throws EmailException {
    Properties properties;
    try {
      properties = load();
    } catch (IOException e) {
      throw new EmailException(e);
    }

    String userName = properties.getProperty("username");
    String password = properties.getProperty("password");
    String fromEmail = properties.getProperty("fromEmail");
    String subject = properties.getProperty("subject");
    String smtpHost = properties.getProperty("smtp.host");
    String smtpPort = properties.getProperty("smtp.port");

    Email email = new SimpleEmail();
    email.setHostName(smtpHost);
    email.setSmtpPort(Integer.parseInt(smtpPort));
    email.setAuthenticator(new DefaultAuthenticator(userName, password));
    email.setSSLOnConnect(true);
    email.setFrom(fromEmail);
    email.setSubject(subject);
    email.setMsg(messageBody);
    email.addTo(sentEmail);

    email.send();
    
  }

  private static Properties load() throws IOException {
    Properties properties = new Properties();
    InputStream in = JasonMailUtil.class.getClassLoader().getResourceAsStream("mail_credentials.properties");
    properties.load(in);
    return properties;
  }
}
