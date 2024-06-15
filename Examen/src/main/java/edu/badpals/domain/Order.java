package edu.badpals.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_orders")
public class Order {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ord_id")
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "ord_wizard")
    private Wizard wizard; 

    @OneToOne
    @JoinColumn(name = "ord_item")
    private MagicalItem item;

    public Order(Wizard wizard, MagicalItem item) {
        this.wizard = wizard;
        this.item = item;
    }

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }

    public MagicalItem getItem() {
        return item;
    }

    public void setItem(MagicalItem item) {
        this.item = item;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((wizard == null) ? 0 : wizard.hashCode());
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (id != other.id)
            return false;
        if (wizard == null) {
            if (other.wizard != null)
                return false;
        } else if (!wizard.equals(other.wizard))
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", wizard=" + wizard + ", item=" + item + "]";
    }

    
}
