package dev.dodo.library.dto.request.author;

import dev.dodo.library.entities.Book;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequest {

    @Positive(message = "Id alanı pozitif tam sayı olmalıdır")
    private int id;
    @NotEmpty(message = "Yazar adı boş veya null olamaz")
    private String authorName;

    private String authorCountry;

    private String authorBirthdate;


}
