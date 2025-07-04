package anbar.model.service;

import anbar.model.entity.Transaction;
import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.TransactionType;
import anbar.model.repository.TransactionRepository;

import java.util.List;

public class TransactionService {
    public static void save(Transaction transaction) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            transactionRepository.save(transaction);
        }
    }

    public static void edit(Transaction transaction) throws Exception {

        try (TransactionRepository transactionRepository = new TransactionRepository()) {
          if (transactionRepository.findById(transaction.getId()) != null) {
                transactionRepository.edit(transaction);
            } else {
                throw new Exception("Product not found");
            }
        }
    }

    public static void delete(int id) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            if (transactionRepository.findById(id) != null) {
                transactionRepository.delete(id);
            } else {
                throw new Exception("Product not found");
            }
        }
    }
    public static Transaction findById(int id) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            return transactionRepository.findById(id);
        }
    }


    public static List<Transaction> findAll() throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            return transactionRepository.findAll();
        }
    }


    public static List<Transaction> findByFilters(Brand brand,TransactionType transactionType,String userName,String supplierName) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            return transactionRepository.findByFilters(brand,transactionType,userName,supplierName);
        }
    }



}
