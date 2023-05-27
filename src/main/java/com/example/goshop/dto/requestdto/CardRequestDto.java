package com.example.goshop.dto.requestdto;

import com.example.goshop.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {

    String customerEmailId;
    String cardNo;
    int cvv;
    CardType cardType;
    Date validTill;
}
