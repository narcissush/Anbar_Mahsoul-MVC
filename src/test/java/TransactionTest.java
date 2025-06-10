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


        System.out.println(TransactionService.findAll());



        //TransactionService.save(Transaction);
        //TransactionService.edit(Transaction,3);
        //TransactionService.delete(3);
        //System.out.println(TransactionService.findByNationalId("0080386822"));
        //System.out.println(TransactionService.findByNameAndFamily("neda", "gorji"));

        //System.out.println(TransactionService.findAll());
    }
}
