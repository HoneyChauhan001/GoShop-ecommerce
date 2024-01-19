package com.example.goshop.dto.requestdto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CheckOutRequestDto {
    String customerEmailId;
    String cardNo;
    int cvv;
}
