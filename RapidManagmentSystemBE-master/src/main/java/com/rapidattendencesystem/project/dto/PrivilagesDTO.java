package com.rapidattendencesystem.project.dto;

import com.rapidattendencesystem.project.entity.Action;
import com.rapidattendencesystem.project.entity.AppIcon;
import com.rapidattendencesystem.project.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PrivilagesDTO {
    private int id;
    private Role role;
    private Action action;
    private AppIcon appIcon;
    private Boolean isActive;
}
