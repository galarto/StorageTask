package com.dandellion.models;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class Product {
    @NotBlank
    @Size(max = 255)
    private String title;
    @Size(max = 4096)
    private String description;
    @PositiveOrZero
    private long price;
    private boolean isAvailable = false;

}
