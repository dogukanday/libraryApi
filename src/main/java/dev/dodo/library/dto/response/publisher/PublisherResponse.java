package dev.dodo.library.dto.response.publisher;

import dev.dodo.library.dto.response.book.BookResponse;
import dev.dodo.library.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponse {

    private int id;

    private String publisherName;

    private String publisherYear;

    private String publisherAdress;

    private List<BookResponse> publisherBooks;
}
