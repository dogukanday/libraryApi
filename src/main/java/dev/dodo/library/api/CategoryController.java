package dev.dodo.library.api;

import dev.dodo.library.business.concretes.BookManager;
import dev.dodo.library.business.concretes.CategoryManager;
import dev.dodo.library.core.config.modelMapper.IModelMapperService;
import dev.dodo.library.core.result.Result;
import dev.dodo.library.core.result.ResultData;
import dev.dodo.library.core.utilies.ResultHelper;
import dev.dodo.library.dto.request.category.CategorySaveRequest;
import dev.dodo.library.dto.request.category.CategoryUpdateRequest;
import dev.dodo.library.dto.response.CursorResponse;
import dev.dodo.library.dto.response.category.CategoryResponse;
import dev.dodo.library.entities.Book;
import dev.dodo.library.entities.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    private final CategoryManager categoryManager;
    private final IModelMapperService modelMapper;
    private final BookManager bookManager;

    public CategoryController(CategoryManager categoryManager, IModelMapperService modelMapper, BookManager bookManager) {
        this.categoryManager = categoryManager;
        this.modelMapper = modelMapper;
        this.bookManager = bookManager;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest){
        Category saveCategory = this.modelMapper.forRequest().map(categorySaveRequest, Category.class);
        Book book = this.bookManager.findById(categorySaveRequest.getCategoryBooksId());
        saveCategory.setCategoryBooks(List.of(book));
        this.categoryManager.save(saveCategory);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCategory, CategoryResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> findById(@PathVariable("id") int id){
        Category category = this.categoryManager.findById(id);
        return ResultHelper.ok(this.modelMapper.forResponse().map(category, CategoryResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> cursor(
            @RequestParam(value = "page", defaultValue = "0" , required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ){
        Page<Category> categoryPage = this.categoryManager.cursor(page, size);
        Page<CategoryResponse> categoryResponse = categoryPage.map(category -> this.modelMapper.forResponse().map(category, CategoryResponse.class));

        return ResultHelper.cursor(categoryResponse);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest){
        Category updatedCategory = this.modelMapper.forRequest().map(categoryUpdateRequest, Category.class);
        Book book = this.bookManager.findById(categoryUpdateRequest.getCategoryBooksId());
        updatedCategory.setCategoryBooks(List.of(book));
        this.categoryManager.update(updatedCategory);
        return ResultHelper.updated(this.modelMapper.forResponse().map(updatedCategory, CategoryResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.categoryManager.delete(id);
        return ResultHelper.successResult();
    }

}
