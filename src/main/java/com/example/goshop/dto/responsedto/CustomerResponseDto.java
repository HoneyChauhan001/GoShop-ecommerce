package com.example.goshop.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerResponseDto {
    String name;
    String emailId;
    String mobNo;
}
