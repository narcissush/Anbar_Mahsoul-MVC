import anbar.model.entity.Storekeeper;
import anbar.model.entity.enums.Gender;
import anbar.model.service.StorekeeperService;


import javax.sound.midi.Soundbank;
import java.time.LocalDate;

public class StorekeeperTest {
    public static void main(String[] args) throws Exception {
        Storekeeper storekeeper = Storekeeper
                .builder()
                .id(6)
                .nationalId("0040506070")
                .name("mohsen")
                .family("roshanai")
                .birthDate(LocalDate.of(1990, 1, 1))
                .gender(Gender.men)
                .phoneNumber("09123987298")
                .username("mohsen")
                .password("mohsen123")
                .build();
        //StorekeeperService.save(storekeeper);
        //StorekeeperService.edit(storekeeper);
        //StorekeeperService.delete(1);
        //System.out.println(StorekeeperService.findByNationalId("0080386822"));
        //System.out.println(StorekeeperService.findByNameAndFamily("mohsen", "roshanai"));
        //System.out.println(StorekeeperService.findByUserAndPassword("narges", "narges123"));
        System.out.println(StorekeeperService.findByUsername("mohsen"));

        //System.out.println(StorekeeperService.findAll());
    }
}
