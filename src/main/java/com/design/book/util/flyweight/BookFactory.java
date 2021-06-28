package com.design.book.util.flyweight;

import com.design.book.entity.Book;
import com.design.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ezuy
 * @date 21/6/7 11:18
 */
@Component
public class BookFactory {

    @Autowired
    private BookService bookService;

    private Map<Long, Book> pool = new HashMap<>();

    public Book getBook(Long bookId) {
        if (!pool.containsKey(bookId)) {
            pool.put(bookId, bookService.getById(bookId));
        }
        return pool.get(bookId);
    }


}