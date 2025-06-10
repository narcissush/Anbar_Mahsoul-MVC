import anbar.model.entity.Product;
import anbar.model.entity.Storekeeper;
import anbar.model.entity.Transaction;
import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.Gender;
import anbar.model.entity.enums.Os;
import anbar.model.entity.enums.Transaction_type;
import anbar.service.ProductService;
import anbar.service.StorekeeperService;
import anbar.service.TransactionService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionTest {
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
        StorekeeperService.save(storekeeper);

        Product product = Product.builder()
                .id(3)
                .brand(Brand.Samsung)
                .model("Note11")
                .os(Os.Android)
                .price(1000)
                .count(22)
                .hasHeadset(true)
                .hasCharger(true)
                .manufactureDate(LocalDate.now())
                .build();
        ProductService.save(product);

        Transaction transaction = Transaction.builder()
                .id(1)
                .product(product)
                .storekeeper(storekeeper)
                .transaction_type(Transaction_type.InBound)
                .quantity(5)
                .transaction_date(LocalDateTime.now())
                .build();



        //TransactionService.save(Transaction);
        //TransactionService.edit(Transaction,3);
        //TransactionService.delete(3);
        //System.out.println(TransactionService.findByNationalId("0080386822"));
        System.out.println(TransactionService.findByNameAndFamily("neda", "gorji"));

        //System.out.println(TransactionService.findAll());
    }
}
