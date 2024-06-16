package edu.badpals.repositorio;

import java.util.Optional;
import java.util.List;

import edu.badpals.domain.MagicalItem;
import edu.badpals.domain.Order;
import edu.badpals.domain.Person;
import edu.badpals.domain.Wizard;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class Repositorio {
    @Inject
    RepoWizard repoWizard;
    @Inject
    RepoItem repoItem;
    @Inject
    RepoOrder repoOrder;

    public Optional<Wizard> loadWizard(String name) {
        return this.repoWizard.findByIdOptional(name);

    }

    public Optional<MagicalItem> loadItem(String name) {
        return this.repoItem.find("name", name).firstResultOptional();
    }

    public List<MagicalItem> loadItems(String name) {
        return this.repoItem.list("name", name);
    }

    public Optional<MagicalItem> loadItem(MagicalItem item) {
        return this.loadItems(item.getName()).stream().filter(  mi -> mi.getName().equals(item.getName()) &&
                                                                mi.getQuality() == item.getQuality() &&
                                                                mi.getType().equals(item.getType())

        ).findFirst();
    }
    public Optional<Order> placeOrder(String name_wizard,String name_item){
        Order orden = null;
        Optional<MagicalItem> item=this.repoItem.find("name = ?1", name_item).firstResultOptional();
        Optional<Wizard> wizard = this.repoWizard.findByIdOptional(name_wizard);
        if(item.isPresent() && wizard.isPresent() && !wizard.get().getPerson().equals(Person.MUDBLOOD)){
             orden = new Order();
            orden.setItem(item.get());
            orden.setWizard(wizard.get());
            this.repoOrder.persist(orden);
        }
        return Optional.ofNullable(orden);
    }
    public void createItem(String name,int quality,String type){
        MagicalItem item = new MagicalItem(name, quality, type);
        this.repoItem.persist(item);
    }
    public void createItems(List<MagicalItem> items){
        this.repoItem.persist(items);
    }
    public void deleteItem(MagicalItem item){
        Optional<MagicalItem> result = loadItem(item);
        if(result.isPresent()){
            this.repoItem.delete(result.get());
        }
    }

}
