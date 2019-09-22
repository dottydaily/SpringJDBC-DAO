package th.ku;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BookDaoImp implements BookDao {

    private JdbcTemplate jdbcTemplate;

    public BookDaoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Book book) {
        String query = "INSERT INTO book (id, name, price) VALUES (?, ?, ?);";
        Object[] data = new Object[] {
                book.getId(), book.getName(), book.getPrice() };

        String testQuery = "SELECT * FROM book WHERE id = " + book.getId();
        Book testBook = jdbcTemplate.queryForObject(testQuery, new BookRowMapper());

        if (testBook != null) { // already have this in DB
            update(book.getId(), book);
        } else {
            jdbcTemplate.update(query, data);
        }
    }

    public void update(int id, Book book) {
        String query = "UPDATE book SET name = ?, price = ? WHERE id = ?;";
        Object[] data = new Object[] {
                book.getName(), book.getPrice(), id
        };
        jdbcTemplate.update(query, data);
    }

    public void deleteById(int id) {
        String query = "DELETE FROM book WHERE id = ?";
        Object[] data = new Object[] {
                id
        };
        jdbcTemplate.update(query, data);
    }

    public Book findById(int id) {
        String query = "SELECT * FROM book WHERE id = " + id;
        Book book = jdbcTemplate.queryForObject(query, new BookRowMapper());
        return book;
    }

    public List<Book> findAll() {
        String query = "SELECT * FROM book";
        List<Book> books = jdbcTemplate.query(query, new BookRowMapper());
        return books;
    }
}
