package lk.ijse.backend.service;

import jakarta.mail.MessagingException;
import lk.ijse.backend.DTO.LoginAlertDTO;

public interface MailService {
    void sendMail(String to, String subject, LoginAlertDTO dto) throws MessagingException;

    void sendMailWithAttachment(String to, String subject, String username, long orderId, String paymentTime, String amount, byte[] pdfData, String fileName);

}
