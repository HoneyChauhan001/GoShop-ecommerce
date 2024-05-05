package com.example.goshop.dto.responsedto;

import com.example.goshop.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {
    int id;
    String customerName;
    String cardNo;
    CardType cardType;
    Date validTill;
}
