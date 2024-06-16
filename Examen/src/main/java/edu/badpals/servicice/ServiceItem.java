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
    
    public Optional<MagicalItem> cargaItem(String name){
        return repo.loadItem(name);
    }
    public Optional<MagicalItem> cargaItem(MagicalItem item){
        return repo.loadItem(item);
    }
    public List<MagicalItem> cargaItems(String name){
        return repo.loadItems(name);
    }
    public Optional<MagicalItem> creaItem(MagicalItem item){
        repo.createItem(item.getName(), item.getQuality(), item.getType());
        return repo.loadItem(item);
    }
    public void eliminaItem(MagicalItem item){
        repo.deleteItem(item);
    }
}
