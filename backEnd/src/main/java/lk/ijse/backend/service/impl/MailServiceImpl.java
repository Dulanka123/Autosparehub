package lk.ijse.backend.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import lk.ijse.backend.DTO.LoginAlertDTO;
import lk.ijse.backend.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void sendMail(String to, String subject, LoginAlertDTO dto) throws MessagingException {
        String htmlBody = generateLoginAlertHtml(
                dto.getUsername(),
                dto.getLoginTime(),
                dto.getDevice(),
                dto.getLocation()
        );

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        helper.setFrom("mail");

        mailSender.send(message);
    }

    @Override
    public void sendMailWithAttachment(String to, String subject, String username, long orderId, String paymentTime, String amount, byte[] pdfData, String fileName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);

            // Generate HTML email body
            String htmlBody = generatePaymentSuccessHtml(username, orderId, paymentTime, amount);
            helper.setText(htmlBody, true);

            // Add attachment
            ByteArrayResource resource = new ByteArrayResource(pdfData);
            helper.addAttachment(fileName, resource);

            helper.setFrom("mail");

            mailSender.send(message);
            System.out.println("Payment confirmation email sent to: " + to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateLoginAlertHtml(String username, String loginTime, String device, String location) {
        return """
<!DOCTYPE html>
<html>
<head>
    <style>
        body { font-family: Arial, sans-serif; background-color: #ffffff; color: #000000; padding: 20px; }
        .container { background-color: #f5f5f5; border-radius: 8px; padding: 20px; border-left: 5px solid #ff4d4d; }
        .highlight { font-weight: bold; color: #ff4d4d; }
        .footer { font-size: 12px; color: #000000; margin-top: 20px; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Hi <span class="highlight">%s</span>,</h2>
        <p>We’ve detected a login to your account from a new device.</p>
        <p><strong>When:</strong> %s</p>
        <p><strong>Device:</strong> %s</p>
        <p><strong>Near:</strong> %s</p>
        <br>
        <p>If this was you, you can ignore this message.</p>
        <p>If not, please update your security settings immediately.</p>
        <div class="footer">© 2025 PartsLK. All rights reserved.</div>
    </div>
</body>
</html>
""".formatted(username, loginTime, device, location);
    }


    private String generatePaymentSuccessHtml(String username, long orderId, String paymentTime, String amount) {
        return """
<!DOCTYPE html>
<html>
<head>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #ffffff; color: #000000; padding: 20px; }
        .container { background-color: #f5f5f5; border-radius: 10px; padding: 30px; max-width: 600px; margin: auto; border-left: 5px solid #ff4d4d; }
        h2 { color: #ff4d4d; }
        .details p { line-height: 1.6; }
        .highlight { color: #ff4d4d; font-weight: bold; }
        .footer { font-size: 12px; color: #000000; text-align: center; margin-top: 40px; }
        .success-icon { font-size: 50px; color: #ff4d4d; text-align: center; }
    </style>
</head>
<body>
    <div class="container">
        <div class="success-icon">✅</div>
        <h2>Thank You, <span class="highlight">%s</span>!</h2>
        <p>Your payment has been successfully processed.</p>

        <div class="details">
            <p><strong>Order ID:</strong> %s</p>
            <p><strong>Payment Time:</strong> %s</p>
            <p><strong>Amount Paid:</strong> <span class="highlight">LKR %s</span></p>
        </div>

        <p>Please find your invoice attached with this email.</p>
        <p>If you have any questions, feel free to contact our support.</p>

        <div class="footer">
            © 2025 PartsLK. All rights reserved.
        </div>
    </div>
</body>
</html>
""".formatted(username, orderId, paymentTime, amount);
    }




}
