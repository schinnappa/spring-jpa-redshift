package edu.kriyaa.springjparedshift.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import edu.kriyaa.springjparedshift.persistence.dao.TransactionRepository;
import edu.kriyaa.springjparedshift.persistence.model.Transaction;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionRepository mockDao;

    @Test
    void testFindAll() throws Exception {
        // Setup

        // Configure TransactionRepository.findAll(...).
        final Transaction transaction = new Transaction();
        transaction.setTransactionId("transactionId");
        final List<Transaction> transactions = Collections.singletonList(transaction);
        when(mockDao.findAll()).thenReturn(transactions);

        // Run the test
        mockMvc.perform(get("/transactions").accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].transactionId", is("transactionId")));
    }

    @Test
    void testFindAll_TransactionRepositoryReturnsNoItems() throws Exception {
        // Setup
        when(mockDao.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/transactions").accept(MediaType.APPLICATION_JSON))
                                                        .andReturn()
                                                        .getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("[]", response.getContentAsString());
    }
}
