package gov.iti.jets.presentation.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.persistence.OrderDao;
import gov.iti.jets.persistence.ProductDao;
import gov.iti.jets.persistence.UserDao;
import gov.iti.jets.persistence.entities.Order;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.impl.OrderDaoImpl;
import gov.iti.jets.persistence.impl.ProductDaoImpl;
import gov.iti.jets.persistence.impl.UserDaoImpl;
import gov.iti.jets.persistence.util.mappers.CartIdMapper;
import gov.iti.jets.persistence.util.mappers.OrderMapper;
import gov.iti.jets.presentation.dtos.OrderDto;
import gov.iti.jets.services.CartProductsService;
import gov.iti.jets.services.impl.CartProductsServiceImpl;

public class OrderController {
    public static OrderMapper orderMapper = OrderMapper.INSTANCE;
    public static CartProductsService cartProductsService = new CartProductsServiceImpl();
    public static UserDao userService = new UserDaoImpl();
    public static ProductDao productService = new ProductDaoImpl();
    public static OrderDao orderService = new OrderDaoImpl();
    boolean check = false;
    int total_price = 0;

    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderDtos = new ArrayList<>();
        orderService.getAllOrders()
                .forEach(cart -> orderDtos.add(orderMapper.orderToDto(cart)));

        if (orderDtos.isEmpty()) {
            return null;
        }
        return orderDtos;
    }

    public List<OrderDto> getAllCartProducts(int id) {
        User user = userService.getUser(id);

        if (user == null) {
            return null;
        }

        List<OrderDto> orderDtos = new ArrayList<>();
        orderService.getOrderByUserId(id)
                .forEach(cart -> orderDtos.add(orderMapper.orderToDto(cart)));

        if (orderDtos.isEmpty()) {
            return null;
        }
        return orderDtos;
    }

    public boolean addProductToCart(int userId) {

        User user = userService.getUser(userId);
        if (user == null) {
            return false;
        }

        cartProductsService.getCartList(userId)
                .forEach(cart -> {
                    total_price += cart.getTotalPrice();
                });

        if (total_price <= 0) {
            return false;
        }
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(total_price);
        order.setOrderTime(LocalDate.now());
        orderService.saveOrder(order);
        cartProductsService.getCartList(userId)
                .forEach(cart -> cartProductsService
                        .removeCartProduct(CartIdMapper.INSTANCE.cartIdToDto(cart.getCartId())));

        return true;

    }

    public boolean deleteOrder(int userId, int orderId) {
        User user = userService.getUser(userId);
        if (user == null) {
            return false;
        }

        Order order = orderService.getOrderById(userId, orderId);
        if (order == null) {
            return false;
        }

        orderService.deleteOrder(userId, orderId);
        return true;
    }
}
