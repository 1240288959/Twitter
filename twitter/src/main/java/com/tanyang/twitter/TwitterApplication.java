package com.tanyang.twitter;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterApplication {

	private static Logger logger = LoggerFactory.getLogger(TwitterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TwitterApplication.class, args);
		logger.info("项目启动完毕");
	}
}
