package gov.iti.jets.services.impl;

import gov.iti.jets.persistence.OrderDao;
import gov.iti.jets.persistence.entities.Order;
import gov.iti.jets.persistence.impl.OrderDaoImpl;
import gov.iti.jets.presentation.controllers.OrderController;
import gov.iti.jets.presentation.dtos.OrderDto;
import gov.iti.jets.services.OrderService;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "gov.iti.jets.services.OrderService")
public class OrderServiceImpl implements OrderService {
    public static OrderController orderController = new OrderController();

    @Override
    public boolean saveOrder(int userId) {
         return orderController.addProductToCart(userId);
      }
    @Override
    public List<OrderDto> getAllOrders() {
        return orderController.getAllOrders();
    }

    @Override
    public List<OrderDto> getOrderByUserId(int id) {
        return orderController.getAllCartProducts(id);
    }
    @Override
    public boolean deleteOrder(int userId, int orderId) {
        return orderController.deleteOrder(userId, orderId);
    }
    @Override
    public Order getOrderById(int userId, int orderId) {
        OrderDao orderDao = new OrderDaoImpl();
        return orderDao.getOrderById(userId, orderId);
    }
}
