package anbar.tools;

import anbar.model.entity.Product;
import anbar.model.entity.Supplier;
import anbar.model.entity.Transaction;
import anbar.model.entity.User;
import anbar.model.entity.enums.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityMapper {
    public static Supplier supplierMapper(ResultSet resultSet) throws SQLException {
        return Supplier
                .builder()
                .id(resultSet.getInt("id"))
                .personType(Person.valueOf(resultSet.getString("person_type")))
                .partyType(Party.valueOf(resultSet.getString("party_type")))
                .nationalId(resultSet.getString("national_id"))
                .postalCode(resultSet.getString("postalcode"))
                .phoneNumber(resultSet.getString("phone_number"))
                .mobileNumber(resultSet.getString("mobile_number"))
                .name(resultSet.getString("name"))
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
                .count(resultSet.getInt("count"))
                .hasHeadset(resultSet.getBoolean("has_headset"))
                .hasCharger(resultSet.getBoolean("has_charger"))
                .serialNumber(resultSet.getString("serial_number"))
                .build();
    }

    public static Transaction transactionMapper(ResultSet resultSet) throws SQLException {

        Supplier supplier = Supplier
                .builder()
                .id(resultSet.getInt("id"))
                .personType(Person.valueOf(resultSet.getString("person_type")))
                .partyType(Party.valueOf(resultSet.getString("party_type")))
                .name(resultSet.getString("name"))
                .nationalId(resultSet.getString("national_id"))
                .postalCode(resultSet.getString("postalcode"))
                .phoneNumber(resultSet.getString("phone_number"))
                .mobileNumber(resultSet.getString("mobile_number"))
                .build();

        Product product =Product
                .builder()
                .id(resultSet.getInt("id"))
                .category(Category.valueOf(resultSet.getString("category")))
                .brand(Brand.valueOf(resultSet.getString("brand")))
                .model(resultSet.getString("model"))
                .os(Os.valueOf(resultSet.getString("os")))
                .price(resultSet.getInt("price"))
                .count(resultSet.getInt("count"))
                .hasHeadset(resultSet.getBoolean("has_headset"))
                .hasCharger(resultSet.getBoolean("has_charger"))
                .serialNumber(resultSet.getString("serial_number"))
                .build();

        User user = User
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

        return Transaction
                .builder()
                .id(resultSet.getInt("transaction_ID"))
                .product(product)
                .supplier(supplier)
                .user(user)
                .transaction_type(Transaction_type.valueOf(resultSet.getString("transaction_type")))
                .quantity(resultSet.getInt("quantity"))
                .transaction_dateTime(resultSet.getTimestamp("transaction_date").toLocalDateTime())

                .build();
    }

}
