package com.lg.entity.mappers;

import com.lg.entity.Author;

import java.util.List;

/**
 * Created by liuguo on 2017/3/27.
 */
public interface AuthorMapper {
    Author selectAuthor(int id);

    void insertAuthor(Author author);
    void insertAuthor(List<Author> authors);

    int deleteAuthor(int id);

    int updateAuthor(Author author);
}
