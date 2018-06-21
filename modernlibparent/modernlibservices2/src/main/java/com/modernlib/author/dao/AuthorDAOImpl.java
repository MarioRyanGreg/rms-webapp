package com.modernlib.author.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.modernlib.author.dto.Author;

public class AuthorDAOImpl implements AuthorDAO {

	public List<Author> getAuthors() throws Exception {
		List<Author> authors = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = DataSourceFactory.getConnection();

			// create sql statement
			String sql = "SELECT * FROM author ORDER BY AuthorName";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process the resultset
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("Id");
				String authorName = myRs.getString("AuthorName");
				String email = myRs.getString("Email");
				String createdBy = myRs.getString("CreatedBy");
				String lastModifiedBy = myRs.getString("LastModifiedBy");

				// create new author object
				Author tempAuthor = new Author(id, authorName, email, createdBy, lastModifiedBy);

				// add it to the list of authors
				authors.add(tempAuthor);
			}

			return authors;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void addAuthor(Author theAuthor) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		String sql = null;

		try {
			// get db connection
			myConn = DataSourceFactory.getConnection();

			// create sql for insert
			sql = "INSERT INTO author" + "(AuthorName, Email, CreatedTime, CreatedBy, RowStatus) "
					+ "VALUES(?, ?, now(), ?, 0)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the author
			myStmt.setString(1, theAuthor.getAuthorName());
			myStmt.setString(2, theAuthor.getEmail());
			myStmt.setString(3, theAuthor.getCreatedBy());

			// execute sql insert
			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}

	}

	public Author getAuthor(String theAuthorId) throws Exception {
		Author theAuthor = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int authorId;

		try {
			// convert author id to int
			authorId = Integer.parseInt(theAuthorId);

			// get connection to database
			myConn = DataSourceFactory.getConnection();

			// create sql to get selected author
			String sql = "select * from author where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, authorId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrieve data from result set row
			if (myRs.next()) {
				String authorName = myRs.getString("AuthorName");
				String email = myRs.getString("Email");
				String createdBy = myRs.getString("CreatedBy");
				String lastModifiedBy = myRs.getString("lastModifiedBy");

				// use the authorId during construction
				theAuthor = new Author(authorId, authorName, email, createdBy, lastModifiedBy);
			} else {
				throw new Exception("Could not find author id: " + authorId);
			}

			return theAuthor;
		} finally {
			// clean up jdbc objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateAuthor(Author theAuthor) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = DataSourceFactory.getConnection();

			// create SQL update statement
			String sql = "update author " + "set AuthorName=?, Email=?, LastModifiedTime=now(), LastModifiedBy=? "
					+ "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theAuthor.getAuthorName());
			myStmt.setString(2, theAuthor.getEmail());
			myStmt.setString(3, theAuthor.getLastModifieBy());
			myStmt.setInt(4, theAuthor.getId());

			// execute SQL Statement
			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}
	}

	public void deleteAuthor(String theAuthorId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// convert student id to int
			int authorId = Integer.parseInt(theAuthorId);

			// get connection to database
			myConn = DataSourceFactory.getConnection();

			// create sql to delete student
			String sql = "delete from author where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, authorId);

			// execute sql statement
			myStmt.execute();
		} finally {
			// clean up JDBC Code
			close(myConn, myStmt, null);
		}
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private static class SingletonHelper {
		private static final AuthorDAOImpl INSTANCE = new AuthorDAOImpl();
	}

	public static AuthorDAO getInstance() {
		return SingletonHelper.INSTANCE;
	}
}
