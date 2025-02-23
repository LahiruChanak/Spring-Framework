package lk.ijse.controller;

import lk.ijse.dto.OrderDTO;
import lk.ijse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin(origins = "*")
public class PlaceOrderController {

    private final OrderService orderService;

    @Autowired
    public PlaceOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public Boolean saveOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.saveOrder(orderDTO);
    }

    @GetMapping("/getAll")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/search/{code}")
    public OrderDTO getOrderByCode(@PathVariable String code) {
        return orderService.getOrderByCode(code);
    }
}