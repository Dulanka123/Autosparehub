package lk.ijse.backend.service.impl;

import lk.ijse.backend.DTO.ItemDTO;
import lk.ijse.backend.DTO.OrderDTO;
import lk.ijse.backend.entity.Order;
import lk.ijse.backend.repo.OrderItemDetailRepository;
import lk.ijse.backend.repo.OrderRepository;
import lk.ijse.backend.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemDetailRepository orderItemDetailRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<OrderDTO> getOrdersByShopId(long shopId) {
        List<Long> orderIds = orderItemDetailRepository.findDistinctOrderIdsByShopId(shopId);
        for (Long orderId : orderIds) {
            System.out.println("Order ID: " + orderId);
        }
        if (orderIds.isEmpty()) {
            return Collections.emptyList();
        }
       List<Order> orderList = orderRepository.findByOrderIdIn(orderIds);
        for (Order order : orderList) {
            System.out.println("Order: " + order);
        }
        return modelMapper.map(orderList,new TypeToken<List<OrderDTO>>(){}.getType());
    }

    @Override
    public List<OrderDTO> getLastFiveOrdersByShopId(long shopId) {
        List<Long> orderIds = orderItemDetailRepository.findDistinctOrderIdsByShopId(shopId);
        if (orderIds.isEmpty()) {
            return Collections.emptyList();
        }

        Pageable topFive = PageRequest.of(0, 5);
        List<Order> orderList = orderRepository.findTop5ByOrderIdInOrderByOrderIdDesc(orderIds, topFive);

        return modelMapper.map(orderList, new TypeToken<List<OrderDTO>>() {}.getType());

    }
}
