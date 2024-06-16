package edu.badpals.repositorio;

import java.util.Optional;

import edu.badpals.domain.Wizard;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Repositorio {
    @Inject
    RepoWizard repoWizard;
    public Optional<Wizard> loadWizard(String name){
       return this.repoWizard.findByIdOptional(name);

    }
}
