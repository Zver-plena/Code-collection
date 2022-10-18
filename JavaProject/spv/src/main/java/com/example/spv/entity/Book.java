package com.example.spv.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author mrl
 * @data 2022/8/13
 */

@Data
public class Book {
    private Integer id;
    private String name;
    private String author;
    private String publish;
}
