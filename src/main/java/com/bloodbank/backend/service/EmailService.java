package com.bloodbank.backend.service;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService
{
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String sendPasswordByEmail(String EmailId) throws MailException{
        SimpleMailMessage mail = new SimpleMailMessage();
        String password = passwordGenerator();
        mail.setFrom("no-reply@gmail.com");
        mail.setTo(EmailId);
        mail.setSubject("BloodBank Portal Password");
        mail.setText("Dear " + EmailId + ", \nYour temporary password for BloodBank portal is: \n"
                + password + ". "+
                "\nPlease, Change your password after Signin.");

        javaMailSender.send(mail);
        return password;
    }

    public String passwordGenerator() {
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String values = Capital_chars + Small_chars + numbers;
        Random rand = new Random();

        int len  = 6;
        String password = "";

        for (int i = 0; i < len; i++){
            password += values.charAt(rand.nextInt(values.length()));

        }
        return password;
    }

}
