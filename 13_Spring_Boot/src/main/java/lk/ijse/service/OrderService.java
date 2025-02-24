package lk.ijse.service;

import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.OrderDetailDTO;

import java.util.List;

public interface OrderService {

    boolean saveOrder(OrderDTO orderDTO);

    OrderDTO getOrderById(String orderId);

    List<OrderDTO> getAllOrders();

    boolean checkItemsInStock(List<OrderDetailDTO> orderDetails);

}