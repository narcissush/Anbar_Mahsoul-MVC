package anbar.model.repository;


import anbar.model.entity.Transaction;
import anbar.model.entity.enums.Brand;
import anbar.tools.ConnectionProvider;
import anbar.tools.EntityMapper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public TransactionRepository() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getconnection();
    }

    public int nextId() throws SQLException {
        preparedStatement = connection.prepareStatement("select transactions_seq.nextval from DUAL");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("nextval");
    }

    public void save(Transaction transaction) throws SQLException {
        transaction.setId(nextId());
        preparedStatement = connection.prepareStatement("insert into transactions  values (?,?,?,?,?,?,?)");
        preparedStatement.setInt(1, transaction.getId());
        preparedStatement.setInt(2, transaction.getProduct().getId());
        preparedStatement.setInt(3, transaction.getSupplier().getId());
        preparedStatement.setInt(4, transaction.getUser().getId());
        preparedStatement.setString(5, transaction.getTransaction_type().name());
        preparedStatement.setInt(6, transaction.getQuantity());
        preparedStatement.setTimestamp(7, transaction.getTransaction_dateTime() == null ? null : Timestamp.valueOf(transaction.getTransaction_dateTime()));
        preparedStatement.execute();
    }

    public void edit(Transaction transaction) throws SQLException {
        preparedStatement = connection.prepareStatement("update transactions set products_id=?, SUPPLIERS_ID=?,USERS_ID, transaction_type=?,quantity=?, transaction_date=? where id=?");
        preparedStatement.setInt(1, transaction.getProduct().getId());
        preparedStatement.setInt(2, transaction.getSupplier().getId());
        preparedStatement.setInt(3, transaction.getUser().getId());
        preparedStatement.setString(4, transaction.getTransaction_type().name());
        preparedStatement.setInt(5, transaction.getQuantity());
        if (transaction.getTransaction_dateTime() != null) {
            preparedStatement.setTimestamp(6, Timestamp.valueOf(transaction.getTransaction_dateTime()));
        } else {
            // اگر نمی‌خوای تغییر بدی یا مقدار قبلی رو از DB بخونی
            preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now())); // یا مقدار قبلی رو از DB بگیر
        }
        preparedStatement.setInt(7, transaction.getId());
        preparedStatement.execute();
    }
    public void delete(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("delete from transactions where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
    public Transaction findById(int id) throws SQLException {
        Transaction transaction = new Transaction();
        connection = ConnectionProvider.getConnectionProvider().getconnection();
        preparedStatement = connection.prepareStatement("select * from transactions_report where transaction_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return EntityMapper.transactionMapper(resultSet);
        } else {
            return null;
        }
    }


    public List<Transaction> findAll() throws SQLException {
        List<Transaction> transactionList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from transactions_report");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            transactionList.add(EntityMapper.transactionMapper(resultSet));
        }
        return transactionList;
    }

    public List<Transaction> findByProductBrand(Brand brand) throws SQLException {
        List<Transaction> transactionList = new ArrayList<>();
        connection = ConnectionProvider.getConnectionProvider().getconnection();
        preparedStatement = connection.prepareStatement("select * from transactions_report where products_brand=?");
        preparedStatement.setString(1, brand.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            transactionList.add(EntityMapper.transactionMapper(resultSet));
        }
        return transactionList;
    }


    @Override
    public void close() throws Exception {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        connection.close();
    }
}