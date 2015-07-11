package hibernate_example.singletable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * A person entity contains the main information which is common between Authors and Publishers: the name.
 *
 * The class is abstract, this means you cannot instantiate a person on its own -- you need a subclass.
 *
 * These examples use the {@link DiscriminatorColumn} and {@link DiscriminatorValue} to override the Hibernate defaults.
 *
 * @author GHajba
 *
 */
@Entity
@Table(name = "PERSONS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PERSON_TYPE")
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
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

}
