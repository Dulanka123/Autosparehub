package lk.ijse.backend.repo;

import lk.ijse.backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment , Long> {
    boolean existsPaymentByPaymentId(long paymentId);
    Payment findPaymentByPaymentId(long paymentId);
}
