package edu.badpals.repositorio;
import edu.badpals.domain.Wizard;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RepoWizard implements PanacheEntityBase<Wizard,String> {
    
}
