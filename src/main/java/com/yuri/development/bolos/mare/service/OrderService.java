package com.yuri.development.bolos.mare.service;

import com.yuri.development.bolos.mare.dto.OrderCreateDTO;
import com.yuri.development.bolos.mare.dto.OrderDTO;
import com.yuri.development.bolos.mare.dto.ProductDTO;
import com.yuri.development.bolos.mare.model.Order;
import com.yuri.development.bolos.mare.repository.OrderRepository;
import com.yuri.development.bolos.mare.util.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<?> create(OrderCreateDTO orderDTO){

        if(orderDTO.getProductList().isEmpty()){
            return new ResponseEntity<>(ErrorConstants.ORDER_CAN_NOT_BE_EMPTY, HttpStatus.BAD_REQUEST);
        }

        Order order = new Order();
        adaptDtoToOrder(order, orderDTO);
        orderRepository.save(order);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    public ResponseEntity<?> findAll(){

        List<Order> orderList = orderRepository.findAll();
        List<OrderDTO> orderDTOList;

        orderDTOList = orderList.stream()
                .map(this::adaptOrderToDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderDTOList, HttpStatus.OK);
    }

    public ResponseEntity<?> update(){
        return null;
    }

    public ResponseEntity<?> delete(Long id){

        Optional<Order> optOrder = orderRepository.findById(id);
        if(optOrder.isEmpty()){
            return new ResponseEntity<>(ErrorConstants.RESOURCE_NOT_FOUND,HttpStatus.NOT_FOUND);
        }

        orderRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void adaptDtoToOrder(Order order, OrderCreateDTO orderCreateDTO){

        order.setNote(orderCreateDTO.getNote());
        order.setProductsList(orderCreateDTO.getProductList());
    }

    private OrderDTO adaptOrderToDto(Order order){

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setNote(order.getNote());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setProductList(order.getProductsList());

        return orderDTO;
    }
}
