package br.com.localweb.app.controllers;

import br.com.localweb.app.domain.order.Order;
import br.com.localweb.app.dtos.OrderDTO;
import br.com.localweb.app.dtos.requests.OrderRequestDTO;
import br.com.localweb.app.services.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderDTO>> findAll(Pageable pageable) {
        Page<OrderDTO> orders = orderService.findAll(pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable UUID id) {
        OrderDTO orderDTO = orderService.findById(id);
        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> insert(@RequestBody OrderRequestDTO data) {
        OrderDTO orderDTO = orderService.insert(data);
        return ResponseEntity.ok(orderDTO);
    }
}
