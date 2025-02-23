package lk.ijse.service;

import lk.ijse.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    boolean saveOrder(OrderDTO orderDTO);

    OrderDTO getOrderByCode(String orderId);

    List<OrderDTO> getAllOrders();

}