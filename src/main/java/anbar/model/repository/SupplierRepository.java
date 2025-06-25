package anbar.model.repository;


import anbar.model.entity.Supplier;
import anbar.tools.ConnectionProvider;
import anbar.tools.EntityMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepository implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public SupplierRepository() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getconnection();
    }

    public int nextId() throws SQLException {
        preparedStatement = connection.prepareStatement("select suppliers_seq.nextval from DUAL");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("nextval");
    }

    public void save(Supplier supplier) throws SQLException {
        supplier.setId(nextId());
        preparedStatement = connection.prepareStatement("insert into suppliers values (?,?,?,?,?,?,?)");
       preparedStatement.setInt(1, supplier.getId());
        preparedStatement.setString(2, supplier.getName());
        preparedStatement.setString(3,supplier.getPersonType().name());
        preparedStatement.setString(4,supplier.getNationalId());
        preparedStatement.setString(5,supplier.getPostalCode());
        preparedStatement.setString(6,supplier.getPhoneNumber());
        preparedStatement.setString(7,supplier.getMobileNumber());
        preparedStatement.execute();
    }

    public void edit(Supplier supplier) throws SQLException {
        preparedStatement = connection.prepareStatement("update suppliers set NAME=?,PERSON_TYPE=?,NATIONAL_ID=?,POSTALCODE=?,PHONE_NUMBER=?,MOBILE_NUMBER=? where id = ?");
        preparedStatement.setString(1, supplier.getName());
        preparedStatement.setString(2,supplier.getPersonType().name());
        preparedStatement.setString(3,supplier.getNationalId());
        preparedStatement.setString(4,supplier.getPostalCode());
        preparedStatement.setString(5,supplier.getPhoneNumber());
        preparedStatement.setString(6,supplier.getMobileNumber());
        preparedStatement.setInt(7, supplier.getId());
        preparedStatement.execute();

    }

    public void delete(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("delete from suppliers where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    public Supplier findById(int id) throws SQLException {
        Supplier supplier = null;
        preparedStatement = connection.prepareStatement("select * from suppliers where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            supplier = EntityMapper.supplierMapper(resultSet);
        }
        return supplier;
    }

    public List<Supplier> findAll() throws SQLException {
        List<Supplier> suppliersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from suppliers");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            suppliersList.add(EntityMapper.supplierMapper(resultSet));
        }
        return suppliersList;
    }


    public List<Supplier> findByNationalId(String nationalId) throws SQLException {
        List<Supplier> suppliersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from suppliers where national_id=?");
        preparedStatement.setString(1, nationalId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            suppliersList.add(EntityMapper.supplierMapper(resultSet));
        }
        return suppliersList;
    }

    public List<Supplier> findByName(String name) throws SQLException {
        List<Supplier> suppliersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from suppliers where name=?");
        preparedStatement.setString(1, name);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            suppliersList.add(EntityMapper.supplierMapper(resultSet));
        }
        return suppliersList;
    }

    public List<Supplier> findByPerson(String person) throws SQLException {
        List<Supplier> suppliersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from suppliers where Person_type=?");
        preparedStatement.setString(1, person);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            suppliersList.add(EntityMapper.supplierMapper(resultSet));
        }
        return suppliersList;
    }

    @Override
    public void close() throws Exception {

        if (preparedStatement != null) {
            preparedStatement.close();
        }
        connection.close();
    }
}


