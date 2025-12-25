package com.springmvc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryCourse {
    private Integer id;
    private String courseName;
    private String room;
    private Integer minNumber;
    private Integer maxNumber;
}
