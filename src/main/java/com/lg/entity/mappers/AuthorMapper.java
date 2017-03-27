package com.lg.entity.mappers;

import com.lg.entity.Author;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.ResultHandler;

import java.util.*;

/**
 * Created by liuguo on 2017/3/27.
 */
public interface AuthorMapper {

    List<Author> selectAllAuthors();

    Set<Author> selectAllAuthorsSet();

    Vector<Author> selectAllAuthorsVector();

    LinkedList<Author> selectAllAuthorsLinkedList();

    Author[] selectAllAuthorsArray();

    void selectAllAuthors(ResultHandler handler);

    Author selectAuthor(int id);

    LinkedHashMap<String, Object> selectAuthorLinkedHashMap(int id);

    void selectAuthor(int id, ResultHandler handler);

    @Select("select")
    void selectAuthor2(int id, ResultHandler handler);

    void insertAuthor(Author author);

    void insertAuthor(List<Author> authors);

    int deleteAuthor(int id);

    int updateAuthor(Author author);
}
