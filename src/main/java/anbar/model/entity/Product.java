package anbar.model.entity;

import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.Os;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@SuperBuilder

public class Product implements Serializable {
    private int id;
    private String title;
    private Brand brand;
    private String model;
    private Os os;
    private boolean hasCharger;
    private boolean hasHeadset;
    private int price;
    private int count;
    private LocalDate manufactureDate;
}
