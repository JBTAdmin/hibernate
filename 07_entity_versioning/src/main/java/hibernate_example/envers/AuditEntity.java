package hibernate_example.envers;

import javax.persistence.Entity;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

/**
 * Stores the basic revision information (revision numnber, timestamp) with a custom value of the username who was executing the change.
 *
 * @author GHajba
 *
 */
@Entity
@RevisionEntity(AuditRevisionListener.class)
public class AuditEntity extends DefaultRevisionEntity {

    private static final long serialVersionUID = -7963967170829486158L;

    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
