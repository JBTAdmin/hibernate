package hibernate_example;

import java.text.MessageFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO to contain information of books.
 *
 * It follows the JavaBeans convention with the private fields and getters and setters for those fields.
 *
 * The no-argument constructor is a JavaBean convention too -- however it is a requirement for persistent classes because Hibernate uses the
 * Java Reflection API to construct the objects.
 *
 * @author GHajba
 *
 */
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    private String isbn;
    private String title;
    private String author;

    @Temporal(TemporalType.DATE)
    @Column(name = "PUBLISHED_DATE")
    private Date published;

    private Book() {
    }

    public Book(String isbn, String title, String author, Date published) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPublished() {
        return this.published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} by {1} (ISBN: {2}), published {3}", this.title, this.author, this.isbn, this.published);
    }
}
