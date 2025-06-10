package anbar.model.repository;


import anbar.model.entity.Storekeeper;
import anbar.model.entity.enums.Gender;
import anbar.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StorekeeperRepository implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public StorekeeperRepository() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getconnection();
    }
    public int nextId() throws SQLException {
        preparedStatement = connection.prepareStatement("select storekeeper_seq.nextval from DUAL");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("nextval");
    }

    public void save(Storekeeper storekeeper) throws SQLException {

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

    public void edit(Storekeeper storekeeper,int i) throws SQLException {
        preparedStatement = connection.prepareStatement("update storekeepers set NATIONALID=?,NAME=?,FAMILY=?,GENDER=?,BIRTH_DATE=?,PHONE_NUMBER=?,USERNAME=?,PASSWORD=? where id = ?");
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
            Storekeeper storekeeper = Storekeeper
                    .builder()
                    .id(resultSet.getInt("id"))
                    .nationalId(resultSet.getString("nationalid"))
                    .name(resultSet.getString("name"))
                    .family(resultSet.getString("family"))
                    .gender(Gender.valueOf(resultSet.getString("gender")))
                    .birthDate(resultSet.getDate("birth_date").toLocalDate())
                    .phoneNumber(resultSet.getString("phone_number"))
                    .username(resultSet.getString("username"))
                    .password(resultSet.getString("password"))
                    .build();

            storekeepersList.add(storekeeper);
        }
        return storekeepersList;
    }

    public List<Storekeeper> findByNationalId(String nationalId) throws SQLException {
        List<Storekeeper> storekeepersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from storekeepers where nationalid=?");
        preparedStatement.setString(1, nationalId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Storekeeper storekeeper = Storekeeper
                    .builder()
                    .id(resultSet.getInt("id"))
                    .nationalId(resultSet.getString("nationalid"))
                    .name(resultSet.getString("name"))
                    .family(resultSet.getString("family"))
                    .gender(Gender.valueOf(resultSet.getString("gender")))
                    .birthDate(resultSet.getDate("birth_date").toLocalDate())
                    .phoneNumber(resultSet.getString("phone_number"))
                    .username(resultSet.getString("username"))
                    .password(resultSet.getString("password"))
                    .build();

            storekeepersList.add(storekeeper);
        }
        return storekeepersList;
    }

    public List<Storekeeper> findByNameAndFamily(String name,String family) throws SQLException {
        List<Storekeeper> storekeepersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from storekeepers where name LIKE ? and family LIKE ?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, family);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Storekeeper storekeeper = Storekeeper
                    .builder()
                    .id(resultSet.getInt("id"))
                    .nationalId(resultSet.getString("nationalid"))
                    .name(resultSet.getString("name"))
                    .family(resultSet.getString("family"))
                    .gender(Gender.valueOf(resultSet.getString("gender")))
                    .birthDate(resultSet.getDate("birth_date").toLocalDate())
                    .phoneNumber(resultSet.getString("phone_number"))
                    .username(resultSet.getString("username"))
                    .password(resultSet.getString("password"))
                    .build();

            storekeepersList.add(storekeeper);
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


