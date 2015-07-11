package hibernate_example.tableperclass;

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
 * The Table per class strategy does not need any discriminators because every entity gets into a separate table containing every
 * information.
 *
 * @author GHajba
 *
 */
@Entity
@Table(name = "PERSONS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
