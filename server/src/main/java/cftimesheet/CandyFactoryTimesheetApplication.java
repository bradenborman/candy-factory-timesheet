package cftimesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CandyFactoryTimesheetApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CandyFactoryTimesheetApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		return builder.sources(CandyFactoryTimesheetApplication.class).initializers();
	}

}
