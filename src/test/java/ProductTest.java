import anbar.model.entity.Product;
import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.Os;
import anbar.service.ProductService;

import java.time.LocalDate;

public class ProductTest {

    public static void main(String[] args) throws Exception {
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
        //ProductService.edit(product,1);
        //ProductService.delete(3);
        //System.out.println(ProductService.findByPrice(900,1500));

        System.out.println(ProductService.findAll());
    }

}
