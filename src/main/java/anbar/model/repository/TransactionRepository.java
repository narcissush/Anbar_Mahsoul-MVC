package anbar.model.repository;


import anbar.model.entity.Transaction;
import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.TransactionType;
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
        preparedStatement = connection.prepareStatement("insert into transactions  values (?,?,?,?,?,?,?)");
        preparedStatement.setInt(1, transaction.getId());
        preparedStatement.setInt(2, transaction.getProduct().getId());
        preparedStatement.setInt(3, transaction.getSupplier().getId());
        preparedStatement.setInt(4, transaction.getUser().getId());
        preparedStatement.setString(5, transaction.getTransactionType().name());
        preparedStatement.setInt(6, transaction.getTransactionQuantity());
        preparedStatement.setTimestamp(7, Timestamp.valueOf(transaction.getTransactionDate()));
        preparedStatement.execute();
    }

    public void edit(Transaction transaction) throws SQLException {
        preparedStatement = connection.prepareStatement("update transactions set products_id=?, SUPPLIERS_ID=?,USERS_ID=?,transaction_type=?,Transaction_QUANTITY=?, transaction_date=? where id=?");
        preparedStatement.setInt(1, transaction.getProduct().getId());
        preparedStatement.setInt(2, transaction.getSupplier().getId());
        preparedStatement.setInt(3, transaction.getUser().getId());
        preparedStatement.setString(4, transaction.getTransactionType().name());
        preparedStatement.setInt(5, transaction.getTransactionQuantity());
        preparedStatement.setTimestamp(7, Timestamp.valueOf(transaction.getTransactionDate()));
        preparedStatement.setInt(7, transaction.getId());
        preparedStatement.execute();
    }

    public void delete(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("delete from transactions where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }


    public List<Transaction> findAll() throws SQLException {
        String sql = "SELECT "
                + "t.id AS transaction_id, t.products_id, "
                + "p.category AS products_category, p.brand AS products_brand, p.model AS products_model, "
                + "p.os AS products_os, p.has_charger AS products_has_charger, p.has_headset AS products_has_headset, "
                + "p.serial_number AS products_serial_number, p.price AS products_price, p.TOTAL_QUANTITY AS products_total_Quantity, "
                + "t.suppliers_id, "
                + "s.person_type AS suppliers_person_type, s.name AS suppliers_name, s.national_id AS suppliers_national_id, "
                + "s.postalcode AS suppliers_postalcode, s.phone_number AS suppliers_phone_number, s.mobile_number AS suppliers_mobile_number, "
                + "t.users_id, "
                + "u.national_id AS users_national_id, u.name AS users_name, u.family AS users_family, u.gender AS users_gender, "
                + "u.birth_date AS users_birthDate, u.username AS users_username, u.password AS users_password, "
                + "t.transaction_type, t.transaction_quantity, t.transaction_date "
                + "FROM transactions t "
                + "JOIN products p ON t.products_id = p.id "
                + "JOIN suppliers s ON t.suppliers_id = s.id "
                + "JOIN users u ON t.users_id = u.id";

        List<Transaction> transactionList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            transactionList.add(EntityMapper.transactionMapper(resultSet));
        }
        return transactionList;
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

    public List<Transaction> findByFilters(Brand brand, TransactionType transactionType, String username, String supplierName) throws SQLException {
        List<Transaction> transactionList = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM transactions_report WHERE 1=1");
        List<Object> parameters = new ArrayList<>();

        if (brand != null) {
            queryBuilder.append(" AND PRODUCTS_BRAND = ?");
            parameters.add(brand.name());
        }
        if (transactionType != null) {
            queryBuilder.append(" AND TRANSACTION_TYPE = ?");
            parameters.add(transactionType.name());
        }
        if (username != null && !username.isEmpty()) {
            queryBuilder.append(" AND USERS_USERNAME = ?");
            parameters.add(username);
        }
        if (supplierName != null && !supplierName.isEmpty()) {
            queryBuilder.append(" AND SUPPLIERS_NAME = ?");
            parameters.add(supplierName);
        }
        connection = ConnectionProvider.getConnectionProvider().getconnection();
        preparedStatement = connection.prepareStatement(queryBuilder.toString());


        for (int i = 0; i < parameters.size(); i++) {
            preparedStatement.setObject(i + 1, parameters.get(i));
        }

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