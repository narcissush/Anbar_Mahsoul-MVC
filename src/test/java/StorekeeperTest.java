import anbar.model.entity.Storekeeper;
import anbar.model.entity.enums.Gender;
import anbar.service.StorekeeperService;



import java.time.LocalDate;

public class StorekeeperTest {
    public static void main(String[] args) throws Exception {
        Storekeeper storekeeper = Storekeeper
                .builder()
                .id(3)
                .nationalId("1122334455")
                .name("neda")
                .family("gorji")
                .gender(Gender.women)
                .birthDate(LocalDate.now())
                .phoneNumber("09129331219")
                .username("neda")
                .password("neda123")
                .build();
       //StorekeeperService.save(storekeeper);
        //StorekeeperService.edit(storekeeper,3);
        //StorekeeperService.delete(3);
        //System.out.println(StorekeeperService.findByNationalId("0080386822"));
        System.out.println(StorekeeperService.findByNameAndFamily("neda", "gorji"));

        //System.out.println(StorekeeperService.findAll());
    }
}
