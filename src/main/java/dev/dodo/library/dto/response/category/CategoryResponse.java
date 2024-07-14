package dev.dodo.library.dto.response.category;

import dev.dodo.library.dto.response.book.BookResponse;
import dev.dodo.library.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private int id;

    private String categoryName;

    private String categoryDescription;

    private List<BookResponse> categoryBooks;
}
