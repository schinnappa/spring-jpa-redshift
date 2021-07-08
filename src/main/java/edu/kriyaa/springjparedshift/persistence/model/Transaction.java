package edu.kriyaa.springjparedshift.persistence.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transaction {

    @Id
    private String transactionId;
    private BigDecimal amount;
}
