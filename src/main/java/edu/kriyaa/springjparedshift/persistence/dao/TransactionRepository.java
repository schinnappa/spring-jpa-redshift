package edu.kriyaa.springjparedshift.persistence.dao;

import edu.kriyaa.springjparedshift.persistence.model.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAll();
}
