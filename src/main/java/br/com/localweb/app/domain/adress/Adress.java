package br.com.localweb.app.domain.adress;

import br.com.localweb.app.dtos.AdressDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Adress {

    private String zipCode;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String number;

    public Adress(AdressDTO data) {
        this.street = data.street();
        this.city = data.city();
        this.neighborhood = data.neighborhood();
        this.number = data.number();
        this.state = data.state();
        this.zipCode = data.zipCode();
    }
}
