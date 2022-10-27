package project.main;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("project")
public class MainServiceApplication {

	private static final Logger LOG = LoggerFactory.getLogger(MainServiceApplication.class);

	private final Integer threadPoolSize;
	private final Integer taskQueueSize;


	//@Bean
	//RestTemplate restTemplate() {
	//	return new RestTemplate();
	//}

	@Autowired
	public MainServiceApplication(
		@Value("${app.threadPoolSize:10}") Integer threadPoolSize,
		@Value("${app.taskQueueSize:100}") Integer taskQueueSize
	) {
		this.threadPoolSize = threadPoolSize;
		this.taskQueueSize = taskQueueSize;
	}

	@Bean
	public Scheduler publishEventScheduler() {
		LOG.info("Creating a message scheduler with connectionPoolSize = {}", threadPoolSize);
		return Schedulers.newBoundedElastic(threadPoolSize, taskQueueSize, "publish-pool");
	}

	//@Bean
	//ReactiveHealthContributor coreServices() {

	//}

	public static void main(String[] args) {
		SpringApplication.run(MainServiceApplication.class, args);
	}

}
