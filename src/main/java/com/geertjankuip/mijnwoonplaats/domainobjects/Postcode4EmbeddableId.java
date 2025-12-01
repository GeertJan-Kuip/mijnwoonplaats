package com.geertjankuip.mijnwoonplaats.domainobjects;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Postcode4EmbeddableId implements Serializable {

    private String pdokPostcode4;
    private String pdokWoonplaatscode;

    public Postcode4EmbeddableId() {}

    public Postcode4EmbeddableId(String pdokPostcode4, String pdokWoonplaatscode) {
        this.pdokPostcode4 = pdokPostcode4;
        this.pdokWoonplaatscode = pdokWoonplaatscode;
    }

    public String getPdokPostcode4() {
        return pdokPostcode4;
    }

    public String getPdokWoonplaatscode() {
        return pdokWoonplaatscode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Postcode4EmbeddableId that)) return false;
        return Objects.equals(pdokPostcode4, that.pdokPostcode4) &&
                Objects.equals(pdokWoonplaatscode, that.pdokWoonplaatscode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pdokPostcode4, pdokWoonplaatscode);
    }
}
