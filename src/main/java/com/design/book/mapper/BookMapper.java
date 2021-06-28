package com.design.book.mapper;

import com.design.book.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ezuy
 * @since 2021-05-25
 */
@Component("bookMapper")
public interface BookMapper extends BaseMapper<Book> {

}
