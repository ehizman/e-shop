package com.ecommerce.shop;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class ShopApplicationTests {
	@Value("${test-name}")
	private String name;

	@Value("${test-year}")
	private String currentYear;

	private DataSource dataSource;
	@Autowired
	public ShopApplicationTests(DataSource dataSource){
		this.dataSource = dataSource;
	}

	@Test
	public void connectToDatabaseTest(){
		assertThat(dataSource).isNotNull();
		log.info("Datasource properties ->{}", dataSource);
		try{
			Connection connection = dataSource.getConnection();
			assertThat(connection).isNotNull();
			log.info("Database -> {}", connection.getCatalog());
		}
		catch(SQLException ex){
			log.info("An exception occurred -> {}", ex.getMessage());
		}
	}

	@Test
	void printValuesFromApplicationProperties(){
		log.info("Test name -> {}", name);
		log.info("Test year -> {}", currentYear);
		assertThat(name).isEqualTo("Jack");
		assertThat(currentYear).isEqualTo("2024");
	}

}
