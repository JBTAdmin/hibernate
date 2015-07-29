package hibernate_example.envers;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * @author GHajba
 *
 */
public class EnversMain {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration().configure();

        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        final SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        final Session session = sessionFactory.openSession();
        System.out.println(session.createQuery("from Book b").list().size());
        session.beginTransaction();
        final Book book = new Book("9781617291999", "Java 8 in Action", new Date(), "Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft");
        session.save(book);
        session.getTransaction().commit();

        System.out.println(session.createQuery("from Book b").list().size());

        session.close();
        sessionFactory.close();

    }
}
