package hibernate_example.envers;

import java.text.MessageFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

/**
 * Base entity which gets audited.
 *
 * If you have {@link Audited} on your class all fields are audited not just those who have the Audited annotation.
 *
 * In this class every field is audited. If you remove the annotation from the class, only title and authors will be audited.
 *
 * @author GHajba
 *
 */
@Entity
@Table(name = "BOOKS")
@Audited
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String isbn;
    @Audited
    private String title;
    @Audited
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