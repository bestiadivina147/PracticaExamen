package edu.badpals.repositorio;
import edu.badpals.domain.Wizard;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RepoWizard implements PanacheRepositoryBase<Wizard,String> {
    
}
