package anbar.model.entity;

import anbar.model.entity.enums.Gender;
import jdk.nashorn.internal.runtime.Debug;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@SuperBuilder
@NoArgsConstructor

public class Storekepper implements Serializable {
    private int id;
    private String nationalId;
    private String name;
    private String family;
    private Gender gender;
    private LocalDate birthDate;
    private String phoneNumber;
    private String username;
    private String password;

    public static Debug builder() {
        return null;
    }
}
