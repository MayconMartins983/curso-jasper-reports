package com.mballem.curso.jasper.spring;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JasperReportsSpringApplication {

	public static void main(String[] args) {
		var jsonObject = new JSONObject();
		jsonObject.put("1", "Maycon");
		jsonObject.put("2", "Maycon2");
		jsonObject.put("3", "Maycon3");

		System.out.printf(jsonObject.toString());
		SpringApplication.run(JasperReportsSpringApplication.class, args);
	}

	public void teste() {

	}

}
