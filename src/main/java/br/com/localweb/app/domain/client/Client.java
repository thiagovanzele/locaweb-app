package br.com.localweb.app.domain.client;

import br.com.localweb.app.domain.invoice.Invoice;
import br.com.localweb.app.domain.order.Order;
import jakarta.persistence.*;
import lombok.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String document;

    @OneToMany(mappedBy = "client")
    private List<Invoice> invoices = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();
}
