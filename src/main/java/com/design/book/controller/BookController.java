package com.design.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.design.book.entity.Book;
import com.design.book.service.BookService;
import com.design.book.service.impl.WebServiceImpl;
import com.design.book.util.*;
import com.design.book.util.flyweight.BookFactory;
import com.design.book.util.singleton.JaxWsDynamicClientFactory;
import com.design.book.util.state.Activity;
import com.design.book.util.state.SynchronizeState;
import com.design.book.util.strategy.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Endpoint;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ezuy
 * @since 2021-05-25
 */
@Slf4j
@CrossOrigin
@RestController
@Api(tags = "图书信息管理")
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private Activity activity;

    @Autowired
    private BookFactory bookFactory;

    @Autowired
    private BookContext bookContext;


    @PostMapping("/add")
    @ApiOperation("添加单个图书信息")
    public Result addBook(
            @ApiParam(value = "图书信息", required = true)
            @RequestBody Book book) {

        book.setFlag1(activity.getState(false));
        // 设置策略
        bookContext.setBookStrategy(bookContext.getBookSaveStrategy());
        // 执行
        boolean result = (boolean) bookContext.executeStrategy(book);

        if (!result) {
            return Result.fail(100, "添加失败");
        }

        return Result.success("添加成功", book);
    }

    @DeleteMapping("/remove/{bookId}")
    @ApiOperation("删除单本图书信息")
    public Result removeBook(
            @ApiParam(value = "图书id", required = true, example = "100")
            @PathVariable("bookId") Long bookId) {

        bookContext.setBookStrategy(bookContext.getBookDeleteStrategy());
        Book book = new Book();
        book.setBookId(bookId);
        boolean result = (boolean) bookContext.executeStrategy(book);

        if (!result) {
            return Result.fail(100, "删除失败");
        }

        return Result.success("删除成功");
    }

    @DeleteMapping("/remove")
    @ApiOperation("批量删除图书信息")
    public Result removeBooks(
            @ApiParam(value = "图书id数组", required = true)
            @RequestBody Map<String, List<Long>> bookIds
    ) {

        List<Long> bookId = bookIds.get("bookId");

        boolean result = bookService.removeByIds(bookId);

        if (!result) {
            return Result.fail(100, "删除失败");
        }

        return Result.success("删除成功");
    }

    @PutMapping("/update")
    @ApiOperation("修改单本图书信息")
    public Result updateBook(
            @ApiParam(value = "图书信息", required = true)
            @RequestBody Book book) {
        bookContext.setBookStrategy(bookContext.getBookUpdateStrategy());
        boolean result = (boolean) bookContext.executeStrategy(book);

        if (!result) {
            return Result.fail(100, "修改失败");
        }

        return Result.success("修改成功");
    }

    @GetMapping("/list")
    @ApiOperation("分页查询图书信息操作")
    public Result listBooKPages(
            @ApiParam(value = "当前页", required = true, example = "100")
            @RequestParam("current") Long current,
            @ApiParam(value = "页面大小", required = true, example = "100")
            @RequestParam("size") Long size
    ) {

        bookContext.setBookStrategy(bookContext.getBookSelectStrategy());

        Page<Book> bookPages = (Page<Book>) bookContext.executeStrategy(null);

        return Result.success("查询成功", bookPages);
    }

    @GetMapping("/search")
    @ApiOperation("根据图书名模糊查询")
    public Result listBookByName(
            @ApiParam(value = "当前页", required = true, example = "100")
            @RequestParam("current") Long current,
            @ApiParam(value = "页面大小", required = true, example = "100")
            @RequestParam("size") Long size,
            @ApiParam(value = "图书名称", required = true)
            @RequestParam("bookName") String bookName
    ) {
        Page<Book> bookPage = new Page<>(current, size);

        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("book_name", bookName);

        Page<Book> bookPages = bookService.page(bookPage, queryWrapper);

        return Result.success("查询成功", bookPages);
    }

    @GetMapping("/get")
    @ApiOperation("获得单本图书信息")
    public Result getBookById(
            @ApiParam(value = "图书id", required = true, example = "100")
            @RequestParam("bookId") Long bookId
    ) {
        Book book = bookFactory.getBook(bookId);

        if (book == null) {
            return Result.fail(100, "查询失败");
        }

        return Result.success("查询成功", book);
    }

    @GetMapping("/publish")
    @ApiOperation("发布图书信息")
    public Result publishBook() {

        String url = "http://192.168.43.194:9000/WebService";

        try {
            Endpoint.publish(url, new WebServiceImpl());

            activity.setState(new SynchronizeState(activity));

            UpdateWrapper<Book> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("flag1", false).set("flag1", true);
            bookService.update(updateWrapper);

        } catch (Exception e) {
            return Result.fail(200, "发布失败");
        }

        return Result.success("发布成功");
    }

    @GetMapping("/subscribe")
    @ApiOperation("拉取图书信息")
    public Result subscribeBook() {

        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

        Client client = dcf.createClient("http://192.168.43.194:9000/WebService?wsdl");

        Object[] objects;

        List<Book> list;

        try {
            objects = client.invoke("selectAllBooks");
            list = (List<Book>) objects[0];
        } catch (Exception e) {
            return Result.fail(200, "拉取图书信息失败");
        }

        UpdateWrapper<Book> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("flag2", false).set("flag2", true);
        bookService.update(updateWrapper);

        boolean result = bookService.saveBatch(list);

        if (result) {
            return Result.success("同步图书信息到本地成功");
        }
        return Result.fail(200, "同步失败");
    }

}

