package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import tutorial.core.models.entities.dtmc.Trade;

import java.util.Date;

public class TradeResource extends ResourceSupport {

    Integer amount = 0;

    Double price = 0.0;

    Double cost = 0.0;

    Date tradeDate;

    private Long rid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
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

    public Trade toTrade() {
        Trade stock = new Trade();
        stock.setAmount(amount);
        stock.setCost(cost);
        stock.setPrice(price);
        stock.setTradeDate(tradeDate);
        return stock;
    }
}
