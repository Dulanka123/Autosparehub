package lk.ijse.backend.repo;

import lk.ijse.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsItemByItemName(String itemName);
    Item findItemByItemName(String itemName);

    boolean existsByShop_ShopId(long shopShopId);
    List<Item> findItemByShop_ShopId(long shopShopId);
}
