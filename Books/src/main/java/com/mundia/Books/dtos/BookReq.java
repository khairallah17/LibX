package com.mundia.Books.dtos;

import com.mundia.Books.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class BookReq {
    private String title;
    private String author;
    private Category category;
    private String isbn;

}

