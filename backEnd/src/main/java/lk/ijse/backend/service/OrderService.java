package lk.ijse.backend.service;

import lk.ijse.backend.DTO.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getOrdersByShopId(long shopId);

    List<OrderDTO> getLastFiveOrdersByShopId(long shopId);

}
