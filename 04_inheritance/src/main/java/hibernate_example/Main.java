package hibernate_example;

import hibernate_example.joined.AbstractPerson;
import hibernate_example.joined.Author;
import hibernate_example.joined.Book;
import hibernate_example.joined.Publisher;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * @author GHajba
 *
 */
public class Main {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration().configure();
        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        final SessionFactory factory = configuration.buildSessionFactory(builder.build());
        final Session session = factory.openSession();
        final Publisher publisher = new Publisher();
        publisher.setName("Manning");
        publisher.setEmailAdress("info@manning.com");
        publisher.setTaxId("unknown");
        session.save(publisher);
        final Book book = new Book("9781617291999", "Java 8 in Action", new Date());
        session.beginTransaction();
        Arrays.stream("Raoul-Gabriel Urma,Mario Fusco,Alan Mycroft".split(",")).map(name -> new Author(name)).forEach(author -> {
            author.getBooks().add(book);
            author.setPublisher(publisher);
            session.save(author);
            book.getAuthors().add(author);
        });
        session.save(book);
        session.getTransaction().commit();
        final List<Book> books = session.createCriteria(Book.class).list();
        System.out.println("\n");
        System.out.println(MessageFormat.format("Storing {0} books in the database", books.size()));
        for (final Book b : books) {
            System.out.println(b);
        }
        System.out.println("\n");
        System.out.println(MessageFormat.format("Storing {0} persons in the database.", session.createCriteria(AbstractPerson.class).list()
                .size()));
        session.close();
        factory.close();
    }
}
