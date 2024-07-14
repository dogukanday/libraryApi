package dev.dodo.library.api;

import dev.dodo.library.business.concretes.BookManager;
import dev.dodo.library.business.concretes.BorrowingManager;
import dev.dodo.library.core.config.modelMapper.IModelMapperService;
import dev.dodo.library.core.result.Result;
import dev.dodo.library.core.result.ResultData;
import dev.dodo.library.core.utilies.ResultHelper;
import dev.dodo.library.dto.request.borrowing.BorrowingSaveRequest;
import dev.dodo.library.dto.request.borrowing.BorrowingUpdateRequest;
import dev.dodo.library.dto.response.CursorResponse;
import dev.dodo.library.dto.response.borrowing.BorrowingResponse;
import dev.dodo.library.entities.Book;
import dev.dodo.library.entities.Borrowing;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/borrowings")
public class BorrowingController {
    private final BorrowingManager borrowingManager;
    private final IModelMapperService modelMapper;
    private final BookManager bookManager;

    public BorrowingController(BorrowingManager borrowingManager, IModelMapperService modelMapper, BookManager bookManager) {
        this.borrowingManager = borrowingManager;
        this.modelMapper = modelMapper;
        this.bookManager = bookManager;
    }

    @PostMapping
    public ResultData<BorrowingResponse> save(@Valid @RequestBody BorrowingSaveRequest borrowingSaveRequest){
        Borrowing saveBorrowing = this.modelMapper.forRequest().map(borrowingSaveRequest, Borrowing.class);
        saveBorrowing.setId(0);
        Book book = this.bookManager.findById(borrowingSaveRequest.getBookId());
        saveBorrowing.setBook(book);
        this.borrowingManager.save(saveBorrowing);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBorrowing, BorrowingResponse.class));
    }

    @GetMapping("/{id}")
    public ResultData<BorrowingResponse> findById(@PathVariable("id") int id){
        Borrowing borrowing = this.borrowingManager.findById(id);
        return ResultHelper.ok(this.modelMapper.forResponse().map(borrowing, BorrowingResponse.class));
    }

    @GetMapping()
    public ResultData<CursorResponse<BorrowingResponse>> cursor(
            @RequestParam(value = "page", defaultValue = "0" , required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ){
        Page<Borrowing> borrowingPage = this.borrowingManager.cursor(page, size);
        Page<BorrowingResponse> borrowingResponse = borrowingPage.map(borrowing -> this.modelMapper.forResponse().map(borrowing, BorrowingResponse.class));
        return ResultHelper.cursor(borrowingResponse);
    }

    @PutMapping
    public ResultData<BorrowingResponse> update(@Valid @RequestBody BorrowingUpdateRequest borrowingUpdateRequest){
        Borrowing updatedBorrowing = this.modelMapper.forRequest().map(borrowingUpdateRequest, Borrowing.class);
        Book book = this.bookManager.findById(borrowingUpdateRequest.getBookId());
        updatedBorrowing.setBook(book);
        this.borrowingManager.update(updatedBorrowing);
        return ResultHelper.ok(this.modelMapper.forResponse().map(updatedBorrowing, BorrowingResponse.class));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") int id){
        this.borrowingManager.delete(id);
        return ResultHelper.successResult();
    }

}
