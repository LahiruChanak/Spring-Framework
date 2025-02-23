package lk.ijse.service.impl;

import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.OrderDetailDTO;
import lk.ijse.entity.OrderDetail;
import lk.ijse.entity.Orders;
import lk.ijse.repository.OrderDetailsRepo;
import lk.ijse.repository.OrdersRepo;
import lk.ijse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrdersRepo ordersRepo;
    private final OrderDetailsRepo orderDetailsRepo;

    @Autowired
    public OrderServiceImpl(OrdersRepo ordersRepo, OrderDetailsRepo orderDetailsRepo) {
        this.ordersRepo = ordersRepo;
        this.orderDetailsRepo = orderDetailsRepo;
    }

    @Override
    @Transactional
    public boolean saveOrder(OrderDTO orderDTO) {
        try {
            // Validation
            if (orderDTO == null || orderDTO.getOrderId() == null || orderDTO.getOrderDetails() == null
                    || orderDTO.getOrderDetails().isEmpty()) {
                return false;
            }

            if (ordersRepo.existsById(orderDTO.getOrderId())) {
                return false;
            }

            // Create Order
            Orders order = new Orders();
            order.setOrderId(orderDTO.getOrderId());
            order.setDateTime(orderDTO.getDateTime() != null ?
                    orderDTO.getDateTime() :
                    LocalDateTime.now().toString());
            order.setCustomerId(orderDTO.getCustomerId());

            // Create OrderDetails
            List<OrderDetail> orderDetails = orderDTO.getOrderDetails().stream()
                    .map(detail -> createOrderDetail(detail, order))
                    .collect(Collectors.toList());

            order.setOrderDetails(orderDetails);
            ordersRepo.save(order);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save order: " + e.getMessage());
        }
    }

    private OrderDetail createOrderDetail(OrderDetailDTO dto, Orders order) {
        OrderDetail detail = new OrderDetail();
        detail.setItemCode(dto.getItemCode());
        detail.setQty(dto.getQty());
        detail.setSubTotal(dto.getSubTotal());
        detail.setOrder(order);
        return detail;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDTO getOrderByCode(String orderId) {
        return ordersRepo.findById(orderId)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> getAllOrders() {
        return ordersRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private OrderDTO mapToDTO(Orders order) {
        List<OrderDetailDTO> orderDetailDTOs = order.getOrderDetails().stream()
                .map(this::mapToDetailDTO)
                .collect(Collectors.toList());

        return new OrderDTO(
                order.getOrderId(),
                order.getDateTime(),
                order.getCustomerId(),
                orderDetailDTOs
        );
    }

    private OrderDetailDTO mapToDetailDTO(OrderDetail detail) {
        return new OrderDetailDTO(
                detail.getId(),
                detail.getItemCode(),
                detail.getQty(),
                detail.getSubTotal(),
                detail.getOrder().getOrderId()
        );
    }
}