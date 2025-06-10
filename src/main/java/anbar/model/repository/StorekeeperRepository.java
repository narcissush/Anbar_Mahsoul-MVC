package anbar.model.repository;


import anbar.model.entity.Storekeeper;
import anbar.model.entity.enums.Gender;
import anbar.tools.ConnectionProvider;
import anbar.tools.EntityMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StorekeeperRepository implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public StorekeeperRepository() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getconnection();
    }

    public int nextId() throws SQLException {
        preparedStatement = connection.prepareStatement("select storekeepers_seq.nextval from DUAL");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("nextval");
    }

    public void save(Storekeeper storekeeper) throws SQLException {
        storekeeper.setId(nextId());
        preparedStatement = connection.prepareStatement("insert into storekeepers values (?,?,?,?,?,?,?,?,?)");
        preparedStatement.setInt(1, storekeeper.getId());
        preparedStatement.setString(2, storekeeper.getNationalId());
        preparedStatement.setString(3, storekeeper.getName());
        preparedStatement.setString(4, storekeeper.getFamily());
        preparedStatement.setString(5, storekeeper.getGender().name());
        preparedStatement.setDate(6, Date.valueOf(storekeeper.getBirthDate()));
        preparedStatement.setString(7, storekeeper.getPhoneNumber());
        preparedStatement.setString(8, storekeeper.getUsername());
        preparedStatement.setString(9, storekeeper.getPassword());
        preparedStatement.execute();
    }

    public void edit(Storekeeper storekeeper) throws SQLException {
        preparedStatement = connection.prepareStatement("update storekeepers set NATIONAL_ID=?,NAME=?,FAMILY=?,GENDER=?,BIRTH_DATE=?,PHONE_NUMBER=?,USERNAME=?,PASSWORD=? where id = ?");
        preparedStatement.setString(1, storekeeper.getNationalId());
        preparedStatement.setString(2, storekeeper.getName());
        preparedStatement.setString(3, storekeeper.getFamily());
        preparedStatement.setString(4, storekeeper.getGender().name());
        preparedStatement.setDate(5, Date.valueOf(storekeeper.getBirthDate()));
        preparedStatement.setString(6, storekeeper.getPhoneNumber());
        preparedStatement.setString(7, storekeeper.getUsername());
        preparedStatement.setString(8, storekeeper.getPassword());
        preparedStatement.setInt(9, storekeeper.getId());
        preparedStatement.execute();

    }

    public void delete(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("delete from storekeepers where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    public List<Storekeeper> findAll() throws SQLException {
        List<Storekeeper> storekeepersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from storekeepers");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {

            storekeepersList.add(EntityMapper.storekeeperMapper(resultSet));
        }
        return storekeepersList;
    }


public Storekeeper findById(int id) throws SQLException {
    Storekeeper storekeeper = null;
    preparedStatement = connection.prepareStatement("select * from storekeepers where id=?");
    preparedStatement.setInt(1, id);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
        storekeeper = EntityMapper.storekeeperMapper(resultSet);
    }
    return storekeeper;
}

    public List<Storekeeper> findByNationalId(String nationalId) throws SQLException {
        List<Storekeeper> storekeepersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from storekeepers where national_id=?");
        preparedStatement.setString(1, nationalId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            storekeepersList.add(EntityMapper.storekeeperMapper(resultSet));
        }
        return storekeepersList;
    }

    public List<Storekeeper> findByNameAndFamily(String name, String family) throws SQLException {
        List<Storekeeper> storekeepersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from storekeepers where name LIKE ? and family LIKE ?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, family);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            storekeepersList.add(EntityMapper.storekeeperMapper(resultSet));
        }
        return storekeepersList;
    }

//    todo :  findByUsername, findByUserAndPassword
public List<Storekeeper> findByUsernameAndPassword(String username, String password) throws SQLException {
    List<Storekeeper> storekeepersList = new ArrayList<>();
    preparedStatement = connection.prepareStatement("select * from storekeepers where username=? and password=?");
    preparedStatement.setString(1, username);
    preparedStatement.setString(2, password);

    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
        storekeepersList.add(EntityMapper.storekeeperMapper(resultSet));
    }
    return storekeepersList;
}

    public List<Storekeeper> findByUsername(String username) throws SQLException {
        List<Storekeeper> storekeepersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from storekeepers where username=?");
        preparedStatement.setString(1, username);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            storekeepersList.add(EntityMapper.storekeeperMapper(resultSet));
        }
        return storekeepersList;
    }


    @Override
    public void close() throws Exception {

        if (preparedStatement != null) {
            preparedStatement.close();
        }
        connection.close();
    }
}


