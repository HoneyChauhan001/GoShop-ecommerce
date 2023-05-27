package com.example.goshop.dto.requestdto;

import com.example.goshop.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerRequestDto {
    String name;
    int age;
    String emailId;
    String mobNo;
    Gender gender;
}
