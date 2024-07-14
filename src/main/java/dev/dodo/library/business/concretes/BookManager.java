package dev.dodo.library.business.concretes;

import dev.dodo.library.business.abstracts.IBookService;
import dev.dodo.library.dao.BookRepo;
import dev.dodo.library.entities.Book;
import dev.dodo.library.core.exceptions.NotFoundException;
import dev.dodo.library.core.utilies.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookManager implements IBookService {

    private final BookRepo bookRepo;

    public BookManager(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Book save(Book book) {
        return this.bookRepo.save(book);
    }

    @Override
    public Book update(Book book) {
        this.findById(book.getId());
        return this.bookRepo.save(book);
    }

    @Override
    public boolean delete(int id) {
        Book book = this.findById(id);
        this.bookRepo.delete(book);
        return true;
    }

    @Override
    public Book findById(int id) {
        return this.bookRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Book> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.bookRepo.findAll(pageable);
    }
}
