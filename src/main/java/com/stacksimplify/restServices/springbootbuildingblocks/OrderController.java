package com.stacksimplify.restServices.springbootbuildingblocks;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/{userId}/orders")
    public List<OrderEntity> getAllOrders( @PathVariable Long userId) throws UserNotFound {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(userEntity.isPresent()){
            return userEntity.get().getOrders();
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

    @PostMapping("/{userId}/orders")
    public OrderEntity createOrder( @PathVariable Long userId, @Valid @RequestBody OrderEntity order) throws UserNotFound {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(userEntity.isPresent()){
            // Gets the user that should have his order updated in the current context
            UserEntity userToUpdate = userEntity.get();
            order.setUser(userToUpdate);
            // And saves the order accordingly
            return orderRepository.save(order);
        }
        else{
            throw new UserNotFound("User with id " + userId + " not found.");
        }
    }
}
