import anbar.model.entity.Product;
import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.Os;
import anbar.model.service.ProductService;

import java.time.LocalDate;

public class ProductServiceTest {

    public static void main(String[] args) throws Exception {
        Product product = Product
                .builder()
                .id(3)
                .title("mobile")
                .brand(Brand.Apple)
                .model("Iphone11")
                .os(Os.iOS)
                .price(800)
                .count(40)
                .hasHeadset(true)
                .hasCharger(true)
                .manufactureDate(LocalDate.now())
                .build();
//        ProductService.save(product);
//        ProductService.edit(product);
        ProductService.delete(1);
        System.out.println(ProductService.findByTitle("mobile1"));
//        System.out.println(ProductService.findByPrice(8000,11000));

//        System.out.println(ProductService.findAll());
    }

}
