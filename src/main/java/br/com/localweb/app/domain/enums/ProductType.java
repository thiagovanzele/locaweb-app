package br.com.localweb.app.domain.enums;

public enum ProductType {

    WEB_HOSTING(100.00),
    CLOUD_HOSTING(200.00),
    E_COMMERCE(300.00);

    private final Double price;


    ProductType(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }
}
