package anbar.model.entity;

import anbar.controller.AppState;
import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.Category;
import anbar.model.entity.enums.Os;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@SuperBuilder

public class Product implements Serializable {
    private int id;
    private Category category;
    private Brand brand;
    private String model;
    private Os os;
    private boolean hasCharger;
    private boolean hasHeadset;
    private String serialNumber;
    private int price;
    private int totalQuantity;

    public String productInfo() {
        return String.format(
                "%s (%s) - %s",
                AppState.product.getModel(), AppState.product.getBrand().name(), AppState.product.getSerialNumber()
        );
    }
}
