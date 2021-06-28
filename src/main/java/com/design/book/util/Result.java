package com.design.book.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ezuy
 * @date 21/5/25 15:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    public static Result success(String message, Object data) {
        return new Result(100, message, data);
    }

    public static Result success(String message) {
        return new Result(100, message, null);
    }


    public static Result fail(Integer code, String message) {
        return new Result(code, message, null);
    }
}