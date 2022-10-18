package com.example.spv.controller;

import com.example.spv.entity.Book;
import com.example.spv.mapper.BookMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author mrl
 * @data 2022/8/13
 */
@Controller
public class BookController {

    @Autowired
    BookMapper bookMapper;

    @ResponseBody
    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks(){
        return bookMapper.queryAllBooks();
    }

}
