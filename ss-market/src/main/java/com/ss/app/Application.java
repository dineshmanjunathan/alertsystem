package com.ss.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.ss.app.entity.HibernateSequence;
import com.ss.app.model.HibernateSequenceRepository;
import com.ss.config.AuthenticationFilter;
import com.ss.config.SessionFilter;
import com.ss.scheduler.SchedulerTasks;

@SpringBootApplication
@ComponentScan("com.ss.app")
@EnableJpaRepositories
@EnableScheduling
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public SchedulerTasks bean() {
		return new SchedulerTasks();
	}

	/*
	 * @Bean public FilterRegistrationBean<AuthenticationFilter> loggingFilter() {
	 * FilterRegistrationBean<AuthenticationFilter> registrationBean = new
	 * FilterRegistrationBean<>(); registrationBean.setFilter(new
	 * AuthenticationFilter()); return registrationBean; }
	 */

}

@Component
class DemoCommandLineRunner implements CommandLineRunner {

	@Autowired
	private HibernateSequenceRepository hibernateSequenceRepository;

	@Override
	public void run(String... args) throws Exception {

		HibernateSequence s1 = new HibernateSequence();
		s1.setId(101);
		s1.setPrefixvalue("SS");
		s1.setNextval(111211);
		s1.setIncrement(1);

		hibernateSequenceRepository.save(s1);

		/*
		 * SSConfiguration ssConfig1 = new SSConfiguration(); ssConfig1.setCode("1111");
		 * ssConfig1.setDescription("COMPANY INCENTIVE"); ssConfig1.setValue(10.00);
		 * ssConfig1.setComment("COMPANY INCENTIVE (CAN MODIFY)");
		 * 
		 * SSConfiguration ssConfig2 = new SSConfiguration(); ssConfig2.setCode("1112");
		 * ssConfig2.setDescription("GST INCENTIVE"); ssConfig2.setValue(5.00);
		 * ssConfig2.setComment("GST INCENTIVE (CAN MODIFY)");
		 * 
		 * dbConfigRepository.save(ssConfig1); dbConfigRepository.save(ssConfig2);
		 */
	}
}
