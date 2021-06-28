package com.design.book.util.strategy;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.design.book.entity.Book;
import com.design.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author ezuy
 * @date 21/6/8 17:19
 */
@Primary
@Component
public class BookSelectStrategy extends BookStrategy {

    @Autowired
    private BookService bookService;

    @Override
    public Object operation(Book book) {
        Page<Book> page = new Page<>(1, 5);
        IPage<Book> bookIPage = bookService.page(page);
        return bookIPage;

    }
}