package com.mundia.Books.services;

import com.mundia.Books.dtos.BookDTO;
import com.mundia.Books.entities.Book;
import com.mundia.Books.mappers.BookMapper;
import com.mundia.Books.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImp implements BookService{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;


    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book saveBook = bookRepository.save(book);
        return bookMapper.toDto(saveBook);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()){
            Book book = bookOptional.get();
            book.setTitle(bookDTO.getTitle());
            book.setAuthor(bookDTO.getAuthor());
            book.setCategory(bookDTO.getCategory());
            book.setIsbn(bookDTO.getIsbn());
            Book updateBook = bookRepository.save(book);
            return bookMapper.toDto(updateBook);
        }else{
            throw new RuntimeException("this book  with the id"+id+"is not existe");
        }
    }

    @Override
    public BookDTO getBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("the book with the id"+id+"is not found"));
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDTO> getAllBook() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("this book with id "+id+" is not existe"));
        bookRepository.delete(book);
    }
}
