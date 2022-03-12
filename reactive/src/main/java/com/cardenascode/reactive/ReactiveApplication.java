package com.cardenascode.reactive;

import com.cardenascode.reactive.model.Employee;
import com.cardenascode.reactive.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveApplication {

	@Bean
	CommandLineRunner employees(EmployeeRepository employeeRepository){
		return args -> {
			employeeRepository
					.deleteAll()
					.subscribe(null,null,()->{
						Stream.of(new Employee(UUID.randomUUID().toString(),
								"Peter",2300L),
								new Employee(UUID.randomUUID().toString(),
										"Peping",2300L),
								new Employee(UUID.randomUUID().toString(),
										"Raul",2300L),
								new Employee(UUID.randomUUID().toString(),
										"Steven",2300L)
								)
								.forEach(employee -> {
									employeeRepository.save(employee)
											.subscribe(System.out::println);

								});

					});
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

}
