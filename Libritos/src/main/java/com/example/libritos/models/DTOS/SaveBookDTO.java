package com.example.libritos.models.DTOS;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveBookDTO {
    @NotEmpty(message = "title can't be empty")
    private String title;

    @NotEmpty(message = "author can't be empty")
    private String author;

    @NotEmpty(message = "genre can't be empty")
    private String genre;

    @NotEmpty(message = "description can't be empty")
    private String description;

    private Float rating;
}
