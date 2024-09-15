package br.com.localweb.app.services;

import br.com.localweb.app.domain.client.Client;
import br.com.localweb.app.domain.order.Order;
import br.com.localweb.app.domain.orderItem.OrderItem;
import br.com.localweb.app.domain.product.Product;
import br.com.localweb.app.dtos.OrderDTO;
import br.com.localweb.app.dtos.requests.OrderRequestDTO;
import br.com.localweb.app.exceptions.ResourceNotFoundException;
import br.com.localweb.app.repositories.ClientRepository;
import br.com.localweb.app.repositories.OrderItemRepository;
import br.com.localweb.app.repositories.OrderRepository;
import br.com.localweb.app.repositories.ProductRepository;
import br.com.localweb.app.services.validators.ValidateOrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    List<ValidateOrderInterface> validators;

    public Page<OrderDTO> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable).map(OrderDTO::new);
    }

    public OrderDTO findById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Order.class, id));
        return new OrderDTO(order);
    }

    public OrderDTO insert(OrderRequestDTO data) {
        validators.forEach(validator -> validator.validateOrder(data));
        Client client = clientRepository.findById(data.clientID()).orElseThrow(() -> new ResourceNotFoundException(Client.class, data.clientID()));
        Order order = new Order(client, new ArrayList<>());

        List<OrderItem> orderItems = data.orderItems().stream().map(orderItem -> {
            Product product = productRepository.findByName(orderItem.productType());
            if (product == null) {
                throw new RuntimeException("Product not found: " + orderItem.productType());
            }
            return new OrderItem(order, product, orderItem.quantity());
        }).collect(Collectors.toList());


        orderRepository.save(order);

        orderItems.forEach(orderItem -> {
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        });

        order.setOrderItems(orderItems);
        order.setTotal();
        orderRepository.save(order);

        invoiceService.insert(order);

        return new OrderDTO(order);
    }


}
