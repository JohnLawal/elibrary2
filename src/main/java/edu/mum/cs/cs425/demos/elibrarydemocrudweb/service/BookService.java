package edu.mum.cs.cs425.demos.elibrarydemocrudweb.service;

import org.springframework.data.domain.Page;

import edu.mum.cs.cs425.demos.elibrarydemocrudweb.model.Book;

public interface BookService {

	public abstract Iterable<Book> getAllBooks();

	public abstract Iterable<Book> getAllBooksSortedByTitle();

	public abstract Page<Book> getAllBooksPaged(Integer page);

	public abstract Page<Book> searchAllBooksPaged(String query, Integer page);

	public abstract Book saveBook(Book book);

	public abstract Book getBookById(Integer bookId);

	public abstract void deleteBookById(Integer bookId);

}
