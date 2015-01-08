package com.parking.core.models.entities;

import javax.persistence.*;

@Entity
public class Connection {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Account initiator;

    @OneToOne
    private Account receiver;

    @OneToOne
    private AccountGroup initiatorGroup;

    @OneToOne
    private AccountGroup receiverGroup;

    private Boolean confirmed;

    public Connection() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getInitiator() {
        return initiator;
    }

    public void setInitiator(Account initiator) {
        this.initiator = initiator;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public AccountGroup getInitiatorGroup() {
        return initiatorGroup;
    }

    public void setInitiatorGroup(AccountGroup initiatorGroup) {
        this.initiatorGroup = initiatorGroup;
    }

    public AccountGroup getReceiverGroup() {
        return receiverGroup;
    }

    public void setReceiverGroup(AccountGroup receiverGroup) {
        this.receiverGroup = receiverGroup;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Connection)) return false;

        Connection that = (Connection) o;

        if (confirmed != null ? !confirmed.equals(that.confirmed) : that.confirmed != null) return false;
        if (!id.equals(that.id)) return false;
        if (initiator != null ? !initiator.equals(that.initiator) : that.initiator != null) return false;
        if (initiatorGroup != null ? !initiatorGroup.equals(that.initiatorGroup) : that.initiatorGroup != null)
            return false;
        if (receiver != null ? !receiver.equals(that.receiver) : that.receiver != null) return false;
        if (receiverGroup != null ? !receiverGroup.equals(that.receiverGroup) : that.receiverGroup != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (initiator != null ? initiator.hashCode() : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        result = 31 * result + (initiatorGroup != null ? initiatorGroup.hashCode() : 0);
        result = 31 * result + (receiverGroup != null ? receiverGroup.hashCode() : 0);
        result = 31 * result + (confirmed != null ? confirmed.hashCode() : 0);
        return result;
    }
}
