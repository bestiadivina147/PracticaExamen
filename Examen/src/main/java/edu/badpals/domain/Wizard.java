package edu.badpals.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_wizards")
public class Wizard {
    public Wizard() {
    }

    @Override
    public String toString() {
        return "Wizard [name=" + name + ", dexterity=" + dexterity + ", person=" + person + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + dexterity;
        result = prime * result + ((person == null) ? 0 : person.hashCode());
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
        Wizard other = (Wizard) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (dexterity != other.dexterity)
            return false;
        if (person != other.person)
            return false;
        return true;
    }

    @Id
    @Column(name = "wizard_name")
    private String name;

    @Column(name = "wizard_dexterity")
    private int dexterity;

    @Column(name = "wizard_person")
    @Enumerated(EnumType.STRING)
    private Person person;

    public Wizard(String name, int dexterity, Person person) {
        this.name = name;
        this.dexterity = dexterity;
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
