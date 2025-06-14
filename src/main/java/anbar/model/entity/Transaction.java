package anbar.model.entity;

import anbar.model.entity.enums.Transaction_type;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
public class Transaction implements Serializable {

    private int id;
    private Product product;
    private Supplier supplier;
    private User user;
    private Transaction_type transaction_type;
    private int quantity;
    private LocalDateTime transaction_dateTime;
}
