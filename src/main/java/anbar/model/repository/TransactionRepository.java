package anbar.model.repository;


import anbar.model.entity.Transaction;
import anbar.model.entity.enums.Brand;
import anbar.tools.ConnectionProvider;
import anbar.tools.EntityMapper;

import java.sql.*;
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
        preparedStatement = connection.prepareStatement("insert into transactions  values (?, ?, ?,?, ?, ?)");
        preparedStatement.setInt(1, transaction.getId());
        preparedStatement.setInt(2, transaction.getProduct().getId());
        preparedStatement.setInt(3, transaction.getStorekeeper().getId());
        preparedStatement.setString(4, transaction.getTransaction_type().name());
        preparedStatement.setInt(5, transaction.getQuantity());
        preparedStatement.setTimestamp(6, transaction.getTransaction_dateTime() == null ? null : Timestamp.valueOf(transaction.getTransaction_dateTime()));
        preparedStatement.execute();
    }

    public void edit(Transaction transaction) throws SQLException {
        preparedStatement = connection.prepareStatement("update transactions set products_id=?, storekeepers_id=?, transaction_type=?,quantity=?, transaction_date=? where id=?");
        preparedStatement.setInt(1, transaction.getProduct().getId());
        preparedStatement.setInt(2, transaction.getStorekeeper().getId());
        preparedStatement.setString(3, transaction.getTransaction_type().name());
        preparedStatement.setInt(4, transaction.getQuantity());
        preparedStatement.setTimestamp(5, transaction.getTransaction_dateTime() == null ? null : Timestamp.valueOf(transaction.getTransaction_dateTime()));
        preparedStatement.setInt(6, transaction.getId());
        preparedStatement.execute();
    }

    public void delete(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "delete from transactions where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
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
    public List<Transaction> findById(int id) throws SQLException {
        List<Transaction> transactionList = new ArrayList<>();
        connection = ConnectionProvider.getConnectionProvider().getconnection();
        preparedStatement = connection.prepareStatement("select * from transactions_report where transaction_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            transactionList.add(EntityMapper.transactionMapper(resultSet));
        }
        return transactionList;
    }

    public List<Transaction> findByStoreKeeperNameAndFamily(String name, String family) throws SQLException {
        List<Transaction> transactionList = new ArrayList<>();
        connection = ConnectionProvider.getConnectionProvider().getconnection();
        preparedStatement = connection.prepareStatement("select * from transactions_report where storekeepers_name like ? and storekeepers_family like ?");
        preparedStatement.setString(1, name + "%");
        preparedStatement.setString(2, family + "%");
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