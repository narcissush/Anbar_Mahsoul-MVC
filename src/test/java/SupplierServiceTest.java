import anbar.model.entity.Supplier;
import anbar.model.entity.enums.Person;
import anbar.model.service.SupplierService;

public class SupplierServiceTest {
    public static void main(String[] args) throws Exception {
        Supplier supplier = Supplier
                .builder()
                .id(10)
                .personType(Person.حقوقی)
                .nationalId("0010386822")
                .postalCode("00")
                .phoneNumber("00")
                .mobileNumber("00")
                .supplierName("n")
                .build();
        SupplierService.save(supplier);
        //StorekeeperService.edit(storekeeper);
        //StorekeeperService.delete(1);
        //System.out.println(StorekeeperService.findByNationalId("0080386822"));
        //System.out.println(StorekeeperService.findByParty("0080386822"));
        //System.out.println(StorekeeperService.findByName("0080386822"));
        //System.out.println(StorekeeperService.findByPerson("0080386822"));

        //System.out.println(SupplierService.findAll());
    }
}
