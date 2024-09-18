package br.com.localweb.app.dtos.requests;
import br.com.localweb.app.domain.enums.ProductType;
import jakarta.validation.constraints.NotBlank;

public record OrderItemRequestDTO(@NotBlank(message = "Quantity cannot be zero.") Integer quantity, @NotBlank(message = "Product cannot be null.") ProductType productType) {

}
