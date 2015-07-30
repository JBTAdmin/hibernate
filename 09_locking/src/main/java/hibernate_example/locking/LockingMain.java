package hibernate_example.locking;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * @author GHajba
 *
 */
public class LockingMain {

    public static void main(String[] args) {

        final Configuration configuration = new Configuration().configure();

        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        final SessionFactory factory = configuration.buildSessionFactory(builder.build());
        final Session session = factory.openSession();
        final Book book = new Book("9781617291999", "Java 8 in Action", new Date(), "Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft");

        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();

        // final List<Book> books = getBooks(session);
        final List<Book> books = session.createQuery("from Book b").list();
        System.out.println("\n");
        System.out.println(MessageFormat.format("Storing {0} book(s) in the database", books.size()));
        for (final Book b : books) {
            System.out.println(b);
        }

        getBooks(session).stream().forEach(b -> {
            session.load(Book.class, b.getId(), new LockOptions(LockMode.PESSIMISTIC_FORCE_INCREMENT));
            System.out.println(b);
        });
        // uncomment the block below to update the version number for the entities currently saved to the database
        // session.beginTransaction();
        // books.stream().filter(b -> b.getVersion() != null).map(b -> {
        // b.setPublished(new Date());
        // return b;
        // }).forEach(session::saveOrUpdate);
        // session.getTransaction().commit();
        getBooks(session).stream().forEach(System.out::println);
        session.close();
        factory.close();
    }

    private static List<Book> getBooks(Session session) {
        final Query query = session.createQuery("from Book b");
        query.setLockMode("b", LockMode.OPTIMISTIC_FORCE_INCREMENT);
        return query.list();
    }
}
