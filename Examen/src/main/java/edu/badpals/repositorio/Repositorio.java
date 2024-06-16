package edu.badpals.repositorio;

import java.util.Optional;

import edu.badpals.domain.MagicalItem;
import edu.badpals.domain.Wizard;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Repositorio {
    @Inject
    RepoWizard repoWizard;
    @Inject
    RepoItem repoItem;
    public Optional<Wizard> loadWizard(String name){
       return this.repoWizard.findByIdOptional(name);

    }
    public Optional<MagicalItem> loadItem(String name){
        return this.repoItem.find("name", name).firstResultOptional();
    }
}
