package com.example.goshop.dto.requestdto;

import jdk.jfr.Name;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SellerRequestDto {
    String name;
    String emailId;
    String mobNo;
}
