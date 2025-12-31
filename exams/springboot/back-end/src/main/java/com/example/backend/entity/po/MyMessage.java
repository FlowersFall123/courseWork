package com.example.backend.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyMessage {
    private Long id;
    private String context;
    private Long toUserId;
    private Long fromUserId;
}
