package edu.badpals.servicice;

import java.util.List;
import java.util.Optional;

import edu.badpals.domain.MagicalItem;
import edu.badpals.repositorio.Repositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ServiceItem {
    @Inject
	 Repositorio repo;
    
    Optional<MagicalItem> cargaItem(String name){
        return repo.loadItem(name);
    }
    Optional<MagicalItem> cargaItem(MagicalItem item){
        return repo.loadItem(item);
    }
    List<MagicalItem> cargaItems(String name){
        return repo.loadItems(name);
    }
    Optional<MagicalItem> creaItem(MagicalItem item){
        repo.createItem(item.getName(), item.getQuality(), item.getType());
        return repo.loadItem(item);
    }
    void eliminaItem(MagicalItem item){
        repo.deleteItem(item);
    }
}
