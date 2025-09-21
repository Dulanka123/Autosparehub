package lk.ijse.backend.controller;

import jakarta.validation.Valid;
import lk.ijse.backend.DTO.CartDTO;
import lk.ijse.backend.DTO.ResponseDTO;
import lk.ijse.backend.DTO.ShopDTO;
import lk.ijse.backend.DTO.UserDTO;
import lk.ijse.backend.service.CartService;
import lk.ijse.backend.service.ShopService;
import lk.ijse.backend.service.UserService;
import lk.ijse.backend.util.JwtUtil;
import lk.ijse.backend.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
@CrossOrigin
public class CartController {
    private final CartService cartService;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final ShopService shopService;

    public CartController(CartService cartService, JwtUtil jwtUtil, UserService userService, ShopService shopService) {
        this.cartService = cartService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.shopService = shopService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> saveCart(@Valid @RequestBody CartDTO cartDTO, @RequestHeader ("Authorization") String authHeader){
        try {
            String token = authHeader.substring(7);
            String username = jwtUtil.getUsernameFromToken(token);
            UserDTO userDTO = userService.searchUser(username);
            cartDTO.setUserId(userDTO.getUserId());

            ShopDTO shopDTO = shopService.searchShopById(cartDTO.getShopId());
            cartDTO.setShopId(shopDTO.getShopId());




            int res = cartService.saveCart(cartDTO);
            switch (res){
                case VarList.Created -> {
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", null));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
        }

        @PutMapping("/update")
        public ResponseEntity<ResponseDTO> updateCart(@Valid @RequestBody List<CartDTO> cartItems, @RequestHeader ("Authorization") String authHeader){
            try {
                String token = authHeader.substring(7);
                String username = jwtUtil.getUsernameFromToken(token);
                UserDTO userDTO = userService.searchUser(username);

                cartItems.forEach(item -> item.setUserId(userDTO.getUserId()));

                int res = cartService.updateCart(cartItems);

                switch (res){
                    case VarList.OK -> {
                        return ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseDTO(VarList.OK, "Success", null));
                    }
                    case VarList.Not_Found -> {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ResponseDTO(VarList.Not_Found, "Cart Not Found", null));
                    }
                    default -> {
                        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                                .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                    }
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
            }
            }

            @DeleteMapping("/delete")
            public ResponseEntity<ResponseDTO> deleteCart(@Valid @RequestBody CartDTO cartDTO, @RequestHeader ("Authorization") String authHeader){
                try {
                    String token = authHeader.substring(7);
                    String username = jwtUtil.getUsernameFromToken(token);
                    UserDTO userDTO = userService.searchUser(username);
                    cartDTO.setUserId(userDTO.getUserId());

                    ShopDTO shopDTO = shopService.searchShopById(cartDTO.getShopId());
                    cartDTO.setShopId(shopDTO.getShopId());

                    int res = cartService.deleteCartByUser(cartDTO.getUserId());
                    switch (res){
                        case VarList.OK -> {
                            return ResponseEntity.status(HttpStatus.OK)
                                    .body(new ResponseDTO(VarList.OK, "Success", null));
                        }
                        case VarList.Not_Found -> {
                            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body(new ResponseDTO(VarList.Not_Found, "Cart Not Found", null));
                        }
                        default -> {
                            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                                    .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                        }
                    }
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
                }
                }
                @DeleteMapping("/delete/{cartId}")
                public ResponseEntity<ResponseDTO> deleteCart(@PathVariable long cartId){
                    try {
                        int res = cartService.deleteItemById(cartId);
                        switch (res){
                            case VarList.OK -> {
                                return ResponseEntity.status(HttpStatus.OK)
                                        .body(new ResponseDTO(VarList.OK, "Success", null));
                            }
                            case VarList.Not_Found -> {
                                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                        .body(new ResponseDTO(VarList.Not_Found, "Cart Not Found", null));
                            }
                            default -> {
                                return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                                        .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                            }
                        }
                    } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
                    }
                    }
                    @GetMapping("/get")
                    public ResponseEntity<ResponseDTO> getCart(@RequestHeader ("Authorization") String authHeader){
                        try {
                            String token = authHeader.substring(7);
                            String username = jwtUtil.getUsernameFromToken(token);
                            UserDTO userDTO = userService.searchUser(username);
                            List<CartDTO> allCartsByUser = cartService.getAllCartsByUser(userDTO.getUserId());

                            return ResponseEntity.status(HttpStatus.OK)
                                    .body(new ResponseDTO(VarList.OK, "Success", allCartsByUser));
                        } catch (Exception e) {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
                        }
                        }
                        @GetMapping("/getAll")
                        public ResponseEntity<ResponseDTO> getAllCarts(){
                            try {
                                List<CartDTO> allCarts = cartService.getAllCart();
                                for (CartDTO cartDTO : allCarts) {
                                    System.out.println(cartDTO.toString());
                                }
                                return ResponseEntity.status(HttpStatus.OK)
                                        .body(new ResponseDTO(VarList.OK, "Success", allCarts));
                            } catch (Exception e) {
                                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
                            }
                        }
    }
