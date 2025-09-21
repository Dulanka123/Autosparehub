package lk.ijse.backend.repo;

import lk.ijse.backend.entity.Shop;
import lk.ijse.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    boolean existsShopByShopEmail(String shopEmail);
    Shop findShopByShopEmail(String shopEmail);
    Shop findShopByUser(User user);
}
