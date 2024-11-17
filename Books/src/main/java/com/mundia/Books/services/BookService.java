package com.mundia.Books.services;

import com.mundia.Books.dtos.BookDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(Long id,BookDTO bookDTO);
    BookDTO getBook(Long id);
    List<BookDTO> getAllBook();
    void deleteBook(Long id);
}
