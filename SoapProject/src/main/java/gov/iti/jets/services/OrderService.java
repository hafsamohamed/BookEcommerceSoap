package gov.iti.jets.services;

import gov.iti.jets.persistence.entities.Order;
import gov.iti.jets.presentation.dtos.OrderDto;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface OrderService {
    public List<OrderDto> getAllOrders();
    public List<OrderDto> getOrderByUserId(int id);
    public Order getOrderById(int userId, int orderId);

    boolean saveOrder(int userId);
    boolean deleteOrder(int userId, int orderId);

}
