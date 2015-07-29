package hibernate_example.batch;

import java.util.Date;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * @author GHajba
 *
 */
public class BatchMain {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration().configure();

        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        final SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        final Session session = sessionFactory.openSession();
        System.out.println(session.createQuery("from Book b").list().size());
        try {
            session.beginTransaction();
            final Random random = new Random();
            for (int i = 0; i < 10_000_000; i++) {
                final Book book = new Book("9781617291999", "Java 8 in Action", new Date(), "Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft");
                book.setId(random.nextLong());
                session.save(book);
                System.out.println(i);
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                    session.getTransaction().commit();
                    session.beginTransaction();
                }
                if (i >= 22) {
                    throw new RuntimeException("boom");
                }
            }
            session.getTransaction().commit();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        System.out.println(session.createQuery("from Book b").list().size());

        session.close();
        sessionFactory.close();

    }
}
