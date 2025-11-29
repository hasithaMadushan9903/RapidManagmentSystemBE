package com.rapidattendencesystem.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationDTO {
    private int id;

    private String message;
    private String createdUserCode;
    private String createdDateTime;
    private Boolean isActive;
}
