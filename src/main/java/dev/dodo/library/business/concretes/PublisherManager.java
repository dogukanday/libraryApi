package dev.dodo.library.business.concretes;

import dev.dodo.library.business.abstracts.IPublisherService;
import dev.dodo.library.dao.PublisherRepo;
import dev.dodo.library.entities.Publisher;
import org.springframework.data.domain.Page;
import dev.dodo.library.core.exceptions.NotFoundException;
import dev.dodo.library.core.utilies.Msg;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublisherManager implements IPublisherService {

    private final PublisherRepo publisherRepo;

    public PublisherManager(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Publisher update(Publisher publisher) {
        this.publisherRepo.findById(publisher.getId());
        return this.publisherRepo.save(publisher);
    }

    @Override
    public boolean delete(int id) {
        Publisher publisher = this.findById(id);
        this.publisherRepo.delete(publisher);
        return true;
    }

    @Override
    public Publisher findById(int id) {
        return this.publisherRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Publisher> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.publisherRepo.findAll(pageable);
    }
}
