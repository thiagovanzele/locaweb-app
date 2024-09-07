package br.com.localweb.app.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AdressDTO(@JsonAlias("cep") String zipCode,
                        @JsonAlias("logradouro") String street,
                        @JsonAlias("bairro") String neighborhood,
                        @JsonAlias("localidade") String city,
                        @JsonAlias("estado")String state, String number) {
}
