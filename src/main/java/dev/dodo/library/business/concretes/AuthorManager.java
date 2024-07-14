package dev.dodo.library.business.concretes;

import dev.dodo.library.business.abstracts.IAuthorService;
import dev.dodo.library.dao.AuthorRepo;
import dev.dodo.library.entities.Author;
import dev.dodo.library.core.exceptions.NotFoundException;
import dev.dodo.library.core.utilies.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorManager implements IAuthorService {

    private final AuthorRepo authorRepo;

    public AuthorManager(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author update(Author author) {
        this.findById(author.getId());
        return this.authorRepo.save(author);
    }

    @Override
    public Author findById(int id) {
        return this.authorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public boolean delete(int id) {
        Author author = this.findById(id);
        this.authorRepo.delete(author);
        return true;
    }

    @Override
    public Page<Author> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.authorRepo.findAll(pageable);
    }
}
