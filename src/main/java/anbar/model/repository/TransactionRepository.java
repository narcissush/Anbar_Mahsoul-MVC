package anbar.model.repository;


import anbar.model.entity.Storekepper;
import anbar.model.entity.Transaction;
import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.Gender;
import anbar.model.entity.enums.Os;
import anbar.model.entity.enums.Transaction_type;
import anbar.tools.ConnectionProvider;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public TransactionRepository() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getconnection();
    }
    public int nextId() throws SQLException {
        preparedStatement = connection.prepareStatement("select transaction_seq.nextval from DUAL");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("nextval");
    }

    private  int id;
    private  int product_id;
    private  int storekepper_id;

    private Transaction_type transaction_type;
    private  int quantity ;
    private LocalDate transaction_date;

    public void save(Transaction transaction) throws SQLException {

        preparedStatement = connection.prepareStatement("insert into transactions values (?,?,?,?,?,?)");

        preparedStatement.setInt(1,transaction.getId());
        preparedStatement.setInt(2, transaction.getProduct_id());
        preparedStatement.setInt(3, transaction.getStorekepper_id());
        preparedStatement.setString(4, transaction.getTransaction_type().name());
        preparedStatement.setInt(5, transaction.getQuantity());
        preparedStatement.setDate(6, Date.valueOf(transaction.getTransaction_date()));

        preparedStatement.execute();

    }

    public void edit(Transaction transaction) throws SQLException {
        preparedStatement = connection.prepareStatement("update transactions set product_id=?,storekepper_id=?,transaction_type=?,quantity=?,transaction_date=? where id=?");


        preparedStatement.setInt(1, transaction.getProduct_id());
        preparedStatement.setInt(2, transaction.getStorekepper_id());
        preparedStatement.setString(3, transaction.getTransaction_type().name());
        preparedStatement.setInt(4, transaction.getQuantity());
        preparedStatement.setDate(5, Date.valueOf(transaction.getTransaction_date()));
        preparedStatement.setInt(6,transaction.getId());

        preparedStatement.execute();

    }

    public void remove(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("delete from transactions where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    public List<Transaction> getAlltransactions() throws SQLException {
        List<Transaction> transactionsList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from transactions");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Transaction transaction = Transaction.builder()
                    .id(resultSet.getInt("id"))
                    .product_id(resultSet.getInt("product_id"))
                    .storekepper_id(resultSet.getInt("storekepper_id"))
                    .transaction_type(transaction_type.valueOf(resultSet.getString("transaction_type")))
                    .quantity(resultSet.getInt("quantity"))
                    .transaction_date(resultSet.getDate("transaction_date").toLocalDate())

                    .build();
            transactionsList.add(transaction);
        }
        return transactionsList;
    }

    public List<Transaction> gettransactionsByTransaction_type(Transaction_type transaction_type) throws SQLException {
        List<Transaction> transactionsList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from transactions where transaction_type=?");

        preparedStatement.setString(1, transaction_type.name());


        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Transaction transaction = Transaction.builder()
                    .id(resultSet.getInt("id"))
                    .product_id(resultSet.getInt("product_id"))
                    .storekepper_id(resultSet.getInt("storekepper_id"))
                    .transaction_type(transaction_type.valueOf(resultSet.getString("transaction_type")))
                    .quantity(resultSet.getInt("quantity"))
                    .transaction_date(resultSet.getDate("transaction_date").toLocalDate())

                    .build();
            transactionsList.add(transaction);
        }
        return transactionsList;
    }



    @Override
    public void close() throws Exception {

        if (preparedStatement != null) {
            preparedStatement.close();
        }
        connection.close();
    }
}