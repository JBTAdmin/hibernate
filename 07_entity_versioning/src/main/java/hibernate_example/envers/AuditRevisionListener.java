package hibernate_example.envers;

import org.hibernate.envers.RevisionListener;

/**
 * @author GHajba
 *
 */
public class AuditRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {

        final AuditEntity auditEntity = (AuditEntity) revisionEntity;
        // normally you would set here the name of the current user
        auditEntity.setUsername("GHajba");

    }

}
