package dev.dodo.library.dto.request.borrowing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingSaveRequest {
    private int id;

    private String user;

    private String date;

    private String returnDate;

    private int bookId;
}
