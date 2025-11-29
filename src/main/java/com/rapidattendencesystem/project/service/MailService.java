package com.rapidattendencesystem.project.service;

import com.rapidattendencesystem.project.dto.EmailDataDTO;
import com.rapidattendencesystem.project.dto.TeacherEmailDTO;
import com.rapidattendencesystem.project.entity.Student;
import com.rapidattendencesystem.project.repo.StudentRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private StudentRepo studentrepo;

    public String SendEmail(String emailMessage, int teacherId){
        try{
            if (emailMessage == null) {
                throw new IllegalArgumentException("No Message");
            }

            List<String> students = studentrepo.findEmailsByTeacherId(teacherId);
//            int length = students.size();

            String[] recipients = students.toArray(new String[0]);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(recipients);
            helper.setSubject("Notification");
            helper.setText(emailMessage);

//            byte[] pdfBytes = Base64.getDecoder().decode(emailData.getPdfBase64());
//            ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

//            helper.addAttachment("receipt.pdf", pdfResource);

            mailSender.send(message);

            return "Message Sent";
        }catch(Exception e){
            System.err.println("MessagingException: " + e.getMessage());
            return "Messageing Exeption Occurd";
        }
    }

    public String SendTeacherReciptEmailWithAttachment(TeacherEmailDTO emailData){
        try{
            if (emailData.getTeacherEmail() == null || emailData.getTeacherEmail().isEmpty()) {
                throw new IllegalArgumentException("Student email is null or empty");
            }

            String[] recipients = { emailData.getTeacherEmail()};
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(recipients);
            helper.setSubject("Your Receipt from Rapid Institute");
            helper.setText("Please find your receipt attached.");

            byte[] pdfBytes = Base64.getDecoder().decode(emailData.getPdfBase64());
            ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

            helper.addAttachment("receipt.pdf", pdfResource);

            mailSender.send(message);

            return "Message Sent";
        }catch(Exception e){
            System.err.println("MessagingException: " + e.getMessage());
            return "Messageing Exeption Occurd";
        }
    }

    public String SendEmailWithAttachment(EmailDataDTO emailData){
        try{
            if (emailData.getStudentEmail() == null || emailData.getStudentEmail().isEmpty()) {
                throw new IllegalArgumentException("Student email is null or empty");
            }
            if (emailData.getParentEmail() == null || emailData.getParentEmail().isEmpty()) {
                throw new IllegalArgumentException("Parent email is null or empty");
            }
            String[] recipients = { emailData.getStudentEmail(), emailData.getParentEmail() };
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(recipients);
            helper.setSubject("Your Receipt from Rapid Institute");
            helper.setText("Please find your receipt attached.");

            byte[] pdfBytes = Base64.getDecoder().decode(emailData.getPdfBase64());
            ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes);

            helper.addAttachment("receipt.pdf", pdfResource);

            mailSender.send(message);

            return "Message Sent";
        }catch(Exception e){
            System.err.println("MessagingException: " + e.getMessage());
            return "Messageing Exeption Occurd";
        }
    }
}
