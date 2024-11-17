package com.mundia.Books.web;


import com.mundia.Books.dtos.BookDTO;
import com.mundia.Books.dtos.BookReq;
import com.mundia.Books.entities.Book;
import com.mundia.Books.services.BookService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    final BookService bookService;

    @PostMapping("/add")
    public BookDTO addBook(@RequestBody BookDTO bookDTO){
        return bookService.createBook(bookDTO);
    }

    @GetMapping("/{id}")
    public  BookDTO getBook(@PathVariable Long id){
        return bookService.getBook(id);
    }

    @GetMapping()
    public List<BookDTO> getAllBooks(){
        return bookService.getAllBook();
    }

    @PostMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id,@RequestBody BookDTO bookDTO){
        return bookService.updateBook(id,bookDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }

}
