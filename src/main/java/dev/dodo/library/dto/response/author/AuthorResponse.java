package dev.dodo.library.dto.response.author;

import dev.dodo.library.dto.response.book.BookResponse;
import dev.dodo.library.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {

    private String authorName;

    private String authorCountry;

    private String authorBirthdate;

    private List<BookResponse> authorBooks;

}
