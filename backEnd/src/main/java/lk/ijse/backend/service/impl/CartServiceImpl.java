package lk.ijse.backend.service.impl;

import lk.ijse.backend.DTO.CartDTO;
import lk.ijse.backend.entity.Cart;
import lk.ijse.backend.repo.CartRepository;
import lk.ijse.backend.service.CartService;
import lk.ijse.backend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public int saveCart(CartDTO cartDTO) {
        try {
            if (cartRepository.existsById(cartDTO.getCartId())) {
                return VarList.Conflict;
            }else {
                cartRepository.save(modelMapper.map(cartDTO, Cart.class));
                return VarList.Created;
            }
        } catch (Exception e) {
            return VarList.Bad_Gateway;
        }
    }

    @Override
    public int updateCart(List<CartDTO> itemList) {
        System.out.println("Cart update method called");

        boolean allExist = true;

        for (CartDTO item : itemList) {
            System.out.println("Updating Cart Item: " + item);

            if (cartRepository.existsById(item.getCartId())) {
                Cart existingCart = cartRepository.findById(item.getCartId()).orElse(null);
                if (existingCart != null) {
                    // Manually set fields you want to update (safer than full map)
                    existingCart.setQty(item.getQty());

                    cartRepository.save(existingCart);
                }
            } else {
                System.out.println("Cart not found: " + item.getCartId());
                allExist = false;
            }
        }

        return allExist ? VarList.OK : VarList.Not_Found;
    }

    @Override
    public int deleteCartByUser(UUID userId) {
        if (cartRepository.existsCartByUser_UserId(userId)) {
            cartRepository.deleteCartByUser_UserId(userId);
            return VarList.OK;
        }else {
            return VarList.Not_Found;
        }
    }

    @Override
    public int deleteItemById(long cartId) {
        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
            return VarList.OK;
        }else {
            return VarList.Not_Found;
        }
    }

    @Override
    public List<CartDTO> getAllCartsByUser(UUID userId) {
            return modelMapper.map(cartRepository.findAllByUser_UserId(userId), new TypeToken<List<CartDTO>>(){}.getType());
    }

    @Override
    public List<CartDTO> getAllCart() {
        return modelMapper.map(cartRepository.findAll(),new TypeToken<List<CartDTO>>(){}.getType());
    }
}
