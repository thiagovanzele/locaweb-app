package br.com.localweb.app.services;

import br.com.localweb.app.domain.address.Address;
import br.com.localweb.app.dtos.AdressDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService {

    public Address findAddress(String zipCode, String number) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + zipCode + "/json/";

        ResponseEntity<AdressDTO> response = restTemplate.getForEntity(url, AdressDTO.class);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            AdressDTO adressDTO = response.getBody();
            Address adress = new Address(adressDTO);
            adress.setNumber(number);
            return adress;

        } else {
            throw new RuntimeException("ZipCode invalid.");
        }
    }
}
