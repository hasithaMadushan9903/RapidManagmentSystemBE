package com.rapidattendencesystem.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime date;

    @ManyToOne
    private Teacher teacher;

    private String reciptNumber;

    @OneToMany(mappedBy = "teacherPayment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeacherPaymentCourse> teacherPaymentCourse;

    @PostPersist
    public void generateRecipt() {
        this.reciptNumber = "R-T" + String.format("%05d", this.id);
    }


}
