package anbar.model.service;

import anbar.controller.exceptions.OutOfProductException;
import anbar.model.entity.Product;
import anbar.model.entity.Transaction;
import anbar.model.entity.enums.TransactionType;
import anbar.model.repository.ProductRepository;
import anbar.model.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public static void save(Product product) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            productRepository.save(product);
        }
    }

    public static void edit(Product product) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            if (productRepository.findById(product.getId()) != null) {
                productRepository.edit(product);
            } else {
                throw new Exception("Product not found");
            }
        }
    }

    public static void editQuantity(int productId, int quantity, TransactionType transactionType) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            if (transactionType.equals(TransactionType.فروش)) {
                if (productRepository.findById(productId).getTotalQuantity() < quantity) {
                    throw new OutOfProductException();
                }
                quantity = -quantity;
            }
            productRepository.editQuantity(productId, quantity);
        }
    }

    public static void delete(int id) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            if (productRepository.findById(id) != null) {
                productRepository.delete(id);
            } else {
                throw new Exception("Product not found");
            }
        }
    }

    public static Product findById(int id) throws Exception {
        try (ProductRepository ProductRepository = new ProductRepository()) {
            return ProductRepository.findById(id);
        }
    }

    public static List<Product> findAll() throws Exception {
        try (ProductRepository ProductRepository = new ProductRepository()) {
            return ProductRepository.findAll();
        }
    }

    public static List<Product> findByCategory(String category) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            return productRepository.findByCategory(category);
        }
    }

    public static List<Product> findByPrice(int price1, int price2) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            return productRepository.findByPrice(price1, price2);
        }
    }

    public static List<Product> findByBrand(String brand) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            return productRepository.findByBrand(brand);
        }
    }


}