package edu.kriyaa.springjparedshift.controller;

import edu.kriyaa.springjparedshift.persistence.dao.TransactionRepository;
import edu.kriyaa.springjparedshift.persistence.model.Transaction;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/transactions")
@AllArgsConstructor
public class TransactionController {

    TransactionRepository dao;

    @GetMapping
    @ResponseBody
    public List<Transaction> findAll() {
        return dao.findAll();
    }
}
