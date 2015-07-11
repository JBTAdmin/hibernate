package hibernate_example;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author GHajba
 *
 */
@Entity
@Table(name = "AUTHORS")
public class Author {

    @Id
    private String name;
    @ManyToMany(mappedBy = "authors")
    private final List<Book> books = new ArrayList<>();

    private Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} has written {1} book(s).", this.name, this.books.size());
    }
}
