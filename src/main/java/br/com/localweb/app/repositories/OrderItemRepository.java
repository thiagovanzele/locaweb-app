package br.com.localweb.app.repositories;

import br.com.localweb.app.domain.orderItem.OrderItem;
import br.com.localweb.app.domain.pk.orderItemPK.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
