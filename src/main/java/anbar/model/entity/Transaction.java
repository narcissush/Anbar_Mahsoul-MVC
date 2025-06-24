package anbar.model.entity;

import anbar.model.entity.enums.Party;
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
    private int productId;
    private int supplierId;
    private int userId;
    private TransactionType transactionType;
    private int quantity;
    private LocalDateTime transactionDateTime;
}
