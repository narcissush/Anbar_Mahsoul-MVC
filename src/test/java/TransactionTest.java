import anbar.model.entity.Product;
import anbar.model.entity.Supplier;
import anbar.model.entity.Transaction;
import anbar.model.entity.User;
import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.Person;
import anbar.model.entity.enums.TransactionType;
import anbar.model.service.ProductService;
import anbar.model.service.SupplierService;
import anbar.model.service.TransactionService;
import anbar.model.service.UserService;

import java.time.LocalDateTime;

public class TransactionTest {
    public static void main(String[] args) throws Exception {


//        Product product = new Product();
//        product=ProductService.findById(1);
//
//        Supplier supplier = new Supplier();
//        supplier=SupplierService.findById(2);
//
//
//        User user = new User();
//        user=UserService.findById(1);
//
//        Transaction transaction = Transaction
//                .builder()
//                .id(1)
//                .product(product)
//                .supplier(supplier)
//                .user(user)
//                .transactionType(TransactionType.خرید)
//                .transactionQuantity(5)
//                .transactionDate(LocalDateTime.now())
//                .build();

        //TransactionService.save(transaction);

        System.out.println(TransactionService.findByFilters(Brand.Apple,null,null,null));
        //System.out.print(TransactionService.findAll());
    }
}
