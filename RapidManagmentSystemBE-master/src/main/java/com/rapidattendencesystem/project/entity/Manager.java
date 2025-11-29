package com.rapidattendencesystem.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mcode;
    private String fullName;
    private String title;
    private String contactNumber;
    private String email;
    private Boolean isActive;
    private String birthDay;
    private String gender;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Role role;

    @PostPersist
    public void generateCode() {
        this.mcode = "M" + String.format("%05d", this.id);
    }
}
