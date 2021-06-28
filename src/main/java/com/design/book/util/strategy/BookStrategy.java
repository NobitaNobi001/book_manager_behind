package com.design.book.util.strategy;

import com.design.book.entity.Book;
import org.springframework.stereotype.Component;



/**
 * @author ezuy
 * @date 21/6/8 17:13
 */
@Component
public abstract class BookStrategy {

    /**
     *
     * @param book
     * @return
     */
    public abstract Object operation(Book book);

}