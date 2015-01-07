package tutorial.core.models.entities.dtmc;

import tutorial.core.models.entities.Account;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Stock extends Instrument implements Asset {

    String description;

    @OneToOne
    private Account owner;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;
        if (!super.equals(o)) return false;

        Stock stock = (Stock) o;

        if (!description.equals(stock.description)) return false;
        if (!owner.equals(stock.owner)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        return result;
    }
}
