package anbar.tools;

import anbar.model.entity.Product;
import anbar.model.entity.Storekeeper;
import anbar.model.entity.Transaction;
import anbar.model.entity.enums.Brand;
import anbar.model.entity.enums.Gender;
import anbar.model.entity.enums.Os;
import anbar.model.entity.enums.Transaction_type;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityMapper {
    public static Storekeeper storekeeperMapper(ResultSet resultSet) throws SQLException {
        return Storekeeper
                .builder()
                .id(resultSet.getInt("id"))
                .nationalId(resultSet.getString("nationalid"))
                .name(resultSet.getString("name"))
                .family(resultSet.getString("family"))
                .gender(Gender.valueOf(resultSet.getString("gender")))
                .birthDate(resultSet.getDate("birthdate").toLocalDate())
                .phoneNumber(resultSet.getString("mobile"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .build();
    }

    public static Product productMapper(ResultSet resultSet) throws SQLException {

        return Product.builder()
                .id(resultSet.getInt("id"))
                .brand(Brand.valueOf(resultSet.getString("brand")))
                .model(resultSet.getString("model"))
                .os(Os.valueOf(resultSet.getString("os")))
                .price(resultSet.getInt("price"))
                .count(resultSet.getInt("count"))
                .hasHeadset(resultSet.getBoolean("has_headset"))
                .hasCharger(resultSet.getBoolean("has_charger"))
                .manufactureDate(resultSet.getDate("manufacture_date").toLocalDate())
                .build();
    }

    public static Transaction transactionMapper(ResultSet resultSet) throws SQLException {

        return Transaction
                .builder()
                .id(resultSet.getInt("transaction_ID"))
                .storekeeper(storekeeperMapper(resultSet))
                .product(productMapper(resultSet))
                .transaction_type(Transaction_type.valueOf(resultSet.getString("transaction_type")))
                .quantity(resultSet.getInt("quantity"))
                .transaction_date(resultSet.getDate("transaction_date").toLocalDate())

                .build();
    }
}