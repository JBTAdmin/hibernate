package hibernate_example.joined;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * A person entity contains the main information which is common between Authors and Publishers.
 *
 * The class is abstract, this means you cannot instantiate a person on its own -- you need a subclass.
 *
 * For this type of inheritance no {@link DiscriminatorColumn} or {@link DiscriminatorValue} is needed. It is a legacy Hibernate behavior
 * and you will be shown an error message at startup when you use discriminators but the application can be started.
 *
 * @author GHajba
 *
 */
@Entity
@Table(name = "PERSONS")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractPerson {

    @Id
    private String name;

    private String emailAdress;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAdress() {
        return this.emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

}
