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
                .nationalId(resultSet.getString("national_id"))
                .name(resultSet.getString("name"))
                .family(resultSet.getString("family"))
                .gender(Gender.valueOf(resultSet.getString("gender")))
                .birthDate(resultSet.getDate("birth_date").toLocalDate())
                .phoneNumber(resultSet.getString("phone_number"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .build();
    }

    public static Product productMapper(ResultSet resultSet) throws SQLException {

        return Product.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
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

        Storekeeper storekeeper = Storekeeper
                .builder()
                .id(resultSet.getInt("storekeepers_id"))
                .nationalId(resultSet.getString("storekeepers_national_id"))
                .name(resultSet.getString("storekeepers_name"))
                .family(resultSet.getString("storekeepers_family"))
                .gender(Gender.valueOf(resultSet.getString("storekeepers_gender")))
                .birthDate(resultSet.getDate("storekeepers_birth_date").toLocalDate())
                .phoneNumber(resultSet.getString("storekeepers_phone_number"))
                .username(resultSet.getString("storekeepers_username"))
                .password(resultSet.getString("storekeepers_password"))
                .build();

        Product product =Product
                .builder()
                .id(resultSet.getInt("products_id"))
                .title(resultSet.getString("products_title"))
                .brand(Brand.valueOf(resultSet.getString("products_brand")))
                .model(resultSet.getString("products_model"))
                .os(Os.valueOf(resultSet.getString("products_os")))
                .price(resultSet.getInt("products_price"))
                .count(resultSet.getInt("products_count"))
                .hasHeadset(resultSet.getBoolean("products_has_headset"))
                .hasCharger(resultSet.getBoolean("products_has_charger"))
                .manufactureDate(resultSet.getDate("products_manufacture_date").toLocalDate())
                .build();

        return Transaction
                .builder()
                .id(resultSet.getInt("transaction_ID"))
                .product(product)
                .storekeeper(storekeeper)
                .transaction_type(Transaction_type.valueOf(resultSet.getString("transaction_type")))
                .quantity(resultSet.getInt("quantity"))
                .transaction_dateTime(resultSet.getTimestamp("transaction_date").toLocalDateTime())

                .build();
    }
}