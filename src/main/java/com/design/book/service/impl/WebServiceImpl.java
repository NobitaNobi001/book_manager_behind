package com.design.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.design.book.entity.Book;
import com.design.book.mapper.BookMapper;
import com.design.book.service.WebService;
import com.design.book.util.SpringBeanUtils;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import java.util.List;

/**
 * @author ezuy
 * @date 21/5/26 10:41
 */
@Service
@javax.jws.WebService(name = "WebService",targetNamespace = "http://192.168.43.194:9000")
public class WebServiceImpl extends ServiceImpl<BookMapper, Book> implements WebService {

    @WebResult()
    @WebMethod
    @Override
    public List<Book> selectAllBooks() {

        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("flag2", false);

        BookMapper bookMapper = SpringBeanUtils.getBean(BookMapper.class);

        List<Book> books = bookMapper.selectList(queryWrapper);

        books.forEach((item) -> item.setFlag2(true));

        return books;
    }
}