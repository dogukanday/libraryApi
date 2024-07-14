package dev.dodo.library.dto.request.book;

import dev.dodo.library.entities.Borrowing;
import dev.dodo.library.entities.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    private int id;

    private String bookName;

    private int authorId;

    private int year;

    private int stock;

    private int categoryId;

    private int publisherId;
}
