package dev.dodo.library.api;

import dev.dodo.library.business.concretes.AuthorManager;
import dev.dodo.library.business.concretes.BookManager;
import dev.dodo.library.core.config.modelMapper.IModelMapperService;
import dev.dodo.library.core.result.Result;
import dev.dodo.library.core.result.ResultData;
import dev.dodo.library.core.utilies.ResultHelper;
import dev.dodo.library.dto.request.author.AuthorSaveRequest;
import dev.dodo.library.dto.request.author.AuthorUpdateRequest;
import dev.dodo.library.dto.response.CursorResponse;
import dev.dodo.library.dto.response.author.AuthorResponse;
import dev.dodo.library.entities.Author;
import dev.dodo.library.entities.Book;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {
    private final AuthorManager authorManager;
    private final IModelMapperService modelMapper;
    private final BookManager bookManager;

    public AuthorController(AuthorManager authorManager, IModelMapperService modelMapper, BookManager bookManager) {
        this.authorManager = authorManager;
        this.modelMapper = modelMapper;
        this.bookManager = bookManager;
    }

    @PostMapping
    public ResultData<AuthorResponse> save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest){
        Author saveAuthor = this.modelMapper.forRequest().map(authorSaveRequest, Author.class);
        saveAuthor.setId(0);
        this.authorManager.save(saveAuthor);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAuthor, AuthorResponse.class));
    }

    @GetMapping("/{id}")
    public ResultData<AuthorResponse> findById(@PathVariable("id") int id){
        Author author = this.authorManager.findById(id);
        return ResultHelper.ok(this.modelMapper.forResponse().map(author, AuthorResponse.class));
    }

    @GetMapping()
    public ResultData<CursorResponse<AuthorResponse>> cursor (
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ){
        Page<Author> authors = this.authorManager.cursor(page, size);
        Page<AuthorResponse> authorResponses = authors.map(author -> this.modelMapper.forResponse().map(author, AuthorResponse.class));
        return ResultHelper.cursor(authorResponses);
    }

    @PutMapping
    public ResultData<AuthorResponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest){
        Author updatedAuthor = this.modelMapper.forRequest().map(authorUpdateRequest, Author.class);
        this.authorManager.update(updatedAuthor);
        return ResultHelper.ok(this.modelMapper.forResponse().map(updatedAuthor, AuthorResponse.class));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") int id){
        this.authorManager.delete(id);
        return ResultHelper.successResult();
    }

}
