package com.cardenascode.reactive.handler;

import com.cardenascode.reactive.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentHandler {

    public Mono<ServerResponse> getAllStudents(ServerRequest request){

        Flux<Student> students=Flux.range(1,20)
                .doOnNext(i-> System.out.println("student record: "+i))
                .map(i-> new Student(i,"student"+i));

            return ServerResponse.ok().body(students,Student.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String pathVariable=request.pathVariable("studentId");

        Integer studenId=Integer.valueOf(pathVariable);
        Flux<Student> students=Flux.range(1,20)
                .doOnNext(i-> System.out.println("student record: "+i))
                .map(i-> new Student(i,"student"+i));
        Mono<Student> next=students.filter(estudent->estudent.getId()==studenId).next();
        return ServerResponse.ok().body(next,Student.class);




    }

    public Mono<ServerResponse>addStudent(ServerRequest request){
        Mono<Student> bodyToMono=request.bodyToMono(Student.class);
        Mono<String>student=bodyToMono.map(std->std.getId() + ":"+std.getName());
        return ServerResponse.ok().body(student,String.class);

    }
}
