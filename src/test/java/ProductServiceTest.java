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
                .brand(Brand.Huawei)
                .model("Huawei16")
                .os(Os.Android)
                .price(750)
                .count(80)
                .hasHeadset(false)
                .hasCharger(false)
                .manufactureDate(LocalDate.now())
                .build();
//        ProductService.save(product);
//        ProductService.edit(product);
//       ProductService.delete(1);
//        System.out.println(ProductService.findByTitle("mobile"));
//        System.out.println(ProductService.findByPrice(700,800));

//        System.out.println(ProductService.findAll());
    }

}
