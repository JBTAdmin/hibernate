package hibernate_example.joined;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * A publisher is a person too because it has a name -- however a publisher has a Tax-ID and the list of their authors.
 *
 * @author GHajba
 *
 */
@Entity
public class Publisher extends AbstractPerson {

    private String taxId;

    @OneToMany(mappedBy = "publisher")
    private final List<Author> authors = new ArrayList<>();

    public String getTaxId() {
        return this.taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public List<Author> getAuthors() {
        return this.authors;
    }
}
