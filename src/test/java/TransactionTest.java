import anbar.model.entity.Product;
import anbar.model.entity.Supplier;
import anbar.model.entity.Transaction;
import anbar.model.entity.User;
import anbar.model.entity.enums.TransactionType;
import anbar.model.service.ProductService;
import anbar.model.service.SupplierService;
import anbar.model.service.UserService;

import java.time.LocalDateTime;

public class TransactionTest {
    public static void main(String[] args) throws Exception {
//        Product product = Product
//                .builder()
//                .id(4)
//                .build();
//
//         Supplier supplier = Supplier
//                .builder()
//                .id(1)
//                .build();

        Supplier supplier = SupplierService.findById(5);
        Product product = ProductService.findById(3);
        User user = UserService.findById(1);


//        Transaction transaction = Transaction
//                .builder()
//                .id(2)
//                .product(1)
//                .supplier(3)
//                .user(user)
//                .transaction_type(TransactionType.خرید)
//                .quantity(4000)
//                .transaction_dateTime(LocalDateTime.now())
//                .build();

        //TransactionService.save(transaction);
       //TransactionService.edit(transaction);
        //TransactionService.delete(2);
        //System.out.println(TransactionService.findByNationalId("0080386822"));
        //System.out.println(TransactionService.findByProductBrand(""));
        //System.out.print(TransactionService.findAll());
    }
}
