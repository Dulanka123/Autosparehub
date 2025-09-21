package lk.ijse.backend.service;

import lk.ijse.backend.DTO.CartDTO;
import lk.ijse.backend.DTO.UserDTO;
import lk.ijse.backend.entity.Cart;

import java.util.List;
import java.util.UUID;

public interface CartService {
    int saveCart (CartDTO cartDTO);
    int updateCart (List<CartDTO> itemList);
    int deleteCartByUser (UUID userId);
    int deleteItemById (long cartId);
    List<CartDTO> getAllCartsByUser (UUID userId);
    List<CartDTO> getAllCart();

}
