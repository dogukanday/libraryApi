package dev.dodo.library.business.abstracts;

import dev.dodo.library.entities.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {
    Category save(Category category);
    Category update(Category category);
    boolean delete(int id);
    Category findById(int id);
    Page<Category> cursor(int page, int size);
}
