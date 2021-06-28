package com.design.book.util.strategy;

import com.design.book.entity.Book;
import com.design.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ezuy
 * @date 21/6/8 17:18
 */
@Component
public class BookSaveStrategy extends BookStrategy {

    @Autowired
    private BookService bookService;

    @Override
    public Object operation(Book book) {
        return bookService.save(book);
    }
}