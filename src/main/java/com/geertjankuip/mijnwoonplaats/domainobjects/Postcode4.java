package com.geertjankuip.mijnwoonplaats.domainobjects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "postcodes4", uniqueConstraints = @UniqueConstraint(columnNames = {"pdok_postcode4", "pdok_woonplaatscode"}))
public class Postcode4 {

    @EmbeddedId
    private Postcode4EmbeddableId id;

    public Postcode4() {}

    public Postcode4(String pdokPostcode4, String pdokWoonplaatscode) {
        this.id = new Postcode4EmbeddableId(pdokPostcode4, pdokWoonplaatscode);
    }

    public Postcode4EmbeddableId getId() {
        return id;
    }



    public void setId(Postcode4EmbeddableId id) {
        this.id = id;
    }

    public String getPdokPostcode4() {
        return id.getPdokPostcode4();
    }

    public String getPdokWoonplaatscode() {
        return id.getPdokWoonplaatscode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Postcode4)) return false;
        Postcode4 other = (Postcode4) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
