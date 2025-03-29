package ru.skypro.homework.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an advertisement object with an ID, title, author, image and price.
 * This class is used to pass ad data between application layers,
 * and is designed to simplify the processing and presentation of ad information in the user interface.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdDto {
    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
}
