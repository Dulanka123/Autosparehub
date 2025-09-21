package lk.ijse.backend.repo;

import lk.ijse.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsOrderByOrderId(long orderId);
    Order findOrderByOrderId(long orderId);
    List<Order> findByOrderIdIn(List<Long> orderIds);
    List<Order> findTop5ByOrderIdInOrderByOrderIdDesc(List<Long> orderIds, org.springframework.data.domain.Pageable pageable);
}
