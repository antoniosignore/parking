package tutorial.core.models.entities.dtmc;


import javax.persistence.*;

@Entity
public class PortfolioEntry {
    @Id
    @GeneratedValue
    private Long id;

    Integer amount = 0;

    @ManyToOne
    private Portfolio portfolio;

    @OneToOne
    private Instrument instrument;

    public PortfolioEntry() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortfolioEntry)) return false;

        PortfolioEntry that = (PortfolioEntry) o;

        if (!amount.equals(that.amount)) return false;
        if (!id.equals(that.id)) return false;
        if (!instrument.equals(that.instrument)) return false;
        if (!portfolio.equals(that.portfolio)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + portfolio.hashCode();
        result = 31 * result + instrument.hashCode();
        return result;
    }
}
