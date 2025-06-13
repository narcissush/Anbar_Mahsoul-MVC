import anbar.model.entity.Supplier;
import anbar.model.entity.enums.Gender;
import anbar.model.entity.enums.Party;
import anbar.model.entity.enums.Person;
import anbar.model.service.SupplierService;


import java.time.LocalDate;

public class SupplierServiceTest {
    public static void main(String[] args) throws Exception {
        Supplier supplier = Supplier
                .builder()
                .id(1)
                .personType(Person.Hoghoghi)
                .party_type(Party.Kharidar)
                .nationalId("")
                .postalCode("")
                .phoneNumber("")
                .mobileNumber("")
                .name("")
                .build();
        //StorekeeperService.save(storekeeper);
        //StorekeeperService.edit(storekeeper);
        //StorekeeperService.delete(1);
        //System.out.println(StorekeeperService.findByNationalId("0080386822"));
        //System.out.println(StorekeeperService.findByParty("0080386822"));
        //System.out.println(StorekeeperService.findByName("0080386822"));
        //System.out.println(StorekeeperService.findByPerson("0080386822"));

        //System.out.println(StorekeeperService.findAll());
    }
}
