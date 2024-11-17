package com.mundia.Books.mappers;

import com.mundia.Books.dtos.BookDTO;
import com.mundia.Books.entities.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

@Component
public class BookMapper {
    public BookDTO toDto(Book book){
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(book,bookDTO);
        return bookDTO;
    }
    public Book toEntity(BookDTO bookDTO){
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        return book;

    }
}
