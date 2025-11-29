package com.rapidattendencesystem.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SystemAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String syscode;
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
        this.syscode = "A" + String.format("%05d", this.id);
    }
}
