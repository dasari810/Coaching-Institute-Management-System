package com.dbms.spark.dao;

import com.dbms.spark.models.Transaction;
import com.dbms.spark.util.DateTimeUtil;
import com.dbms.spark.util.PreparedStatementUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class  TransactionDao {
	
	@Autowired
    private PreparedStatementUtil preparedStatementUtil;
	
    @Autowired
    private JdbcTemplate jt;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    

    // save a transaction
   
    public Transaction save(Transaction transaction) {
    	
        String sql = "INSERT INTO transaction (amount, date, time, transactionMode , isSuccess) VALUES (?, ?, ?, ? ,?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jt.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] { "transactionId" });
                preparedStatementUtil.setParameters(preparedStatement, 
                		transaction.getAmount(),
                        dateTimeUtil.getCurrentDateTime("yyyy-MM-dd"), 
                        dateTimeUtil.getCurrentDateTime("HH:mm:ss"),
                        transaction.getTransactionMode(),
                        transaction.getIsSuccess()
                        );
                
                return preparedStatement;
            }
        }, keyHolder);
        
        int transactionId = keyHolder.getKey().intValue();
        transaction.setTransactionId(transactionId);
        
        return transaction;
    }
    
    // get a transaction 

    
    public Transaction get(int transactionId) {
        try {
            String sql = "SELECT * FROM transaction WHERE transactionId = ?";
            
            Transaction transaction = jt.queryForObject(sql, new Object[] { transactionId }, new BeanPropertyRowMapper<>(Transaction.class));
            
            return transaction;
            
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // update a transaction 
    
    public void setSuccess(int transactionId) {
    	
        String sql = "UPDATE transaction SET isSuccess = ? WHERE transactionId = ?";
        
        jt.update(sql, true, transactionId);
    }

    
    // get all transactions 
    
    public List<Transaction> getAll() {
    	
        String sql = "SELECT * FROM transaction";
        
        List<Transaction> transactions = jt.query(sql, new BeanPropertyRowMapper<>(Transaction.class));
        return transactions;
    }

}
