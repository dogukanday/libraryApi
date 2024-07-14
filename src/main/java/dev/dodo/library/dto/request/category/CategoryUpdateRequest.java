package dev.dodo.library.dto.request.category;

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
public class CategoryUpdateRequest {
    @Positive(message = "Id alanı pozitif tam sayı olmalıdır")
    private int id;
    @NotEmpty(message = "Kategori adı boş veya null olamaz")
    private String categoryName;

    private String categoryDescription;

    private int categoryBooksId;
}
