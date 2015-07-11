package hibernate_example;

import hibernate_example.joined.Author;
import hibernate_example.joined.Book;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * This class contains methods which query the database to find answers to specific questions.
 *
 * @author GHajba
 *
 */
public class Repository {

    public static Long countBooks(Session session) {
        return (Long) session.createQuery("select count(*) from hibernate_example.joined.Book b").uniqueResult();
    }

    public static Long countPersons(Session session) {
        return (Long) session.createQuery("select count(*) from AbstractPerson p").uniqueResult();
    }

    public static Long countAuthorsWithCriteria(Session session) {
        return (Long) session.createCriteria("hibernate_example.joined.Author").setProjection(Projections.rowCount()).uniqueResult();
    }

    public static List authorsNamedM(Session session) {
        return session.createCriteria(Author.class).add(Restrictions.like("name", "M%")).list();
    }

    public static BigInteger countAuthorsNative(Session session) {
        return (BigInteger) session.createSQLQuery("select count(*) from author").uniqueResult();
    }

    /**
     * This is the default approach how to get a list of Books with HQL or JPQL
     */
    public static List<Book> getBooks(Session session) {
        return session.createQuery("from Book").list();
    }

    /**
     * This version is slightly better because it generates a dynamically type-safe collection.
     *
     * The drawback is that you can have elements of the wrong type in this collection prior creation. This means that when you try to cast
     * an object of the other type you get a runtime exception.
     */
    public static List<Book> getBooksTypeSafer(Session session) {

        return Collections.checkedList(session.createCriteria(Book.class).list(), Book.class);
    }

    /**
     * This is the version where the IDE and the compile does not warn about type safety.
     */
    public static List<Book> getBooksWithCast(Session session) {
        final List<Book> result = new ArrayList<>();
        for (final Object o : session.createQuery("from Book").list()) {
            if (o instanceof Book) {
                result.add((Book) o);
            }
        }
        return result;
    }
}
