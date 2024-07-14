package dev.dodo.library.business.abstracts;

import dev.dodo.library.entities.Author;
import org.springframework.data.domain.Page;

public interface IAuthorService {

    Author save(Author author);
    Author update(Author author);
    Author findById(int id);
    boolean delete(int id);
    Page<Author> cursor(int page, int size);
}
