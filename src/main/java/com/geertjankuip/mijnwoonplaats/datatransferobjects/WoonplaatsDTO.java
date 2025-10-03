package com.geertjankuip.mijnwoonplaats.datatransferobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WoonplaatsDTO {

    private String id;
    private String weergavenaam;
    private String woonplaatscode;
    private String woonplaatsnaam;
    private String gemeentecode;
    private String gemeentenaam;
    private String provincienaam;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeergavenaam() {
        return weergavenaam;
    }

    public void setWeergavenaam(String weergavenaam) {
        this.weergavenaam = weergavenaam;
    }

    public String getWoonplaatscode() {
        return woonplaatscode;
    }

    public void setWoonplaatscode(String woonplaatscode) {
        this.woonplaatscode = woonplaatscode;
    }

    public String getWoonplaatsnaam() {
        return woonplaatsnaam;
    }

    public void setWoonplaatsnaam(String woonplaatsnaam) {
        this.woonplaatsnaam = woonplaatsnaam;
    }

    public String getGemeentecode() {
        return gemeentecode;
    }

    public void setGemeentecode(String gemeentecode) {
        this.gemeentecode = gemeentecode;
    }

    public String getGemeentenaam() {
        return gemeentenaam;
    }

    public void setGemeentenaam(String gemeentenaam) {
        this.gemeentenaam = gemeentenaam;
    }

    public String getProvincienaam() {
        return provincienaam;
    }

    public void setProvincienaam(String provincienaam) {
        this.provincienaam = provincienaam;
    }
}
