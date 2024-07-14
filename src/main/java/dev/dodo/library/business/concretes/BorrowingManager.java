package dev.dodo.library.business.concretes;

import dev.dodo.library.business.abstracts.IBorrowingService;
import dev.dodo.library.dao.BorrowingRepo;
import dev.dodo.library.entities.Borrowing;
import dev.dodo.library.core.exceptions.NotFoundException;
import dev.dodo.library.core.utilies.Msg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BorrowingManager implements IBorrowingService {

    private final BorrowingRepo borrowingRepo;

    public BorrowingManager(BorrowingRepo borrowingRepo) {
        this.borrowingRepo = borrowingRepo;
    }

    @Override
    public Borrowing save(Borrowing borrowing) {
        return this.borrowingRepo.save(borrowing);
    }

    @Override
    public Borrowing update(Borrowing borrowing) {
        this.findById(borrowing.getId());
        return this.borrowingRepo.save(borrowing);
    }

    @Override
    public boolean delete(int id) {
        Borrowing borrowing = this.findById(id);
        this.borrowingRepo.delete(borrowing);
        return true;

    }

    @Override
    public Borrowing findById(int id) {
        return this.borrowingRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Borrowing> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.borrowingRepo.findAll(pageable);
    }
}
