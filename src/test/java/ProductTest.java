import anbar.model.entity.Product;
import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.Os;
import anbar.service.ProductService;

import java.time.LocalDate;

public class ProductTest {

    public static void main(String[] args) throws Exception {
        Product product = Product.builder()
                .id(4)
                .brand(Brand.Xiaomi)
                .model("Note")
                .os(Os.Android)
                .price(800)
                .count(90)
                .hasHeadset(true)
                .hasCharger(true)
                .manufactureDate(LocalDate.now())
                .build();
        //ProductService.save(product);
        //ProductService.edit(product,1);
        //ProductService.delete(3);
        System.out.println(ProductService.findByPrice(900,1500));

        //System.out.println(ProductService.findAll());
    }

}
