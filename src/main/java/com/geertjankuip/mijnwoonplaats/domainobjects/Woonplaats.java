package com.geertjankuip.mijnwoonplaats.domainobjects;

import jakarta.persistence.*;

@Entity
@Table(name="woonplaatsen")
public class Woonplaats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="pdok_id")
    private String pdokId;

    @Column(name="weergavenaam") // town, municipality, province
    private String weergavenaam;

    @Column(name="woonplaatscode", unique=true)
    private String woonplaatscode;

    @Column(name="woonplaatsnaam") // town name only
    private String woonplaatsnaam;

    @Column(name="gemeentecode")
    private String gemeentecode;

    @Column(name="gemeentenaam")
    private String gemeentenaam;

    @Column(name="provincienaam")
    private String provincienaam;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPdokId() {
        return pdokId;
    }

    public void setPdokId(String pdokId) {
        this.pdokId = pdokId;
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
