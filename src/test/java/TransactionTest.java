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
//                .build();
//
//        Storekeeper storekeeper = Storekeeper
//                .builder()
//                .id(1)
//                .build();

        Storekeeper storekeeper = StorekeeperService.findById(5);
        Product product = ProductService.findById(3);


        Transaction transaction = Transaction
                .builder()
                .id(2)
                .product(product)
                .storekeeper(storekeeper)
                .transaction_type(Transaction_type.InBound)
                .quantity(4000)
                .transaction_dateTime(LocalDateTime.now())
                .build();

        //TransactionService.save(transaction);
       //TransactionService.edit(transaction);
        //TransactionService.delete(2);
        //System.out.println(TransactionService.findByNationalId("0080386822"));
        System.out.println(TransactionService.findByProductBrand(Brand.Samsung));
        System.out.println(TransactionService.findByStoreKeeperNameAndFamily("narges","hajizadeh"));


        //System.out.print(TransactionService.findAll());
    }
}
