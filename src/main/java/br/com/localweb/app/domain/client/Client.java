package br.com.localweb.app.domain.client;

import br.com.localweb.app.domain.address.Address;
import br.com.localweb.app.domain.order.Order;
import br.com.localweb.app.dtos.ClientDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

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
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
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

    @Embedded
    private Address address;

    public Client(ClientDTO data) {
        this.document = data.document();
        this.name = data.name();
        this.address = data.adress();
    }
}
