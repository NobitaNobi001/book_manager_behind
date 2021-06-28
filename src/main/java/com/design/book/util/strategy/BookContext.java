package com.design.book.util.strategy;

import com.design.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ezuy
 * @date 21/6/8 17:31
 */
@Data
@Component
@AllArgsConstructor
public class BookContext {

    @Autowired
    private BookStrategy bookStrategy;
    @Autowired
    private BookSaveStrategy bookSaveStrategy;
    @Autowired
    private BookDeleteStrategy bookDeleteStrategy;
    @Autowired
    private BookUpdateStrategy bookUpdateStrategy;
    @Autowired
    private BookSelectStrategy bookSelectStrategy;

    public Object executeStrategy(Book book){
        return bookStrategy.operation(book);
    }

}