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

    public static void edit(Product product,int id) throws Exception {
        try(ProductRepository productRepository = new ProductRepository()){
            productRepository.edit(product,id);
        }
    }

    public static void delete(int id) throws Exception {
        try(ProductRepository productRepository = new ProductRepository()){
            productRepository.delete (id);
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