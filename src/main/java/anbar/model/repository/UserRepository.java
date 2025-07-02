package anbar.model.repository;

import anbar.model.entity.User;
import anbar.tools.ConnectionProvider;
import anbar.tools.EntityMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;
    User loginUser = new User();

    public UserRepository() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getconnection();
    }

    public int nextId() throws SQLException {
        preparedStatement = connection.prepareStatement("select users_seq.nextval from DUAL");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("nextval");
    }

    public  void save(User user) throws SQLException {
        user.setId(nextId());
        preparedStatement = connection.prepareStatement("insert into users values (?,?,?,?,?,?,?,?)");
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getNationalId());
        preparedStatement.setString(3, user.getName());
        preparedStatement.setString(4, user.getFamily());
        preparedStatement.setString(5, user.getGender().name());
        preparedStatement.setDate(6, Date.valueOf(user.getBirthDate()));
        preparedStatement.setString(7, user.getUsername());
        preparedStatement.setString(8, user.getPassword());
        preparedStatement.execute();
    }


    public void edit(User user) throws SQLException {

        preparedStatement = connection.prepareStatement("update users set NATIONAL_ID=?,NAME=?,FAMILY=?,GENDER=?,BIRTH_DATE=?,USERNAME=?,PASSWORD=? where id = ?");
        preparedStatement.setString(1, user.getNationalId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getFamily());
        preparedStatement.setString(4, user.getGender().name());
        preparedStatement.setDate(5, Date.valueOf(user.getBirthDate()));
        preparedStatement.setString(6, user.getUsername());
        preparedStatement.setString(7, user.getPassword());
        preparedStatement.setInt(8, user.getId());
        preparedStatement.execute();


    }

    public void delete(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("delete from users where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    public User findById(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("select * from users where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return EntityMapper.userMapper(resultSet);
        } else {
            return null;
        }
    }

    public List<User> findAll() throws SQLException {
        List<User> usersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from users");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {

            usersList.add(EntityMapper.userMapper(resultSet));
        }
        return usersList;
    }

    public User findByNationalId(String nationalId) throws SQLException {
        User user = new User();
        preparedStatement = connection.prepareStatement("select * from users where national_id like ?");
        preparedStatement.setString(1, nationalId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return EntityMapper.userMapper(resultSet);
        } else {
            return null;
        }
    }

    public List<User> findByNameAndFamily(String name, String family) throws SQLException {
        List<User> usersList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from users where name LIKE ? and family LIKE ?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, family);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usersList.add(EntityMapper.userMapper(resultSet));
        }
        return usersList;
    }


    public User findByUsernameAndPassword(String username, String password) throws SQLException {

        preparedStatement = connection.prepareStatement("select * from users where username=? and password=?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            loginUser=EntityMapper.userMapper(resultSet);
            return loginUser;
        }else
        return null;
    }

    public User findByUsername(String username) throws SQLException {
        User user = new User();
        preparedStatement = connection.prepareStatement("select * from users where username=?");
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return EntityMapper.userMapper(resultSet);
        } else {
            return null;
        }
    }

    @Override
    public void close() throws Exception {

        if (preparedStatement != null) {
            preparedStatement.close();
        }
        connection.close();
    }
}
