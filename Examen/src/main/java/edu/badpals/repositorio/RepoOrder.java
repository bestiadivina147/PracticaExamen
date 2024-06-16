package edu.badpals.repositorio;

import edu.badpals.domain.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RepoOrder implements PanacheRepository<Order> {
    
}
