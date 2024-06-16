package edu.badpals.repositorio;

import java.util.Optional;
import java.util.List;

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
    public List<MagicalItem> loadItems(String name){
        return this.repoItem.list("name", name);
    }
    public Optional<MagicalItem>loadItem(MagicalItem item){
        return this.loadItems(item.getName()).stream().filter(mi -> mi.getName().equals(item.getName())&&
                                                                    mi.getQuality()==item.getQuality()&&
                                                                    mi.getType().equals(item.getType())
        
        ).findFirst();
}
}
