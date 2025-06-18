package anbar.model.repository;

import anbar.model.entity.User;
import anbar.tools.ConnectionProvider;

import java.sql.*;

public class UserRegisterRepository implements AutoCloseable{

    private Connection connection;
    private static PreparedStatement preparedStatement;
    User loginUser = new User();

    public UserRegisterRepository() throws SQLException {
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

    @Override
    public void close() throws Exception {

        if (preparedStatement != null) {
            preparedStatement.close();
        }
        connection.close();
    }
}
