package dev.dodo.library.dto.request.borrowing;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingUpdateRequest {

    @Positive(message = "Id alanı pozitif tam sayı olmalıdır")
    private int id;

    @NotEmpty(message = "Kullanıcı adı boş veya null olamaz")
    private String user;

    private String date;

    private String returnDate;

    private int bookId;
}
