package com.example.goshop.dto.responsedto;

import com.example.goshop.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {
    String customerName;
    String cardNo;
    CardType cardType;
}
