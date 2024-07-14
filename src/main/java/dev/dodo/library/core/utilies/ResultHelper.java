package dev.dodo.library.core.utilies;

import dev.dodo.library.core.result.Result;
import dev.dodo.library.core.exceptions.NotFoundException;
import dev.dodo.library.core.utilies.Msg;
import dev.dodo.library.core.result.ResultData;
import org.springframework.data.domain.Page;
import dev.dodo.library.dto.response.CursorResponse;

public class ResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, Msg.SAVE_SUCCESS, "201", data);
    }

    public static <T> ResultData<T> validation(T data) {
        return new ResultData<>(false, Msg.VALIDATE_ERROR, "400", data);
    }

    public static <T> ResultData<T> ok(T data) {
        return new ResultData<>(true, Msg.OK, "200", data);
    }

    public static Result successResult(){
        return new Result(true, Msg.DELETE_SUCCESS, "200");
    }

    public static Result notFound(String msg) {
        return new Result(false, msg, "404");
    }

    public static <T> ResultData<CursorResponse<T>> cursor(Page<T> pageData){
        CursorResponse<T> cursor = new CursorResponse<>();
        cursor.setItems(pageData.getContent());
        cursor.setPageNumber(pageData.getNumber());
        cursor.setPageSize(pageData.getSize());
        cursor.setTotalElements(pageData.getTotalElements());
        return ResultHelper.ok(cursor);
    }

    public static <T> ResultData<T> updated(T data) {
        return new ResultData<>(true, Msg.UPDATE_SUCCESS, "200", data);
    }
}
