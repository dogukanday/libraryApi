package dev.dodo.library.business.abstracts;

import dev.dodo.library.entities.Publisher;
import org.springframework.data.domain.Page;

public interface IPublisherService {
    Publisher save(Publisher publisher);
    Publisher update(Publisher publisher);
    boolean delete(int id);
    Publisher findById(int id);
    Page<Publisher> cursor(int page, int size);
}
