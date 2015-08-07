package hibernate_example.pooling;

import java.text.MessageFormat;
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
public class PoolingMain {

    public static void main(String[] args) {

        final Configuration configuration = new Configuration().configure();

        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        final SessionFactory factory = configuration.buildSessionFactory(builder.build());

        final Session session = factory.openSession();

        final Book book = new Book("9781617291999", "Java 8 in Action", new Date(), "Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft");
        session.beginTransaction();

        session.save(book);
        session.getTransaction().commit();
        final List<Book> books = session.createQuery("from Book b").list();
        System.out.println("\n");
        System.out.println(MessageFormat.format("Storing {0} book(s) in the database", books.size()));
        for (final Book b : books) {
            System.out.println(b);
        }

        session.close();
        factory.close();
    }
}
