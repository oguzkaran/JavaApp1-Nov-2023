package com.bekirkocadag.app.operation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.bekirkocadag", "com.berkayyilmaz", "com.karandev"})
public class App {
	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
	}
}
