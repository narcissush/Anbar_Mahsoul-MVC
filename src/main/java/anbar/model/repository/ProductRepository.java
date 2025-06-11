package anbar.model.repository;


import anbar.model.entity.Product;
import anbar.tools.ConnectionProvider;
import anbar.tools.EntityMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public ProductRepository() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getconnection();
    }

    public int nextId() throws SQLException {
        preparedStatement = connection.prepareStatement("select products_seq.nextval from DUAL");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("nextval");
    }

    public void save(Product product) throws SQLException {
        product.setId(nextId());
        preparedStatement = connection.prepareStatement("insert into Products values (?,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setInt(1, product.getId());
        preparedStatement.setString(2, product.getTitle());
        preparedStatement.setString(3, product.getBrand().name());
        preparedStatement.setString(4, product.getModel());
        preparedStatement.setString(5, product.getOs().name());
        preparedStatement.setBoolean(6, product.isHasCharger());
        preparedStatement.setBoolean(7, product.isHasHeadset());
        preparedStatement.setInt(8, product.getPrice());
        preparedStatement.setInt(9, product.getCount());
        preparedStatement.setDate(10, Date.valueOf(product.getManufactureDate()));
        preparedStatement.execute();

    }

    public void edit(Product product) throws SQLException {
        preparedStatement = connection.prepareStatement("update Products set title=?, brand=?,model=?,os=?,has_charger=?,has_headset=?,price=?,count=?,manufacture_date=? where id=?");
        preparedStatement.setString(1, product.getTitle());
        preparedStatement.setString(2, product.getBrand().name());
        preparedStatement.setString(3, product.getModel());
        preparedStatement.setString(4, product.getOs().name());
        preparedStatement.setBoolean(5, product.isHasCharger());
        preparedStatement.setBoolean(6, product.isHasHeadset());
        preparedStatement.setInt(7, product.getPrice());
        preparedStatement.setInt(8, product.getCount());
        preparedStatement.setDate(9, Date.valueOf(product.getManufactureDate()));
        preparedStatement.setInt(10, product.getId());
        preparedStatement.execute();

    }

    public void delete(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("delete from products where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    public Product findById(int id) throws SQLException {
        Product product = null;
        preparedStatement = connection.prepareStatement("select * from Products where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            product = EntityMapper.productMapper(resultSet);
        }
        return product;
    }

    public List<Product> findAll() throws SQLException {
        List<Product> productsList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from products");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {

            productsList.add(EntityMapper.productMapper(resultSet));
        }
        return productsList;
    }

    public List<Product> findByTitle(String title) throws SQLException {
        List<Product> productsList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from Products where TITLE Like ?");
        preparedStatement.setString(1, title + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            productsList.add(EntityMapper.productMapper(resultSet));
        }
        return productsList;
    }



    public List<Product> findByPrice(int price1, int price2) throws SQLException {
        List<Product> productsList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from Products where price between ? and ?");
        preparedStatement.setInt(1, price1);
        preparedStatement.setInt(2, price2);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            productsList.add(EntityMapper.productMapper(resultSet));
        }
        return productsList;
    }

    @Override
    public void close() throws Exception {

        if (preparedStatement != null) {
            preparedStatement.close();
        }
        connection.close();
    }
}

