package dev.dodo.library.dto.request.category;

import dev.dodo.library.entities.Book;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySaveRequest {
    @NotEmpty(message = "Kategori adı boş veya null olamaz")
    private String categoryName;

    private String categoryDescription;

    private int categoryBooksId;
}
