package com.modernlib.author.dao;

import java.util.List;

import com.modernlib.author.dto.Author;

public interface AuthorDAO {

	List<Author> getAuthors() throws Exception;

	void addAuthor(Author theAuthor) throws Exception;

	Author getAuthor(String theAuthorId) throws Exception;

	void updateAuthor(Author theAuthor) throws Exception;

	void deleteAuthor(String theAuthorId) throws Exception;
}
