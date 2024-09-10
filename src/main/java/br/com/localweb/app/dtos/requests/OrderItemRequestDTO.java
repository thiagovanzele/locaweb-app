package br.com.localweb.app.dtos.requests;
import br.com.localweb.app.domain.enums.ProductType;

public record OrderItemRequestDTO(Integer quantity, ProductType productType) {

}
