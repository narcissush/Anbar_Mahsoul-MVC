package anbar.model.entity;

import anbar.model.entity.enums.Gender;

import java.io.Serializable;
import java.time.LocalDate;

public class StoreKepper implements Serializable {
    private int id;
    private String nationalId;
    private String name;
    private String family;
    private Gender gender;
    private LocalDate birthDate;
    private String phoneNumber;
    private String username;
    private String password;
}
