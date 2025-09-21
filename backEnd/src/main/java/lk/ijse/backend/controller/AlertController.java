package lk.ijse.backend.controller;

import jakarta.mail.MessagingException;
import lk.ijse.backend.DTO.LoginAlertDTO;
import lk.ijse.backend.DTO.PlacePaymentDTO;
import lk.ijse.backend.DTO.ResponseDTO;
import lk.ijse.backend.DTO.UserDTO;
import lk.ijse.backend.service.MailService;
import lk.ijse.backend.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static lk.ijse.backend.util.InvoiceGenerator.generateInvoicePDF;

@RestController
@RequestMapping("/api/v1/alert")
@CrossOrigin
public class AlertController {

    private final MailService mailService;

    public AlertController(MailService mailService) {
        this.mailService = mailService;
    }
    @PostMapping("/send-login-alert")
    public ResponseEntity<ResponseDTO> sendLoginAlert(@RequestBody LoginAlertDTO dto, UserDTO userDTO) {
        try {
            mailService.sendMail(userDTO.getEmail(), "Login Alert - New Device Detected", dto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK, "Email sent successfully", null));
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(VarList.Internal_Server_Error, "Failed to send email", null));
        }
    }

    @PostMapping("/send-payment-confirmation")
    public ResponseEntity<ResponseDTO> sendPaymentMail(PlacePaymentDTO placePaymentDTO, long orderId, double total) throws Exception {
        String username = placePaymentDTO.getUserDTO().getName();
        String paymentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String amount = String.valueOf(total);
        byte[] pdfInvoice = generateInvoicePDF(placePaymentDTO, orderId);
        String fileName = "Invoice_" + orderId + ".pdf";

        mailService.sendMailWithAttachment(placePaymentDTO.getUserDTO().getEmail(), "Your Payment is Successful!", username, orderId, paymentTime, amount, pdfInvoice, fileName);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK, "Email sent successfully", null));
    }

}
