package dev.dodo.library.api;

import dev.dodo.library.business.concretes.*;
import dev.dodo.library.core.config.modelMapper.IModelMapperService;
import dev.dodo.library.core.result.Result;
import dev.dodo.library.core.result.ResultData;
import dev.dodo.library.core.utilies.ResultHelper;
import dev.dodo.library.dto.request.book.BookSaveRequest;
import dev.dodo.library.dto.request.book.BookUpdateRequest;
import dev.dodo.library.dto.response.CursorResponse;
import dev.dodo.library.dto.response.book.BookResponse;
import dev.dodo.library.entities.Book;
import dev.dodo.library.entities.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    private final BookManager bookManager;
    private final IModelMapperService modelMapper;
    private final AuthorManager authorManager;
    private final CategoryManager categoryManager;
    private final PublisherManager publisherManager;

    public BookController(BookManager bookManager, IModelMapperService modelMapper, AuthorManager authorManager, CategoryManager categoryManager, PublisherManager publisherManager, BorrowingManager borrowingManager) {
        this.bookManager = bookManager;
        this.modelMapper = modelMapper;
        this.authorManager = authorManager;
        this.categoryManager = categoryManager;
        this.publisherManager = publisherManager;
    }

    @PostMapping
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest){
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);
        saveBook.setId(0);
        saveBook.setAuthor(this.authorManager.findById(bookSaveRequest.getAuthorId()));
        Category category = this.categoryManager.findById(bookSaveRequest.getCategoryId());
        saveBook.setCategory(List.of(category));
        saveBook.setPublisher(this.publisherManager.findById(bookSaveRequest.getPublisherId()));
        this.bookManager.save(saveBook);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBook, BookResponse.class));
    }

    @GetMapping("/{id}")
    public ResultData<BookResponse> findById(@PathVariable("id") int id){
        Book book = this.bookManager.findById(id);
        return ResultHelper.ok(this.modelMapper.forResponse().map(book, BookResponse.class));
    }

    @GetMapping()
    public ResultData<CursorResponse<BookResponse>> cursor(
            @RequestParam(value = "page", defaultValue = "0" , required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ){
        Page <Book> bookPage = this.bookManager.cursor(page, size);
        Page<BookResponse> bookResponse = bookPage.map(book -> this.modelMapper.forResponse().map(book, BookResponse.class));
        return ResultHelper.cursor(bookResponse);
    }

    @PutMapping
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        Book updatedBook = this.modelMapper.forRequest().map(bookUpdateRequest, Book.class);
        updatedBook.setAuthor(this.authorManager.findById(bookUpdateRequest.getAuthorId()));
        Category category = this.categoryManager.findById(bookUpdateRequest.getCategoryId());
        updatedBook.setCategory(List.of(category));
        updatedBook.setPublisher(this.publisherManager.findById(bookUpdateRequest.getPublisherId()));
        this.bookManager.update(updatedBook);
        return ResultHelper.ok(this.modelMapper.forResponse().map(updatedBook, BookResponse.class));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") int id){
        this.bookManager.delete(id);
        return ResultHelper.successResult();
    }

}
