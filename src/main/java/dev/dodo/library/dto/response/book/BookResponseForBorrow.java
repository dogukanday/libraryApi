package dev.dodo.library.dto.response.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseForBorrow {
    private int id;

    private String bookName;

    private int authorId;

    private int year;

    private int publisherId;
}
