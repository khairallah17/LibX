package com.mundia.Books.dtos;

import com.mundia.Books.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private Category category;
    private String isbn;

}
