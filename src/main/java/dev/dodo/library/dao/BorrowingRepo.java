package dev.dodo.library.dao;

import dev.dodo.library.entities.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepo extends JpaRepository<Borrowing, Integer> {
}
