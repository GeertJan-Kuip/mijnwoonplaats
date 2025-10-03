package com.geertjankuip.mijnwoonplaats.textcreation;

import java.util.List;

public class WoonplaatsData {

    private String woonplaatsCode;
    private Integer nInwoners;
    private Integer n65Plus;
    private Integer n15Min;
    private Double stedelijkheid;
    private Double afstandSchool;
    private Double afstandSupermarkt;

    private String woonplaatsNaam;
    private String gemeenteNaam;
    private String provincienaam;

    private Assessment assessment=null;

    public WoonplaatsData(String woonplaatsCode, Integer nInwoners, Integer n65Plus, Integer n15Min,
                          Double stedelijkheid, Double afstandSchool, Double afstandSupermarkt,
                          String woonplaatsNaam, String gemeenteNaam, String provincienaam) {

        this.woonplaatsCode = woonplaatsCode;
        this.nInwoners = nInwoners;
        this.n65Plus = n65Plus;
        this.n15Min = n15Min;
        this.stedelijkheid = stedelijkheid;
        this.afstandSchool = afstandSchool;
        this.afstandSupermarkt = afstandSupermarkt;
        this.woonplaatsNaam = woonplaatsNaam;
        this.gemeenteNaam = gemeenteNaam;
        this.provincienaam = provincienaam;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public String getWoonplaatsCode() {
        return woonplaatsCode;
    }

    public void setWoonplaatsCode(String woonplaatsCode) {
        this.woonplaatsCode = woonplaatsCode;
    }

    public Integer getnInwoners() {
        return nInwoners;
    }

    public void setnInwoners(Integer nInwoners) {
        this.nInwoners = nInwoners;
    }

    public Integer getN65Plus() {
        return n65Plus;
    }

    public void setN65Plus(Integer n65Plus) {
        this.n65Plus = n65Plus;
    }

    public Integer getN15Min() {
        return n15Min;
    }

    public void setN15Min(Integer n15Min) {
        this.n15Min = n15Min;
    }

    public Double getStedelijkheid() {
        return stedelijkheid;
    }

    public void setStedelijkheid(Double stedelijkheid) {
        this.stedelijkheid = stedelijkheid;
    }

    public Double getAfstandSchool() {
        return afstandSchool;
    }

    public void setAfstandSchool(Double afstandSchool) {
        this.afstandSchool = afstandSchool;
    }

    public Double getAfstandSupermarkt() {
        return afstandSupermarkt;
    }

    public void setAfstandSupermarkt(Double afstandSupermarkt) {
        this.afstandSupermarkt = afstandSupermarkt;
    }

    public String getWoonplaatsNaam() {
        return woonplaatsNaam;
    }

    public void setWoonplaatsNaam(String woonplaatsNaam) {
        this.woonplaatsNaam = woonplaatsNaam;
    }

    public String getGemeenteNaam() {
        return gemeenteNaam;
    }

    public void setGemeenteNaam(String gemeenteNaam) {
        this.gemeenteNaam = gemeenteNaam;
    }

    public String getProvincienaam() {
        return provincienaam;
    }

    public void setProvincienaam(String provincienaam) {
        this.provincienaam = provincienaam;
    }
}
