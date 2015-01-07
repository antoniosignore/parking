package tutorial.core.models.entities.dtmc;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

public class Trade implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    Integer amount = 0;

    Double price = 0.0;

    Double cost = 0.0;

    Date tradeDate;

    @ManyToOne
    private PortfolioEntry portfolioEntry;

    public Trade() {
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PortfolioEntry getPortfolioEntry() {
        return portfolioEntry;
    }

    public void setPortfolioEntry(PortfolioEntry portfolioEntry) {
        this.portfolioEntry = portfolioEntry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trade)) return false;

        Trade trade = (Trade) o;

        if (amount != null ? !amount.equals(trade.amount) : trade.amount != null) return false;
        if (cost != null ? !cost.equals(trade.cost) : trade.cost != null) return false;
        if (!id.equals(trade.id)) return false;
        if (portfolioEntry != null ? !portfolioEntry.equals(trade.portfolioEntry) : trade.portfolioEntry != null)
            return false;
        if (price != null ? !price.equals(trade.price) : trade.price != null) return false;
        if (tradeDate != null ? !tradeDate.equals(trade.tradeDate) : trade.tradeDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (tradeDate != null ? tradeDate.hashCode() : 0);
        result = 31 * result + (portfolioEntry != null ? portfolioEntry.hashCode() : 0);
        return result;
    }
}
