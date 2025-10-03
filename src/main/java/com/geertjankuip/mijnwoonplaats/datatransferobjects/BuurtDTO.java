package com.geertjankuip.mijnwoonplaats.datatransferobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BuurtDTO {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("WijkenEnBuurten")
    private String wijkenEnBuurten;

    @JsonProperty("Gemeentenaam_1")
    private String gemeentenaam_1;

    @JsonProperty("MeestVoorkomendePostcode_123")
    private String meestVoorkomendePostcode_123;

    @JsonProperty("AantalInwoners_5")
    private Integer aantalInwoners_5;

    @JsonProperty("k_0Tot15Jaar_8")
    private Integer k_0Tot15Jaar_8;

    @JsonProperty("k_65JaarOfOuder_12")
    private Integer k_65JaarOfOuder_12;

    @JsonProperty("GemiddeldInkomenPerInwoner_81")
    private Double gemiddeldInkomenPerInwoner_81;

    @JsonProperty("AfstandTotGroteSupermarkt_116")
    private Double afstandTotGroteSupermarkt_116;

    @JsonProperty("AfstandTotSchool_118")
    private Double afstandTotSchool_118;

    @JsonProperty("MateVanStedelijkheid_125")
    private Integer mateVanStedelijkheid_125;

    private String stripWhitespace(String str){
        if (str==null) return null;
        return str.strip();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = stripWhitespace(id);
    }

    public String getWijkenEnBuurten() {
        return wijkenEnBuurten;
    }

    public void setWijkenEnBuurten(String wijkenEnBuurten) {
        this.wijkenEnBuurten = stripWhitespace(wijkenEnBuurten);
    }

    public String getGemeentenaam_1() {
        return gemeentenaam_1;
    }

    public void setGemeentenaam_1(String gemeentenaam_1) {
        this.gemeentenaam_1 = stripWhitespace(gemeentenaam_1);
    }

    public String getMeestVoorkomendePostcode_123() {
        return meestVoorkomendePostcode_123;
    }

    public void setMeestVoorkomendePostcode_123(String meestVoorkomendePostcode_123) {
        this.meestVoorkomendePostcode_123 = stripWhitespace(meestVoorkomendePostcode_123);
    }

    public Integer getAantalInwoners_5() {
        return aantalInwoners_5;
    }

    public void setAantalInwoners_5(Integer aantalInwoners_5) {
        this.aantalInwoners_5 = aantalInwoners_5;
    }

    public Integer getK_0Tot15Jaar_8() {
        return k_0Tot15Jaar_8;
    }

    public void setK_0Tot15Jaar_8(Integer k_0Tot15Jaar_8) {
        this.k_0Tot15Jaar_8 = k_0Tot15Jaar_8;
    }

    public Integer getK_65JaarOfOuder_12() {
        return k_65JaarOfOuder_12;
    }

    public void setK_65JaarOfOuder_12(Integer k_65JaarOfOuder_12) {
        this.k_65JaarOfOuder_12 = k_65JaarOfOuder_12;
    }

    public Double getGemiddeldInkomenPerInwoner_81() {
        return gemiddeldInkomenPerInwoner_81;
    }

    public void setGemiddeldInkomenPerInwoner_81(Double gemiddeldInkomenPerInwoner_81) {
        this.gemiddeldInkomenPerInwoner_81 = gemiddeldInkomenPerInwoner_81;
    }

    public Double getAfstandTotGroteSupermarkt_116() {
        return afstandTotGroteSupermarkt_116;
    }

    public void setAfstandTotGroteSupermarkt_116(Double afstandTotGroteSupermarkt_116) {
        this.afstandTotGroteSupermarkt_116 = afstandTotGroteSupermarkt_116;
    }

    public Double getAfstandTotSchool_118() {
        return afstandTotSchool_118;
    }

    public void setAfstandTotSchool_118(Double afstandTotSchool_118) {
        this.afstandTotSchool_118 = afstandTotSchool_118;
    }

    public Integer getMateVanStedelijkheid_125() {
        return mateVanStedelijkheid_125;
    }

    public void setMateVanStedelijkheid_125(Integer mateVanStedelijkheid_125) {
        this.mateVanStedelijkheid_125 = mateVanStedelijkheid_125;
    }
}
