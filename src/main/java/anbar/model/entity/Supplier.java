package anbar.model.entity;
import anbar.model.entity.enums.Person;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor

public class Supplier implements Serializable {
    private int id;
    private Person personType;
    private String supplierName;
    private String nationalId;
    private String postalCode;
    private String phoneNumber;
    private String mobileNumber;
}
