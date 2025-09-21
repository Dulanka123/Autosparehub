package lk.ijse.backend.service;

import lk.ijse.backend.DTO.ItemDTO;

import java.util.List;

public interface ItemService {
    int addItem(ItemDTO itemDTO);
    int deleteItem(long itemId);
    int updateItem(ItemDTO itemDTO);
    ItemDTO searchItem(long itemId);
    List<ItemDTO> getAllItems();
    ItemDTO searchItemByName(String itemName);
    List<ItemDTO> searchItemByShop(long shopId);

}
