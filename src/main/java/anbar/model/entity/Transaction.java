package anbar.model.entity;

import anbar.model.entity.enums.Transaction_type;

import java.time.LocalDate;

public class Transaction {

    private  int id;
    private  int product_id;
    private  int storekepper_id;

    private Transaction_type transaction_type;
    private  int quantity ;
    private  LocalDate transaction_date;
}
