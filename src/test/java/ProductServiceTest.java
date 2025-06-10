import anbar.model.entity.Product;
import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.Os;
import anbar.model.service.ProductService;

import java.time.LocalDate;

public class ProductServiceTest {

    public static void main(String[] args) throws Exception {
//        Product product = Product
//                .builder()
//                .id(1)
//                .brand(Brand.Samsung)
//                .model("Note")
//                .os(Os.Android)
//                .price(900)
//                .count(90)
//                .hasHeadset(true)
//                .hasCharger(true)
//                .manufactureDate(LocalDate.now())
//                .build();
//        ProductService.save(product);
//        ProductService.edit(product);
        ProductService.delete(1);
//        System.out.println(ProductService.findByPrice(8000,11000));

        System.out.println(ProductService.findAll());
    }

}
