package br.com.localweb.app.domain.product;

import br.com.localweb.app.domain.enums.ProductType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_product")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Double value;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    public Product(ProductType type) {
        this.type = type;
        this.value = type.getPrice();
    }


}
