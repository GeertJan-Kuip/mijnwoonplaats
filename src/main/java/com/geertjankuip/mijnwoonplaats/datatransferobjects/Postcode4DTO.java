package com.geertjankuip.mijnwoonplaats.datatransferobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.geertjankuip.mijnwoonplaats.domainobjects.Postcode4;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Postcode4DTO {

    @JsonProperty("postcode")
    private String postcode;

    @JsonProperty("woonplaatscode")
    private String woonplaatscode;

    private String stripWhitespace(String str){
        if (str==null) return null;
        return str.strip();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = stripWhitespace(postcode).substring(0,4);
    }

    public String getWoonplaatscode() {
        return woonplaatscode;
    }

    public void setWoonplaatscode(String woonplaatscode) {
        this.woonplaatscode = stripWhitespace(woonplaatscode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Postcode4DTO)) return false;
        Postcode4DTO other = (Postcode4DTO) o;
        return (postcode.equals(other.postcode) && woonplaatscode.equals(other.woonplaatscode));
    }

    @Override
    public int hashCode() {
        return Objects.hash(postcode, woonplaatscode);
    }
}
