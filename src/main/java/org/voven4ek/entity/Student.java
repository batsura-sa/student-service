package org.voven4ek.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Student extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(unique = true)
    private String fio;

    @OneToMany(mappedBy = "student")
    private List<Event> events;
}
