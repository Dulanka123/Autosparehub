package lk.ijse.backend.controller;

import lk.ijse.backend.DTO.OrderDTO;
import lk.ijse.backend.DTO.PlacePaymentDTO;
import lk.ijse.backend.DTO.ResponseDTO;
import lk.ijse.backend.service.OrderService;
import lk.ijse.backend.util.VarList;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get-all/{shopId}")
    public ResponseEntity<ResponseDTO> getAllOrders(@PathVariable long shopId, @RequestHeader ("Authorization") String authHeader) {
        try {
            List<OrderDTO> allOrders = orderService.getOrdersByShopId(shopId);
            for (OrderDTO order : allOrders) {
                System.out.println("order controller get resuls " + order.toString());
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK, "Success", allOrders));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }


}
