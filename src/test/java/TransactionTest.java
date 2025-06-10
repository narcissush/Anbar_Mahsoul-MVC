import anbar.model.entity.Product;
import anbar.model.entity.Storekeeper;
import anbar.model.entity.Transaction;
import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.Gender;
import anbar.model.entity.enums.Os;
import anbar.model.entity.enums.Transaction_type;
import anbar.model.service.ProductService;
import anbar.model.service.StorekeeperService;
import anbar.model.service.TransactionService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionTest {
    public static void main(String[] args) throws Exception {
//        Product product = Product
//                .builder()
//                .id(4)
//                .title("mobile")
//                .brand(Brand.Apple)
//                .model("Iphone11")
//                .os(Os.iOS)
//                .price(800)
//                .count(40)
//                .hasHeadset(true)
//                .hasCharger(true)
//                .manufactureDate(LocalDate.now())
//                .build();
//
//        Storekeeper storekeeper = Storekeeper
//                .builder()
//                .id(1)
//                .nationalId("1122334400")
//                .name("narges")
//                .family("hajizadeh")
//                .gender(Gender.women)
//                .birthDate(LocalDate.now())
//                .phoneNumber("09121234567")
//                .username("narges")
//                .password("narges")
//                .build();

        Storekeeper storekeeper = StorekeeperService.findById(1);
        Product product = ProductService.findById(4);

        System.out.println(storekeeper);
        System.out.println(product);


        Transaction transaction = Transaction
                .builder()
                .id(9)
                .storekeeper(storekeeper)
                .product(product)
                .transaction_type(Transaction_type.OutBound)
                .quantity(1000)
                .transaction_dateTime(LocalDateTime.now())
                .build();

        System.out.println(transaction);

//        TransactionService.save(transaction);
//        TransactionService.edit(transaction);
        //TransactionService.delete(3);
        //System.out.println(TransactionService.findByNationalId("0080386822"));
        //System.out.println(TransactionService.findByNameAndFamily("neda", "gorji"));

        System.out.println(TransactionService.findAll());
    }
}
