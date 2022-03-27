package org.voven4ek.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Event extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;
    @Column(name = "event_name")
    private String eventName;

    @ManyToOne
    @JoinColumn(name="student_id", nullable=false)
    private Student student;
}
