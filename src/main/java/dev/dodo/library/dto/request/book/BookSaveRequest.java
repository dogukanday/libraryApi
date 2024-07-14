package dev.dodo.library.dto.request.book;

import dev.dodo.library.entities.Author;
import dev.dodo.library.entities.Borrowing;
import dev.dodo.library.entities.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {

    private int id;

    private String bookName;

    private int authorId;

    private int year;

    private int stock;

    private int categoryId;

    private int publisherId;

}
