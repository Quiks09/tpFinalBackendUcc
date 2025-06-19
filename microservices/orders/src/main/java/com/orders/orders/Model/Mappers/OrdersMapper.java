package com.orders.orders.Model.Mappers;

import com.orders.orders.Model.DTO.OrderDTO;
import com.orders.orders.Model.Entity.Order;
import com.orders.orders.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrdersMapper {

    private final OrderRepository orderRepository;

    public Order orderDTOToOrdersEntity(OrderDTO orderDTO){
        Order productEntity = new Order();
        productEntity.setName(orderDTO.getName());

        return productEntity;
    }

    public OrderDTO OrdersEntityToOrderDTO(Order orderEntity){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setName(orderEntity.getName());
        return  orderDTO;
    }
}