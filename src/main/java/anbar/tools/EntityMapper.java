package anbar.tools;

import anbar.model.entity.*;
import anbar.model.entity.enums.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class EntityMapper {
    public static Supplier supplierMapper(ResultSet resultSet) throws SQLException {
        return Supplier
                .builder()
                .id(resultSet.getInt("id"))
                .supplierName(resultSet.getString("name"))
                .personType(Person.valueOf(resultSet.getString("person_type")))
                .nationalId(resultSet.getString("national_id"))
                .postalCode(resultSet.getString("postalcode"))
                .phoneNumber(resultSet.getString("phone_number"))
                .mobileNumber(resultSet.getString("mobile_number"))
                .build();

    }

    public static User userMapper(ResultSet resultSet) throws SQLException {
    return User
            .builder()
            .id(resultSet.getInt("id"))
            .nationalId(resultSet.getString("national_id"))
            .name(resultSet.getString("name"))
            .family(resultSet.getString("family"))
            .gender(Gender.valueOf(resultSet.getString("gender")))
            .birthDate(resultSet.getDate("birth_date").toLocalDate())
            .username(resultSet.getString("username"))
            .password(resultSet.getString("password"))
            .build();
}

    public static Product productMapper(ResultSet resultSet) throws SQLException {

        return Product.builder()
                .id(resultSet.getInt("id"))
                .category(Category.valueOf(resultSet.getString("category")))
                .brand(Brand.valueOf(resultSet.getString("brand")))
                .model(resultSet.getString("model"))
                .os(Os.valueOf(resultSet.getString("os")))
                .price(resultSet.getInt("price"))
                .totalQuantity(resultSet.getInt("total_quantity"))
                .hasHeadset(resultSet.getBoolean("has_headset"))
                .hasCharger(resultSet.getBoolean("has_charger"))
                .serialNumber(resultSet.getString("serial_number"))
                .build();
    }



    public static Transaction transactionMapper(ResultSet resultSet) throws SQLException {

        Supplier supplier = Supplier
                .builder()
                .id(resultSet.getInt("suppliers_id"))
                .supplierName(resultSet.getString("suppliers_name"))
                .personType(Person.valueOf(resultSet.getString("suppliers_person_type")))
                .nationalId(resultSet.getString("suppliers_national_id"))
                .postalCode(resultSet.getString("suppliers_postalcode"))
                .phoneNumber(resultSet.getString("suppliers_phone_number"))
                .mobileNumber(resultSet.getString("suppliers_mobile_number"))
                .build();

        Product product = Product.builder()
                .id(resultSet.getInt("products_id"))
                .category(Category.valueOf(resultSet.getString("products_category")))
                .brand(Brand.valueOf(resultSet.getString("products_brand")))
                .model(resultSet.getString("products_model"))
                .os(Os.valueOf(resultSet.getString("products_os")))
                .price(resultSet.getInt("products_price"))
                .totalQuantity(resultSet.getInt("products_total_quantity"))
                .hasHeadset(resultSet.getBoolean("products_has_headset"))
                .hasCharger(resultSet.getBoolean("products_has_charger"))
                .serialNumber(resultSet.getString("products_serial_number"))
                .build();

        User user = User
                .builder()
                .id(resultSet.getInt("users_id"))
                .nationalId(resultSet.getString("users_national_id"))
                .name(resultSet.getString("users_name"))
                .family(resultSet.getString("users_family"))
                .gender(Gender.valueOf(resultSet.getString("users_gender")))
                .birthDate(resultSet.getDate("users_birthdate").toLocalDate())
                .username(resultSet.getString("users_username"))
                .password(resultSet.getString("users_password"))
                .build();

        Timestamp ts = resultSet.getTimestamp("transaction_date");
        LocalDateTime txDate = ts != null ? ts.toLocalDateTime() : null;

        return Transaction.builder()
                .id(resultSet.getInt("transaction_id"))
                .product(product)
                .supplier(supplier)
                .user(user)
                .transactionType(TransactionType.valueOf(resultSet.getString("transaction_type")))
                .transactionQuantity(resultSet.getInt("transaction_quantity"))
                .transactionDate(txDate)
                .build();

    }

}
