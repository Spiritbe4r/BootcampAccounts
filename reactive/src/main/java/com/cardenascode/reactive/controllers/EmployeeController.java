package com.cardenascode.reactive.controllers;

import com.cardenascode.reactive.model.Employee;
import com.cardenascode.reactive.model.EmployeeEvent;
import com.cardenascode.reactive.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/reactive")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/all")
    public Flux<Employee> getAll(){
        return employeeRepository.findAll();
    }
    @GetMapping("/{id}")
    public Mono<Employee> getId(@PathVariable("id")final String empId){
        return employeeRepository.findById(empId);
    }

    @GetMapping(value = "/{id}/events",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EmployeeEvent> getEvents(@PathVariable("id")final String id){
        return employeeRepository.findById(id)
                .flatMapMany(employee -> {
                    Flux<Long> interval=Flux.interval(Duration.ofSeconds(2));

                    Flux<EmployeeEvent> employeeEventFlux=
                            Flux.fromStream(
                                    Stream.generate(()->new EmployeeEvent(employee,new Date()))
                            );
                    return Flux.zip(interval,employeeEventFlux)
                            .map(Tuple2::getT2);


                });
    }
}
