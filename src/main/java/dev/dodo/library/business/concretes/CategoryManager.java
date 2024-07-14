package dev.dodo.library.business.concretes;

import dev.dodo.library.business.abstracts.ICategoryService;
import dev.dodo.library.core.exceptions.NotFoundException;
import dev.dodo.library.core.utilies.Msg;
import dev.dodo.library.dao.CategoryRepo;
import dev.dodo.library.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements ICategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryManager(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category update(Category category) {
        this.categoryRepo.findById(category.getId());
        return this.categoryRepo.save(category);
    }

    @Override
    public boolean delete(int id) {
        Category category = this.findById(id);
        this.categoryRepo.delete(category);
        return true;
    }

    @Override
    public Category findById(int id) {
        return this.categoryRepo.findById(id).orElseThrow(() ->new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Category> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.categoryRepo.findAll(pageable);
    }
}
