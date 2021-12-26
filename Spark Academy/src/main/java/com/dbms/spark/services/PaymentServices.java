package com.dbms.spark.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.dbms.spark.dao.UserPhoneNumberDao;
import com.dbms.spark.models.User;
import com.dbms.spark.models.UserPhoneNumber;
import com.instamojo.wrapper.api.ApiContext;
import com.instamojo.wrapper.api.Instamojo;
import com.instamojo.wrapper.api.InstamojoImpl;
import com.instamojo.wrapper.exception.ConnectionException;
import com.instamojo.wrapper.exception.HTTPException;
import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.model.PaymentOrderResponse;

@Transactional
@Service
public class PaymentServices {

	
	@Autowired
    private UserPhoneNumberDao userPhoneNumberDao;
	
//	 ApiContext context = ApiContext.create(System.getenv("INSTAMOJO_CLIENT_ID"), System.getenv("INSTAMOJO_CLIENT_SECRET"), ApiContext.Mode.TEST);
	ApiContext context = ApiContext.create("test_YCG61lD2lCHBbsPvM3mCN4HYw4HDwn9cGOV", "test_Q3al8aaxeUBsxhe4tjhiCGQa8DArXfa7dlStuJMUgzxiYMraidpQU7HfLTzMFb6Za2ZTBmPlqTVsZthpFlIgRLhrzxanZhvdOxxgtvZ7CGpyFZraiNPDaDeszlz" , ApiContext.Mode.TEST);
	 Instamojo api = new InstamojoImpl(context);
	 
	 public int getTransactionId(String transaction_id) {
	        String prefix = "SPARK_ACAD";
	        return Integer.valueOf(transaction_id.substring(prefix.length()));
	    }

	 
	 
	 public String createPayment(User user, int transactionId, int amount, String courseId, String batchId) {

	       
	        PaymentOrder order = new PaymentOrder();
	        order.setName(user.getName());
	        order.setEmail(user.getEmailAddress());
	        order.setPhone("9999999999");
	        order.setCurrency("INR");
	        order.setAmount((double)amount);
	        order.setDescription("Payment for enrolling " + user.getFirstName() + " in " + courseId + batchId );
	        
	        String hostname = System.getenv("SERVER_HOSTNAME");
	        if (hostname == null) {
	            hostname="http://localhost:8080";
	        }
	            
	        String redirectUrl = hostname + "/student/transaction/" + courseId + "/" + batchId;
	        order.setRedirectUrl(redirectUrl);
	        order.setTransactionId( "SPARK_ACAD" + transactionId);

	        List<UserPhoneNumber> phoneNumbers = userPhoneNumberDao.getByUserId(user.getUserId());
	        if (phoneNumbers.size() > 0) {
	            String phoneNumber = phoneNumbers.get(0).getPhoneNumber();
	            if (phoneNumber.length() == 10) {
	                order.setPhone(phoneNumber);
	            }
	        }

	        try {
	            PaymentOrderResponse paymentOrderResponse = api.createPaymentOrder(order);
	            return paymentOrderResponse.getPaymentOptions().getPaymentUrl();

	        } catch (HTTPException e) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
	                    e.getStatusCode() + e.getMessage() + e.getJsonPayload());

	        } catch (ConnectionException e) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
	        }
	    }
	 
	 
	 public String getTransactionDetails(int transactionId) {
	       
	        try {
	            PaymentOrder paymentOrder = api.getPaymentOrderByTransactionId("SPARK_ACAD" + transactionId);
	            return paymentOrder.getStatus();

	        } catch (HTTPException e) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
	                    e.getStatusCode() + " " + e.getMessage() + " " + e.getJsonPayload());

	        } catch (ConnectionException e) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
	        }
	    }
}
