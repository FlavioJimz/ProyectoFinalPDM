package com.example.libritos.models.DTOS;

import com.example.libritos.models.entities.Book;
import com.example.libritos.models.entities.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveFavoriteDTO {

    private User user;

    private Book book;
}
