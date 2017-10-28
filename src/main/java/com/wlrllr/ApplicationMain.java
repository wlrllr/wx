package com.wlrllr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
@MapperScan("com.wlrllr.mapper")
public class ApplicationMain {

	public static void main(String[] args) {
		new ApplicationMain().init(args);
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
}
