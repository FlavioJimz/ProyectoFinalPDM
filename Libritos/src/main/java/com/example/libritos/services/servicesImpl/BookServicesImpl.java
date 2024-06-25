package com.example.libritos.services.servicesImpl;


import com.example.libritos.models.DTOS.SaveBookDTO;
import com.example.libritos.models.entities.Book;
import com.example.libritos.repositories.BookRepository;
import com.example.libritos.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServicesImpl implements BookServices {

    @Autowired
    BookRepository bookRepository;

    @Override
    public void save(SaveBookDTO info) throws Exception {
        Book newBook = new Book(info.getTitle(), info.getAuthor(), info.getGenre(), info.getDescription(), info.getRating());

        bookRepository.save(newBook);
    }

    @Override
    public void deleteByTitle(String title) throws Exception {
        Book book = bookRepository.findByTitle(title);

        if (book == null){
            throw new Exception("Book not found");
        }

        bookRepository.delete(book);
    }

    @Override
    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }
}
