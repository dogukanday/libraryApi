package dev.dodo.library.business.abstracts;

import dev.dodo.library.entities.Borrowing;
import org.springframework.data.domain.Page;

public interface IBorrowingService {
    Borrowing save(Borrowing borrowing);
    Borrowing update(Borrowing borrowing);
    boolean delete(int id);
    Borrowing findById(int id);
    Page<Borrowing> cursor(int page, int size);
}
