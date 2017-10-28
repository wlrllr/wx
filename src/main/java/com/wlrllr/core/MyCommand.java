package com.wlrllr.core;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommand implements CommandLineRunner {

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("MyCommand.run >>>>>CommandLineRunner");
	}

}
