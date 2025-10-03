package com.geertjankuip.mijnwoonplaats.domainobjects;

import jakarta.persistence.*;

@Entity
@Table(name="buurten")
public class Buurt {

    @Column(name="cbs_id")
    private String cbsId;

    @Id
    @Column(name="wijken_en_buurten", unique=true)
    private String wijkenEnBuurten;

    @Column(name="gemeentenaam_1")
    private String gemeentenaam1;

    @Column(name="meestvoorkomendepostcode_123")
    private String meestVoorkomendePostcode123;

    @Column(name="aantalinwoners_5")
    private Integer aantalInwoners5;

    @Column(name="k_0tot15jaar_8")
    private Integer k0To15Jaar8;

    @Column(name="k_65jaarofouder_12")
    private Integer k65JaarOfOuder12;

    @Column(name="gemiddeldinkomenperinwoner_81")
    private Double gemiddeldInkomenPerInwoner81;

    @Column(name="afstandtotgrotesupermarkt_116")
    private Double afstandTotGroteSupermarkt116;

    @Column(name="afstandtotschool_118")
    private Double afstandTotSchool118;

    @Column(name="matevanstedelijkheid_125")
    private Integer mateVanStedelijkheid125;

    public String getCbsId() {
        return cbsId;
    }

    public void setCbsId(String cbsId) {
        this.cbsId = cbsId;
    }

    public String getWijkenEnBuurten() {
        return wijkenEnBuurten;
    }

    public void setWijkenEnBuurten(String wijkenEnBuurten) {
        this.wijkenEnBuurten = wijkenEnBuurten;
    }

    public String getGemeentenaam1() {
        return gemeentenaam1;
    }

    public void setGemeentenaam1(String gemeentenaam1) {
        this.gemeentenaam1 = gemeentenaam1;
    }

    public String getMeestVoorkomendePostcode123() {
        return meestVoorkomendePostcode123;
    }

    public void setMeestVoorkomendePostcode123(String meestVoorkomendePostcode123) {
        this.meestVoorkomendePostcode123 = meestVoorkomendePostcode123;
    }

    public Integer getAantalInwoners5() {
        return aantalInwoners5;
    }

    public void setAantalInwoners5(Integer aantalInwoners5) {
        this.aantalInwoners5 = aantalInwoners5;
    }

    public Integer getK0To15Jaar8() {
        return k0To15Jaar8;
    }

    public void setK0To15Jaar8(Integer k0To15Jaar8) {
        this.k0To15Jaar8 = k0To15Jaar8;
    }

    public Integer getK65JaarOfOuder12() {
        return k65JaarOfOuder12;
    }

    public void setK65JaarOfOuder12(Integer k65JaarOfOuder12) {
        this.k65JaarOfOuder12 = k65JaarOfOuder12;
    }

    public Double getGemiddeldInkomenPerInwoner81() {
        return gemiddeldInkomenPerInwoner81;
    }

    public void setGemiddeldInkomenPerInwoner81(Double gemiddeldInkomenPerInwoner81) {
        this.gemiddeldInkomenPerInwoner81 = gemiddeldInkomenPerInwoner81;
    }

    public Double getAfstandTotGroteSupermarkt116() {
        return afstandTotGroteSupermarkt116;
    }

    public void setAfstandTotGroteSupermarkt116(Double afstandTotGroteSupermarkt116) {
        this.afstandTotGroteSupermarkt116 = afstandTotGroteSupermarkt116;
    }

    public Double getAfstandTotSchool118() {
        return afstandTotSchool118;
    }

    public void setAfstandTotSchool118(Double afstandTotSchool118) {
        this.afstandTotSchool118 = afstandTotSchool118;
    }

    public Integer getMateVanStedelijkheid125() {
        return mateVanStedelijkheid125;
    }

    public void setMateVanStedelijkheid125(Integer mateVanStedelijkheid125) {
        this.mateVanStedelijkheid125 = mateVanStedelijkheid125;
    }
}
