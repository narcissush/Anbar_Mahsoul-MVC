package anbar.model.service;

import anbar.model.entity.Product;
import anbar.model.repository.ProductRepository;

import java.util.List;

public class ProductService {
    public static void save(Product product) throws Exception {
        try(ProductRepository productRepository = new ProductRepository()){
            productRepository.save(product);
        }
    }

    public static void edit(Product product) throws Exception {
        try(ProductRepository productRepository = new ProductRepository()){
//            if (productRepository.findById(product.getId()) != null) {
                productRepository.edit(product);
//            }else{
//                throw new Exception("Product not found");   // ProductNotFoundException
//            }
        }
    }

    public static void delete(int id) throws Exception {
        try(ProductRepository productRepository = new ProductRepository()){
//            if (productRepository.findById(product.getId()) != null) {
                productRepository.delete(id);
//            }else{
//                throw new Exception("Product not found");   // ProductNotFoundException
//            }
        }
    }

    public static List<Product> findAll() throws Exception {
        try(ProductRepository ProductRepository = new ProductRepository()){
            return ProductRepository.findAll();
        }
    }

//    todo : findByTitle Like

    public static List<Product> findByPrice(int price1,int price2) throws Exception {
        try(ProductRepository productRepository = new ProductRepository()){
            return productRepository.findByPrice(price1,price2);
        }
    }

}