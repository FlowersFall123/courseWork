package entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {
    private Long id;
    private String petName,species;
    private String password,sex,option,notes;
    private Date time;

}
