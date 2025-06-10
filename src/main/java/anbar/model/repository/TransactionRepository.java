package anbar.model.repository;


import anbar.model.entity.Transaction;
import anbar.model.entity.enums.Brand;
import anbar.tools.ConnectionProvider;
import anbar.tools.EntityMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public TransactionRepository() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getconnection();
    }

    public void save(Transaction transaction) throws SQLException {
        preparedStatement = connection.prepareStatement("select transaction_seq.nextval from dual");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        transaction.setId(resultSet.getInt("nextval"));

        preparedStatement = connection.prepareStatement("insert into transactions (id, storekeeper_id, product_id)values (?, ?, ?)");
        preparedStatement.setInt(1, transaction.getId());
        preparedStatement.setInt(2, transaction.getStorekeeper().getId());
        preparedStatement.setInt(3, transaction.getProduct().getId());
        preparedStatement.execute();
    }

    public void edit(Transaction transaction) throws SQLException {
        preparedStatement = connection.prepareStatement("update transactions set storekeeper_id=?, product_id=?, transaction_type=?,quantity=?, transaction_date=? where id=?");
        preparedStatement.setInt(1, transaction.getStorekeeper().getId());
        preparedStatement.setInt(2, transaction.getProduct().getId());
        preparedStatement.setString(3, transaction.getTransaction_type().name());
        preparedStatement.setInt(4, transaction.getQuantity());
        preparedStatement.setDate(3, transaction.getTransaction_date() == null? null: Date.valueOf(transaction.getTransaction_date()));
        preparedStatement.setInt(5, transaction.getId());
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
        preparedStatement = connection.prepareStatement("select * from transaction_report");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            transactionList.add(EntityMapper.transactionMapper(resultSet));
        }
        return transactionList;
    }

    public List<Transaction> findByProductBrand(Brand brand) throws SQLException {
        List<Transaction> transactionList = new ArrayList<>();
        connection = ConnectionProvider.getConnectionProvider().getconnection();
        preparedStatement = connection.prepareStatement("select * from transaction_report where brand=?");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            transactionList.add(EntityMapper.transactionMapper(resultSet));
        }
        return transactionList;
    }

    public List<Transaction> findByStoreKeeperNameAndFamily(String name,String family) throws SQLException {
        List<Transaction> transactionList = new ArrayList<>();
        connection = ConnectionProvider.getConnectionProvider().getconnection();
        preparedStatement = connection.prepareStatement("select * from transaction_report where storekeeper_name like ? and family_name like ?");
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