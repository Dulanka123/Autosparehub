package lk.ijse.backend.repo;

import lk.ijse.backend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart,Long> {
    void deleteCartByUser_UserId(UUID userUserId);

    boolean existsCartByUser_UserId(UUID userUserId);

    List<Cart> findCartsByUser_UserId(UUID userUserId);

    List<Cart> findAllByUser_UserId(UUID userUserId);


}
