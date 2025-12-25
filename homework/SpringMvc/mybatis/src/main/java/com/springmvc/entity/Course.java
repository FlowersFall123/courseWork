package com.springmvc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    private Integer id;
    private String courseName;
    private String code;
    private String teacher;
    private String room;
    private Integer number;
}
