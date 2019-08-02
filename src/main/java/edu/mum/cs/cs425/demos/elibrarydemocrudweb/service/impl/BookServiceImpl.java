package edu.mum.cs.cs425.demos.elibrarydemocrudweb.service.impl;

import edu.mum.cs.cs425.demos.elibrarydemocrudweb.model.Book;
import edu.mum.cs.cs425.demos.elibrarydemocrudweb.repository.BookRepository;
import edu.mum.cs.cs425.demos.elibrarydemocrudweb.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository repository;

	@Override
	public Iterable<Book> getAllBooks() {
		return repository.findAll();
	}

	@Override
	public Iterable<Book> getAllBooksSortedByTitle() {
		return repository.findAll(Sort.by("title"));
	}

	@Override
	public Book saveBook(Book book) {
		return repository.save(book);
	}

	@Override
	public Book getBookById(Integer bookId) {
		return repository.findById(bookId).orElse(null);
	}

	@Override
	public void deleteBookById(Integer bookId) {
		repository.deleteById(bookId);
	}

	@Override
	public Page<Book> getAllBooksPaged(Integer page) {
		return repository.findAll(PageRequest.of(page, 2, Sort.by("title")));
	}

	@Override
	public Page<Book> searchAllBooksPaged(String query, Integer page) {
		ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("isbn", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("publisher", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		Example<Book> example = Example.of(new Book(query, query, query), customExampleMatcher);
		return repository.findAll(example, PageRequest.of(page, 2, Sort.by("title")));
	}

}
