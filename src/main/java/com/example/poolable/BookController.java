package com.example.poolable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private ApplicationContext context;

    @GetMapping
    public ResponseEntity<String> getRandomIsbn() {
        final Book book = (Book) this.context.getBean("bookProxyFactoryBean");
        return ResponseEntity.ok(book.getIsbn());
    }
}
