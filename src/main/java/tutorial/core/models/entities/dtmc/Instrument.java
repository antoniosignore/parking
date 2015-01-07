package tutorial.core.models.entities.dtmc;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Instrument {

    @Id
    @GeneratedValue
    private Long id;

    String name;

    public Instrument() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instrument)) return false;
        Instrument that = (Instrument) o;
        if (!id.equals(that.id)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
