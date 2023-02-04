package com.example.ws1301.ws1301;

import java.io.File;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ws1301Application {

	public static void main(String[] args) {
		SpringApplication.run(Ws1301Application.class, args);

		String path;

		ApplicationArguments app_args = new DefaultApplicationArguments(args);

		if (app_args.containsOption("path")) {
			path = app_args.getOptionValues("path").get(0);
		} else {
			throw new RuntimeException("path is not provided");
		}

		File file = new File(path);

		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Dir is created");
			} else {
				System.out.println("Failed to create the Dir");
			}

		}

	}

}
