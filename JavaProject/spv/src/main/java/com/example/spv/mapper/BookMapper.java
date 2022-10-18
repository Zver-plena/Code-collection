package com.example.spv.mapper;

import com.example.spv.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author mrl
 * @data 2022/8/13
 */

@Mapper
public interface BookMapper {

    List<Book> queryAllBooks();
}
