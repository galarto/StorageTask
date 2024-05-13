package com.dandellion.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull(message = "Название товара обязательно должно быть указано")
    @NotBlank
    @Size(max = 255, message = "Название товара не должно превышать 255 символов")
    private String title;

    @Column
    @NotBlank
    @Size(max = 4096, message = "Описание товара не должно превышать 4096 символов")
    private String description;

    @Column
    @PositiveOrZero
    private double price;

    @Column(name = "is_available")
    private boolean isAvailable = false;

}
