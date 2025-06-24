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
        preparedStatement.setInt(2, transaction.getProductId());
        preparedStatement.setInt(3, transaction.getSupplierId());
        preparedStatement.setInt(4, transaction.getUserId());
        preparedStatement.setString(5, transaction.getTransactionType().name());
        preparedStatement.setInt(6, transaction.getQuantity());
        preparedStatement.setTimestamp(7, transaction.getTransactionDateTime() == null ? null : Timestamp.valueOf(transaction.getTransactionDateTime()));
        preparedStatement.execute();
    }

    public void edit(Transaction transaction) throws SQLException {
        preparedStatement = connection.prepareStatement("update transactions set products_id=?, SUPPLIERS_ID=?,USERS_ID=?,transaction_type=?,quantity=?, transaction_date=? where id=?");
        preparedStatement.setInt(1, transaction.getProductId());
        preparedStatement.setInt(2, transaction.getSupplierId());
        preparedStatement.setInt(3, transaction.getUserId());
        preparedStatement.setString(4, transaction.getTransactionType().name());
        preparedStatement.setInt(5, transaction.getQuantity());
        preparedStatement.setTimestamp(6, transaction.getTransactionDateTime() == null ? null : Timestamp.valueOf(transaction.getTransactionDateTime()));
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