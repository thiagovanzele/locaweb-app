package br.com.localweb.app.services;

import br.com.localweb.app.domain.adress.Adress;
import br.com.localweb.app.dtos.AdressDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdressService {

    public Adress findAdress(String zipCode, String number) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + zipCode + "/json/";

        ResponseEntity<AdressDTO> response = restTemplate.getForEntity(url, AdressDTO.class);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            AdressDTO adressDTO = response.getBody();
            Adress adress = new Adress(adressDTO);
            adress.setNumber(number);
            return adress;

        } else {
            throw new RuntimeException("ZipCode invalid.");
        }
    }
}
