package com.tanyang.twitter;




import com.tanyang.twitter.filter.LoginFilter;
import com.tanyang.twitter.listener.ListenerTest;
import com.tanyang.twitter.servlet.ServletTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import java.util.EnumSet;

@EnableScheduling
@SpringBootApplication
public class TwitterApplication {



	private static Logger logger = LoggerFactory.getLogger(TwitterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TwitterApplication.class, args);
		logger.info("项目启动完毕");
	}

/*	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		//implements ServletContextInitializer
		//配置Servlet
		servletContext.addServlet("servletTest",new ServletTest())
				.addMapping("/servletTest");

		//配置过滤器
		servletContext.addFilter("timeFilter", new LoginFilter())
				.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");

		//配置监听器
		servletContext.addListener(new ListenerTest());
	}*/
}
