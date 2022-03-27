package org.voven4ek.service;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.voven4ek.entity.Event;
import org.voven4ek.entity.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Log4j2
@ApplicationScoped
public class StudentService {
    @Inject
    EntityManager em;

    @Transactional
    public void createStudent(String fio) {
        log.info("fio={}", fio);
        Student student = new Student();
        student.setFio(fio);
        em.persist(student);
    }

    @Transactional
    public void createStudentAndEvent(String fio, String eventName) {
        log.info("fio={} eventName={}", fio, eventName);

        Student student = new Student();

        student.setFio(fio);
        em.persist(student);

        Event event = new Event();

        event.setEventName(eventName);
        student.setEvents(List.of(event));
        event.setStudent(student);

        em.persist(event);
        em.persist(student);

    }

    @Transactional
    public List<Student> list() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery(Student.class);
        query.from(Student.class);
        List resultList = em.createQuery(query).getResultList();

        resultList.forEach(log::info);
        
        return resultList;
    }

}
