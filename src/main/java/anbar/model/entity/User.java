package anbar.model.entity;

import anbar.model.entity.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@SuperBuilder
@NoArgsConstructor
public class User implements Serializable {
    private int id;
    private String nationalId;
    private String name;
    private String family;
    private Gender gender;
    private LocalDate birthDate;
    private String username;
    private String password;

    public String getFullName() {
        return name + " " + family;
    }
}
