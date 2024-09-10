package br.com.localweb.app.repositories;

import br.com.localweb.app.domain.order.Order;
import br.com.localweb.app.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

}
