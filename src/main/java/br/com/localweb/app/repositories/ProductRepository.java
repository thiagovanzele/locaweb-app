package br.com.localweb.app.repositories;

import br.com.localweb.app.domain.enums.ProductType;
import br.com.localweb.app.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    public Product findByName(ProductType type);
}
