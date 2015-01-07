package tutorial.core.models.entities.dtmc;

import tutorial.core.models.entities.Account;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Portfolio extends Instrument implements Asset, Serializable {

    @OneToOne
    private Account owner;

    public Portfolio() {
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
        if (!(o instanceof Portfolio)) return false;
        if (!super.equals(o)) return false;

        Portfolio portfolio = (Portfolio) o;

        if (!owner.equals(portfolio.owner)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + owner.hashCode();
        return result;
    }
}
