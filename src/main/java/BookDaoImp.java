import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImp {

    private JdbcTemplate jdbcTemplate;

    public BookDaoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Book book) {
        String query = "INSERT INTO book (id, name, price) VALUES (?, ?, ?);";
        Object[] data = new Object[] {
                book.getId(), book.getName(), book.getPrice() };

        jdbcTemplate.update(query, data);
    }

    public void update(int id, Book book) {
        // TODO: add code to update book
    }

    public void deleteById(int id) {
        // TODO: add code to delete book
    }

    // TO BE CONTINUED
}
