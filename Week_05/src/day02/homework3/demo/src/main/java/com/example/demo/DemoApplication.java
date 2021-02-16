package com.example.demo;

import com.demo.properties.KlassProperties;
import com.demo.properties.SchoolProperties;
import com.demo.properties.StudentPropertise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private StudentPropertise studentPropertise;
	@Autowired
	private KlassProperties klassProperties;
	@Autowired
	private SchoolProperties schoolProperties;

	public static void main(String[] args) {
		System.out.println("哈哈");

		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(studentPropertise);
		System.out.println(klassProperties);
		System.out.println(schoolProperties);

	}
}
