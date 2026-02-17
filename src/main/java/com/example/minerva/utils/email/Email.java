package com.example.minerva.utils.email;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class Email {
    private static Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    private final String username = dotenv.get("EMAIL_USER", System.getenv("EMAIL_USER"));
    private final String password = dotenv.get("EMAIL_PASSWORD", System.getenv("EMAIL_PASSWORD"));

    public void sendRegistration(String toEmail, String registration) {

        // Configurações SMTP do Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Autenticação
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );

            message.setSubject("Seu código de matrícula");

            String html =
                    "<html>\n" +
                            "<body style=\"font-family: Georgia, serif; background-color: #fdf6e3; padding: 20px;\">\n" +
                            "<div style=\"border: 2px solid #2E8B57; border-radius: 10px; padding: 20px; background-color: #fffde7; max-width: 600px; margin: auto;\">\n" +
                            "<h1 style=\"color: #4B0082; text-align: center;\"> ⫘⫘⫘⫘⫘⫘ Bem-vindo(a) a Hogwarts! ⫘⫘⫘⫘⫘⫘</h1>\n" +
                            "<p>Seu código de matrícula é:</p>\n" +
                            "<h2 style=\"color: #2E8B57; text-align: center;\">" + registration + "</h2>\n" +
                            "<p style=\"text-align: center;\">Válido por 4 dias.</p>\n" +
                            "<hr>\n" +
                            "<p style=\"text-align: center; color: gray;\">\n" +
                            "Hogwarts School of Witchcraft and Wizardry<br>\n" +
                            "🪄 Que a magia esteja com você! 🪄\n" +
                            "</p>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "</html>";

            message.setContent(html, "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Email enviado com sucesso!");

        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}
