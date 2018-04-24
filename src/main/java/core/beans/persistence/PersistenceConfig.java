package main.java.core.beans.persistence;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan
public class PersistenceConfig {
	//comment
	@Bean
	@Profile("main")
	public BasicDataSource mainDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306");
		ds.setUsername("root");
		ds.setPassword("password");
		ds.setInitialSize(5);
		ds.setMaxActive(10);
		return ds;
	}
	
	@Bean
	@Profile("test")
	public BasicDataSource testDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1531:XE");
		ds.setUsername("sys");
		ds.setPassword("ssi");
		ds.setInitialSize(5);
		ds.setMaxActive(10);
		return ds;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(mainDataSource());
	}
}
