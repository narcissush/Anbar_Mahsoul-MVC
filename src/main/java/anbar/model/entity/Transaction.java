package anbar.model.entity;

import anbar.model.entity.enums.TransactionType;
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
    private TransactionType transactionType;
    private int transactionQuantity;
    private LocalDateTime transactionDate;
}
