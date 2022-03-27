package org.voven4ek;

import org.voven4ek.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/student")
@Produces(MediaType.TEXT_PLAIN)
public class StudentResource {
    @Inject
    private StudentService studentService;

    @GET
    @Path("list")
    public List<String> listStudents() {
        return studentService.list().stream().map(x -> x.getFio()).collect(Collectors.toList());
    }

    @GET
    @Path("add/{fio}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response addStudent(@PathParam("fio") String fio) {
        studentService.createStudent(fio);
        return Response.ok().build();
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("add/{fio}/{event}")
    public Response addStudent(@PathParam("fio") String fio, @PathParam("event") String event) {
        studentService.createStudentAndEvent(fio, event);
        return Response.ok().build();
    }

}