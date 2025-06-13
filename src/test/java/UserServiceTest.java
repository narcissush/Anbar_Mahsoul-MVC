import anbar.model.entity.User;
import anbar.model.entity.enums.Gender;
import anbar.model.service.UserService;

import java.time.LocalDate;

public class UserServiceTest {
    public static void main(String[] args) throws Exception {

        User user = User
                .builder()
                .id(2)
                .nationalId("55")
                .name("elmiraa")
                .family("sadeghi")
                .gender(Gender.men)
                .birthDate(LocalDate.now())
                .username("elmira")
                .password("elmira456")
                .build();

        //UserService.save(user);
        //UserService.edit(user);
        //UserService.delete(1);
        //System.out.println(UserService.findByNationalId("55"));
        //System.out.println(UserService.findById(2));
        System.out.println(UserService.findByNameAndFamily("elmiraa", "sadeghi"));

        //System.out.println(UserService.findByUserAndPassword("elmira", "elmira456"));
        // System.out.println(SupplierService.findByUsername("mohsen"));

        //System.out.println(UserService.findAll());
    }
}
