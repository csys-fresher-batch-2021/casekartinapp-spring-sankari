package in.casekartin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@EnableJdbcRepositories(basePackages = "in.casekartin.dao")
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan(basePackages = "in.casekartin")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Demo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

}
