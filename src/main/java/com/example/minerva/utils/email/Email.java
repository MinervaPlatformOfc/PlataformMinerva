package com.example.minerva.utils.email;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;

import java.security.SecureRandom;
import java.util.Properties;

public class Email {
    private static Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    private final String username = dotenv.get("EMAIL_USER", System.getenv("EMAIL_USER"));
    private final String password = dotenv.get("EMAIL_PASSWORD", System.getenv("EMAIL_PASSWORD"));

    public boolean sendRegistration(String toEmail, String msg) {

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

            String subject;
            String titulo;
            String descricao;
            String validadeTexto;

            // Verifica se é OTP (6 caracteres) ou Matrícula (8 caracteres)
            if (msg != null && msg.length() == 6) {
                subject = "Código para redefinição de senha";
                titulo = "🔐 Redefinição de Senha 🔐";
                descricao = "Seu código OTP para redefinir sua senha é:";
                validadeTexto = "10 minutos";
            } else {
                subject = "Seu código de matrícula";
                titulo = "⫘ Bem-vindo(a) a Hogwarts! ⫘";
                descricao = "Seu código de matrícula é:";
                validadeTexto = "4 dias";
            }

            message.setSubject(subject);

            String html =
                    "<html>\n" +
                            "<body style=\"font-family: Georgia, serif; background-color: #fdf6e3; padding: 20px;\">\n" +
                            "<div style=\"border: 2px solid #2E8B57; border-radius: 10px; padding: 20px; background-color: #fffde7; max-width: 600px; margin: auto;\">\n" +
                            "<h1 style=\"color: #4B0082; text-align: center;\">" + titulo + "</h1>\n" +
                            "<p style=\"text-align: center;\">" + descricao + "</p>\n" +
                            "<h2 style=\"color: #2E8B57; text-align: center;\">" + msg + "</h2>\n" +
                            "<p style=\"text-align: center;\">Válido por " + validadeTexto + ".</p>\n" +
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
            return true; // sucesso


        } catch (MessagingException me) {
            me.printStackTrace();
            return false; // falha
        }
    }
}
