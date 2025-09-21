package lk.ijse.backend.service;


import lk.ijse.backend.DTO.ShopDTO;
import lk.ijse.backend.DTO.UserDTO;

import java.util.List;
import java.util.UUID;

public interface ShopService {
     int saveShop(ShopDTO shopDTO);
     ShopDTO searchShop(String shopName);
     int deleteShop(long shopId);
     int updateShop(ShopDTO shopDTO);
     ShopDTO findShop(UserDTO userDTO);
     List<ShopDTO> findAllShops();
     ShopDTO searchShopById(long shopId);
}
