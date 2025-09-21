package lk.ijse.backend.repo;

import lk.ijse.backend.entity.Order;
import lk.ijse.backend.entity.OrderedItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface OrderItemDetailRepository extends JpaRepository<OrderedItemDetail, Long> {
    @Query("SELECT DISTINCT o.order.orderId FROM OrderedItemDetail o WHERE o.shopId = :shopId")
    List<Long> findDistinctOrderIdsByShopId(@Param("shopId") long shopId);

    @Query("SELECT o FROM Order o WHERE o.orderId IN :orderIds ORDER BY o.orderId DESC")
    List<Order> findTop5ByOrderIdInOrderByOrderIdDesc(@Param("orderIds") List<Long> orderIds, Pageable pageable);

}
