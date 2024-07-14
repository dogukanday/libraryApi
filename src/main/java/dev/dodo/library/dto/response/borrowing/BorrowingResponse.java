package dev.dodo.library.dto.response.borrowing;

import dev.dodo.library.dto.response.book.BookResponse;
import dev.dodo.library.dto.response.book.BookResponseForBorrow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingResponse {
    private int id;

    private String user;

    private String date;

    private String returnDate;

    private BookResponseForBorrow book;
}
