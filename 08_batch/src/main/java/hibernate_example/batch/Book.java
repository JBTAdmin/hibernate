package hibernate_example.batch;

import java.text.MessageFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author GHajba
 *
 */
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    private Long id;
    private String isbn;
    private String title;
    private String authors;

    @Temporal(TemporalType.DATE)
    @Column(name = "PUBLISHED_DATE")
    private Date published;

    Book() {
    }

    public Book(String isbn, String title, Date published, String authors) {
        this.isbn = isbn;
        this.title = title;
        this.published = published;
        this.authors = authors;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return MessageFormat.format("{0} by {1} (ISBN: {2}), published {3}", this.title, this.authors, this.isbn, this.published);
    }
}