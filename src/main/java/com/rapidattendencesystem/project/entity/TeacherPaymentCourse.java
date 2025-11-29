package com.rapidattendencesystem.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherPaymentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacherPayment_id")
    @JsonIgnore
    private TeacherPayment teacherPayment;

    private BigDecimal amount;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "month_id")
    private Month month;
}
