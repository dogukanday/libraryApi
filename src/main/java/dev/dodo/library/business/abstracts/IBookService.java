package dev.dodo.library.business.abstracts;

import dev.dodo.library.entities.Book;
import org.springframework.data.domain.Page;

public interface IBookService {
    Book save(Book book);
    Book update(Book book);
    boolean delete(int id);
    Book findById(int id);
    Page<Book> cursor(int page, int size);
}
