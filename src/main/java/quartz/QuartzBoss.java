package main.java.quartz;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class QuartzBoss {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void init() {
		logger.info("Hello world from Quartz...");
	}

	@Bean
	public SpringBeanJobFactory springBeanJobFactory() {
		AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
		logger.debug("Configuring Job factory");

		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	/*-
		@Bean
		@Qualifier("databaseReaderJobDetail")
		public JobDetail databaseReaderJobDetail() {
	
			return newJob().ofType(DatabaseReaderJob.class).storeDurably().withIdentity(JobKey.jobKey("databaseReaderJob")).withDescription("Invoke Sample Job service...").build();
		}
	
		@Bean
		@Qualifier("onOffExecutorJobDetail")
		public JobDetail onOffExecutorJobDetail() {
	
			return newJob().ofType(OnOffExecutorJob.class).storeDurably().withIdentity(JobKey.jobKey("onOffExecutor")).withDescription("Invoke Sample Job service...").build();
		}
	
		@Bean
		@Qualifier("databaseReaderTrigger")
		public Trigger databaseReaderTrigger(@Qualifier("databaseReaderJobDetail") JobDetail job) {
	
			int frequencyInSec = 10;
			logger.info("Configuring trigger to fire every {} seconds", frequencyInSec);
	
			return newTrigger().forJob(job).withIdentity(TriggerKey.triggerKey("databaseReaderTrigger")).withDescription("databaseReader").withSchedule(simpleSchedule().withIntervalInSeconds(frequencyInSec).repeatForever()).build();
		}
	
		@Bean
		@Qualifier("onOffExecutorTrigger")
		public Trigger onOffExecutorTrigger(@Qualifier("onOffExecutorJobDetail") JobDetail job) {
	
			int frequencyInSec = 15;
			logger.info("Configuring trigger to fire every {} seconds", frequencyInSec);
	
			return newTrigger().forJob(job).withIdentity(TriggerKey.triggerKey("onOffExecutorTrigger")).withDescription("onOffExecutor").withSchedule(simpleSchedule().withIntervalInSeconds(frequencyInSec)).build();
	
			/*-
			 		return newTrigger().forJob(job).withIdentity(TriggerKey.triggerKey("onOffExecutorTrigger"))
					.withDescription("onOffExecutor")
					.withSchedule(simpleSchedule().withIntervalInSeconds(frequencyInSec).repeatForever()).build();
			 */
	/*
	}
	
	@Bean
	public Scheduler scheduler(@Qualifier("databaseReaderTrigger") Trigger trigger, @Qualifier("databaseReaderJobDetail") JobDetail job, @Qualifier("onOffExecutorTrigger") Trigger trigger2, @Qualifier("onOffExecutorJobDetail") JobDetail job2) throws SchedulerException, IOException {
	
		StdSchedulerFactory factory = new StdSchedulerFactory();
		//factory.initialize(new ClassPathResource("quartz.properties").getInputStream());
	
		logger.debug("Getting a handle to the Scheduler");
		Scheduler scheduler = factory.getScheduler();
		scheduler.setJobFactory(springBeanJobFactory());
		//scheduler.scheduleJob(job, trigger);
		//scheduler.scheduleJob(job2, trigger2);
	
		logger.debug("Starting Scheduler threads");
		// Commenting below line stopped the double job creation
		// scheduler.start();
		return scheduler;
	}*/
}