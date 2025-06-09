package anbar.model.repository;


import anbar.model.entity.Storekepper;
import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.Gender;
import anbar.model.entity.enums.Os;
import anbar.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StorekepperRepository implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public StorekepperRepository() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getconnection();
    }
    public int nextId() throws SQLException {
        preparedStatement = connection.prepareStatement("select storekepper_seq.nextval from DUAL");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("nextval");
    }

    public void save(Storekepper storekepper) throws SQLException {

        preparedStatement = connection.prepareStatement("insert into storekeppers values (?,?,?,?,?,?,?,?,?)");

        preparedStatement.setInt(1, storekepper.getId());
        preparedStatement.setString(2, storekepper.getNationalId());
        preparedStatement.setString(3, storekepper.getName());
        preparedStatement.setString(4, storekepper.getFamily());
        preparedStatement.setString(5, storekepper.getGender().name());
        preparedStatement.setDate(6, Date.valueOf(storekepper.getBirthDate()));
        preparedStatement.setString(7, storekepper.getPhoneNumber());
        preparedStatement.setString(8, storekepper.getUsername());
        preparedStatement.setString(9, storekepper.getPassword());
        preparedStatement.execute();

    }

    public void edit(Storekepper storekepper) throws SQLException {
        preparedStatement = connection.prepareStatement("update storekeppers set NATIONALID=?,NAME=?,FAMILY=?,GENDER=?,BIRTH_DATE=?,PHONE_NUMBER=?,USERNAME=?,PASSWORD=? where id = ?");

        preparedStatement.setInt(1, storekepper.getId());
        preparedStatement.setString(2, storekepper.getNationalId());
        preparedStatement.setString(3, storekepper.getName());
        preparedStatement.setString(4, storekepper.getFamily());
        preparedStatement.setString(5, storekepper.getGender().name());
        preparedStatement.setDate(6, Date.valueOf(storekepper.getBirthDate()));
        preparedStatement.setString(7, storekepper.getPhoneNumber());
        preparedStatement.setString(8, storekepper.getUsername());
        preparedStatement.setString(9, storekepper.getPassword());
        preparedStatement.setInt(1, storekepper.getId());
        preparedStatement.execute();

    }

    public void remove(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("delete from storekeppers where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    public List<Storekepper> getAllstorekeppers() throws SQLException {
        List<Storekepper> storekeppersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from storekeppers");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Storekepper storekepper = new  Storekepper();
            storekepper.setId(resultSet.getInt("id"));
            storekepper.setNationalId(resultSet.getString("national_id"));
            storekepper.setName(resultSet.getString("name"));
            storekepper.setFamily(resultSet.getString("family"));
            storekepper.setGender(Gender.valueOf(resultSet.getString("gender")));
            storekepper.setBirthDate(resultSet.getDate("birthdate").toLocalDate());
            storekepper.setPhoneNumber(resultSet.getString("mobile"));
            storekepper.setUsername(resultSet.getString("username"));
            storekepper.setPassword(resultSet.getString("password"));
            storekeppersList.add(storekepper);

            storekeppersList.add(storekepper);
        }
        return storekeppersList;
    }

    public List<Storekepper> getstorekeppersByBrand_Price(int price1,int price2) throws SQLException {
        List<Storekepper> storekeppersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from storekeppers where price between ? and ?");

        preparedStatement.setInt(1, price1);
        preparedStatement.setInt(2, price2);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Storekepper storekepper = new  Storekepper();
            storekepper.setId(resultSet.getInt("id"));
            storekepper.setNationalId(resultSet.getString("national_id"));
            storekepper.setName(resultSet.getString("name"));
            storekepper.setFamily(resultSet.getString("family"));
            storekepper.setGender(Gender.valueOf(resultSet.getString("gender")));
            storekepper.setBirthDate(resultSet.getDate("birthdate").toLocalDate());
            storekepper.setPhoneNumber(resultSet.getString("mobile"));
            storekepper.setUsername(resultSet.getString("username"));
            storekepper.setPassword(resultSet.getString("password"));

            storekeppersList.add(storekepper);
        }
        return storekeppersList;
    }



    @Override
    public void close() throws Exception {

        if (preparedStatement != null) {
            preparedStatement.close();
        }
        connection.close();
    }
}
}

