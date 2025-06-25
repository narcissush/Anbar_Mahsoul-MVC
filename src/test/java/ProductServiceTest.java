import anbar.model.entity.Product;
import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.Category;
import anbar.model.entity.enums.Os;
import anbar.model.service.ProductService;

public class ProductServiceTest {

    public static void main(String[] args) throws Exception {
//        Product product = Product
//                .builder()
//                .id(1)
//                .category(Category.Laptop)
//                .brand(Brand.Samsung)
//                .model("")
//                .os(Os.Android)
//                .price(1000)
//                .quantity(5)
//                .hasHeadset(true)
//                .hasCharger(false)
//                .serialNumber("123")
//                .build();
//        ProductService.save(product);
//        ProductService.edit(product);
//       ProductService.delete(1);
//        System.out.println(ProductService.findByCategory("mobile"));
//        System.out.println(ProductService.findByPrice(700,800));
        ProductService.editQuantity(1,5,2);

        System.out.println(ProductService.findAll());
    }

}
