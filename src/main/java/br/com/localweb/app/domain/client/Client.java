package br.com.localweb.app.domain.client;

import br.com.localweb.app.domain.invoice.Invoice;
import br.com.localweb.app.domain.order.Order;
import br.com.localweb.app.dtos.ClientDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

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
    private Boolean isActive;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Client(ClientDTO data) {
        this.document = data.document();
        this.name = data.name();
    }
}
