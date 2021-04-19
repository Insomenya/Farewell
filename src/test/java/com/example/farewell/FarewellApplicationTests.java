package com.example.farewell;

import com.example.farewell.domain.*;
import com.example.farewell.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FarewellApplicationTests {

	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private OperatorRepo operatorRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Test
	void contextLoads() {

		Category category = new Category("смартфоны");
		categoryRepo.save(category);

		Customer newCustomer = new Customer();
		newCustomer.setFullName("алексей");
		newCustomer.setPhoneNum("797898");
		newCustomer.setPersonalDiscount(0.0d);
		newCustomer.setPassword("sdfhjsl");
		customerRepo.save(newCustomer);

		Operator operator = new Operator("Denis", "login", "sdfsdsdf");
		operatorRepo.save(operator);


		Product product1 = new Product("honor 9", 16000.0d, (short)3, "lsfdsdsfsdfd", "path to image", category, operator);
		Product product2 = new Product("galaxy a2", 13000.0d, (short)2, "lsfhhhhhhhhsdfd", "path to image", category, operator);
		productRepo.save(product1);
		productRepo.save(product2);

		Order order = new Order();
		order.setCustomer(newCustomer);
		order.setProcessedBy(operator);
		order.setDate(LocalDate.now());
		order.setStatus((short)0);
		List<Product> products = new ArrayList<Product>();
		products.add(product1);
		products.add(product2);
		order.setProducts(products);
		orderRepo.save(order);
	}

}
