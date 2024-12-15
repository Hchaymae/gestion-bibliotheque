package com.library.dao;

import com.library.model.Book;
import com.library.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // Add a new book to the database
    public void addBook(Book book) {
        String sql = "INSERT INTO Book (title, author, publisher, year, isbn, available) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getYear());
            statement.setString(5, book.getIsbn());
            statement.setBoolean(6, book.isAvailable());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Book inserted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting book: " + e.getMessage());
        }
    }

    // Get a book by its ISBN
    public Book getBookByIsbn(String isbn) {
        String sql = "SELECT * FROM Book WHERE isbn = ?";
        Book book = null;

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setYear(resultSet.getInt("year"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setAvailable(resultSet.getBoolean("available"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving book: " + e.getMessage());
        }

        return book;
    }

    // Get a book by its ID
    public Book getBookById(int id) {
        String sql = "SELECT * FROM Book WHERE id = ?";
        Book book = null;

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setYear(resultSet.getInt("year"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setAvailable(resultSet.getBoolean("available"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving book by ID: " + e.getMessage());
        }

        return book;
    }


    public Book findBookByTitle(String title) {
        Book book = null;
        String query = "SELECT * FROM Book WHERE title = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String bookTitle = resultSet.getString("title");
                String author = resultSet.getString("author");
                String publisher = resultSet.getString("publisher");
                int year = resultSet.getInt("year");
                String isbn = resultSet.getString("isbn");
                Boolean available = resultSet.getBoolean("available");
                book = new Book(id, bookTitle, author,publisher, year,isbn,available);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    // Get all Book
    public List<Book> getAllBooks() {
        List<Book> Book = new ArrayList<>();
        String sql = "SELECT * FROM Book";

        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setYear(resultSet.getInt("year"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setAvailable(resultSet.getBoolean("available"));
                Book.add(book);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving Book: " + e.getMessage());
        }

        return Book;
    }

    // Update an existing book
    public void updateBook(Book book) {
        String sql = "UPDATE Book SET title = ?, author = ?, publisher = ?, year = ?, isbn = ?, available = ? WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getYear());
            statement.setString(5, book.getIsbn());
            statement.setBoolean(6, book.isAvailable());
            statement.setInt(7, book.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Book updated successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }

    // Delete a book by its ID
    public void deleteBook(int id) {
        String sql = "DELETE FROM Book WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Book deleted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting book: " + e.getMessage());
        }
    }

    public boolean isBookExists(int bookId) {
        String query = "SELECT COUNT(*) FROM Book WHERE id = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateBookAvailability(int bookId, boolean isAvailable) {
        String query = "UPDATE Book SET available = ? WHERE id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, isAvailable);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
