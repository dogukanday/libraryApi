package dev.dodo.library.api;

import dev.dodo.library.business.concretes.BookManager;
import dev.dodo.library.business.concretes.PublisherManager;
import dev.dodo.library.core.config.modelMapper.IModelMapperService;
import dev.dodo.library.core.result.Result;
import dev.dodo.library.core.result.ResultData;
import dev.dodo.library.core.utilies.ResultHelper;
import dev.dodo.library.dto.request.publisher.PublisherSaveRequest;
import dev.dodo.library.dto.request.publisher.PublisherUpdateRequest;
import dev.dodo.library.dto.response.CursorResponse;
import dev.dodo.library.dto.response.publisher.PublisherResponse;
import dev.dodo.library.entities.Book;
import dev.dodo.library.entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {
    private final PublisherManager publisherManager;
    private final IModelMapperService modelMapper;
    private final BookManager bookManager;

    public PublisherController(PublisherManager publisherManager, IModelMapperService modelMapper, BookManager bookManager) {
        this.publisherManager = publisherManager;
        this.modelMapper = modelMapper;
        this.bookManager = bookManager;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest){
        Publisher savePublisher = this.modelMapper.forRequest().map(publisherSaveRequest, Publisher.class);
        publisherSaveRequest.setId(0);
        Book book = this.bookManager.findById(publisherSaveRequest.getPublisherBooksId());
        savePublisher.setPublisherBooks(List.of(book));
        this.publisherManager.save(savePublisher);
        return ResultHelper.created(this.modelMapper.forResponse().map(savePublisher, PublisherResponse.class));
    }
    @GetMapping("/{id}")
    public ResultData<PublisherResponse> findById(@PathVariable("id") int id){
        Publisher publisher = this.publisherManager.findById(id);
        return ResultHelper.ok(this.modelMapper.forResponse().map(publisher, PublisherResponse.class));
    }

    @GetMapping()
    public ResultData<CursorResponse<PublisherResponse>> cursor(
            @RequestParam(value = "page", defaultValue = "0" , required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ){
        Page<Publisher> publisherPage = this.publisherManager.cursor(page, size);
        Page<PublisherResponse> publisherResponse = publisherPage.map(publisher -> this.modelMapper.forResponse().map(publisher, PublisherResponse.class));
        return ResultHelper.cursor(publisherResponse);
    }

    @PutMapping
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest){
        Publisher updatePublisher = this.modelMapper.forRequest().map(publisherUpdateRequest, Publisher.class);
        Book book = this.bookManager.findById(publisherUpdateRequest.getPublisherBooksId());
        updatePublisher.setPublisherBooks(List.of(book));
        this.publisherManager.save(updatePublisher);
        return ResultHelper.updated(this.modelMapper.forResponse().map(updatePublisher, PublisherResponse.class));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") int id){
        this.publisherManager.delete(id);
        return ResultHelper.successResult();
    }
}
