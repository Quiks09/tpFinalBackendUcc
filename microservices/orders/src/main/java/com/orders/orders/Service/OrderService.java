package com.orders.orders.Service;

import com.orders.orders.Model.Entity.Order;
import com.orders.orders.Model.Mappers.OrdersMapper;
import com.orders.orders.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrdersMapper ordersMapper;
    private final RestTemplate restTemplate;

    @Value("${product.service.url:http://localhost:8080/api}")
    private String productServiceUrl;

    public OrderService(OrderRepository orderRepository, OrdersMapper ordersMapper, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.ordersMapper = ordersMapper;
        this.restTemplate = restTemplate;
    }

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public ResponseEntity<?> newOrder(Order order){
        // Consultar stock
        String url = productServiceUrl + "/products/" + order.getProductId();
        ResponseEntity<ProductResponse> response;

        try {
            response = restTemplate.getForEntity(url, ProductResponse.class);
        } catch (Exception e) {
            // Manejo de errores si el servicio de productos no está disponible
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "El producto con ID " + order.getProductId() + " no existe");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }


        ProductResponse product = response.getBody();


        if (product.getStock() == null || product.getStock() <= 0 || product.getStock() < order.getStock()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "El producto con ID " + order.getProductId() + " no tiene stock disponible");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }



        orderRepository.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    // Clase interna para mapear la respuesta del servicio de productos
    private static class ProductResponse {
        private Integer stock;
        public Integer getStock() { return stock; }
        public void setStock(Integer stock) { this.stock = stock; }
    }

    public ResponseEntity<Order> deleteOrder(Long id){
        try {
            Order orderDelete = orderRepository.findById(id).orElse(null);
            if (orderDelete == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                orderRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
