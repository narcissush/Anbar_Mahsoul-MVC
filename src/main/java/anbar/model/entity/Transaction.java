package anbar.model.entity;

import anbar.model.entity.enums.Transaction_type;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
public class Transaction {

    private  int id;
    private  int product_id;
    private  int storekepper_id;

    private Transaction_type transaction_type;
    private  int quantity ;
    private  LocalDate transaction_date;
}
