package com.stacksimplify.restServices.springbootbuildingblocks;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class OrderHateoasController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/{userId}/orders")
    public CollectionModel<OrderEntity> getAllOrders(@PathVariable Long userId) throws UserNotFound {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(userEntity.isPresent()){
            List<OrderEntity> orderEntities =  userEntity.get().getOrders();
            CollectionModel<OrderEntity> orderModel = CollectionModel.of(orderEntities);
            return orderModel;
        }
        else{
            throw new UserNotFound("User with id " + userId + " not found.");
        }
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public OrderEntity getOrderById( @PathVariable Long userId ,@PathVariable Long orderId) throws UserNotFound, OrderNotFound {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(userEntity.isPresent()){
            List<OrderEntity> userOrders = userEntity.get().getOrders();
            OrderEntity orderFound = null;
            for(OrderEntity orderEntity : userOrders){
                if(orderEntity.getOrderId().equals(orderId)){
                    orderFound = orderEntity;
                }
            }
            if(orderFound == null){
                throw new OrderNotFound("Order with id " + orderId + " has not been found for user with id " + userId + " .");
            }
            else{
                return orderFound;
            }
        }
        else{
            throw new UserNotFound("User with id " + userId + " not found.");
        }
    }
}
