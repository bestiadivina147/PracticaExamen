package edu.badpals.repositorio;

import edu.badpals.domain.MagicalItem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RepoItem implements PanacheRepository<MagicalItem> {
    
}
