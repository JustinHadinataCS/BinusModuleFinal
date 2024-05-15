package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@SpringBootApplication
@RestController
public class BinusModulesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BinusModulesApplication.class, args);
	}

	@Controller
	public class MyController {

		@GetMapping("/index")
		public String index() {
			return "index";
		}
	}
}