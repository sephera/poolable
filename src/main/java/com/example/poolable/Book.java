package com.example.poolable;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Getter
@Setter
public class Book {
    private static final AtomicInteger COUNTER = new AtomicInteger();
    private final int instanceNumber;

    public Book() {
        this.instanceNumber = COUNTER.incrementAndGet();
        log.info("Create book with ISBN {}", this.instanceNumber);
    }

    public String getIsbn() {
        final String isbn = String.format("ISBN:%s", this.instanceNumber);
        log.info(isbn);
        return isbn;
    }
}
