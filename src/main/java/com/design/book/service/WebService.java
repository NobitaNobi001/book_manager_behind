package com.design.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.design.book.entity.Book;

import java.util.List;

/**
 * @author ezuy
 * @date 21/5/26 10:26
 */
public interface WebService extends IService<Book> {

    /**
     * 查询所有书籍信息
     * @return
     */
    List<Book> selectAllBooks();
}
