package com.wlrllr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.event.*;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@MapperScan("com.wlrllr.mapper")
@EnableAspectJAutoProxy  //这个没用。。
public class ApplicationMain extends SpringBootServletInitializer {

	public static void main(String[] args) {
		//SpringApplication.run(ApplicationMain.class, args);
		new ApplicationMain().init(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationMain.class);
	}

	private SpringApplication init(String[] args) {
		SpringApplication app = new SpringApplication(ApplicationMain.class);
		app.setAddCommandLineProperties(false);
		app.addListeners(new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				if (event instanceof ApplicationStartingEvent) {
					System.out.println("...started...");
				}
				if (event instanceof ApplicationEnvironmentPreparedEvent) {
					System.out.println("...EnvironmentPrepared...");
				}
				if (event instanceof ApplicationPreparedEvent) {
					System.out.println("...Prepared...");
				}
				if (event instanceof ApplicationReadyEvent) {
					System.out.println("...Ready...");
				}
				if (event instanceof ApplicationFailedEvent) {
					System.out.println("...Failed...");
				}
			}
		});
		app.run(args);
		return app;
	}

	/*@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {

				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
				ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

				container.addErrorPages(error401Page, error404Page, error500Page);
			}
		};
	}*/
}
