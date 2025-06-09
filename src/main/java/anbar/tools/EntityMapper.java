package anbar.tools;

import anbar.model.entity.Product;
import anbar.model.entity.Storekepper;
import anbar.model.entity.Transaction;
import jdk.nashorn.internal.runtime.Debug;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityMapper {
    public static Storekepper storekepperMapper(ResultSet resultSet) throws SQLException {
        return storekepper
                .builder()
                .id(resultSet.getInt("ID"))
                .name(resultSet.getString("NAME"))
                .family(resultSet.getString("FAMILY"))
                .nationalId(resultSet.getString("NATIONAL_ID"))
                .birthDate(resultSet.getDate("BIRTH_DATE") == null ? null : resultSet.getDate("BIRTH_DATE").toLocalDate())
                .build();
    }

    public static Product productMapper(ResultSet resultSet) throws SQLException {
        return Product
                .builder()
                .id(resultSet.getInt("ID"))
                .title(resultSet.getString("TITLE"))
                .author(resultSet.getString("AUTHOR"))
                .isbn(resultSet.getString("ISBN"))
                .build();
    }

    public static Transaction transactionMapper(ResultSet resultSet) throws SQLException {
        storekepper storekepper =
                Storekepper
                        .builder()
                        .id(resultSet.getInt("storekepper_ID"))
                        .name(resultSet.getString("storekepper_NAME"))
                        .family(resultSet.getString("storekepper_FAMILY"))
                        .nationalId(resultSet.getString("storekepper_NATIONAL_ID"))
                        .birthDate(resultSet.getDate("storekepper_BIRTH_DATE") == null ? null : resultSet.getDate("storekepper_BIRTH_DATE").toLocalDate())
                        .build();

        Product product = Product
                .builder()
                .id(resultSet.getInt("product_ID"))
                .title(resultSet.getString("product_TITLE"))
                .author(resultSet.getString("product_AUTHOR"))
                .isbn(resultSet.getString("product_ISBN"))
                .build();

        return Transaction
                .builder()
                .id(resultSet.getInt("transaction_ID"))
                .storekepper(storekepper)
                .product(product)
                .transactionDate(
                        resultSet.getDate("transaction_DATE") == null ? null :resultSet.getDate("transaction_DATE").toLocalDate()
                )
                .returnDate(
                        resultSet.getDate("RETURN_DATE") == null ? null : resultSet.getDate("RETURN_DATE").toLocalDate()
                )
                .build();
    }

    private static class storekepper {
        public static Debug builder() {
            return null;
        }
    }
}