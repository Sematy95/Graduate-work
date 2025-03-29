package ru.skypro.homework.dto.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a list of advertisement object with a count and results.
 * This class is used to package and pass multiple ad DTOs data between application layers,
 * and is designed to simplify the processing and presentation of ad information in the user interface.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ads {
    private Integer count;
    private List<AdDto> results;
}
