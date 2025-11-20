package com.geertjankuip.mijnwoonplaats.apiclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="myapp.api")
public class ApiUrls {

    private String woonplaatsen;
    private String woonplaatsenrequestparamq;
    private String woonplaatsdetails;
    private String postcodes;
    private String postcodesrequestparamq;
    private String postcodesrequestparamfl;
    private String postcodesrequestparamfq;
    private String postcodesrequestparamrows;
    private String buurten;
    private String buurtenrequestparamselect;
    private String buurtenrequestparamfilter;

    public String getWoonplaatsen() {
        return woonplaatsen;
    }

    public void setWoonplaatsen(String woonplaatsen) {
        this.woonplaatsen = woonplaatsen;
    }

    public String getWoonplaatsenrequestparamq() {
        return woonplaatsenrequestparamq;
    }

    public void setWoonplaatsenrequestparamq(String woonplaatsenrequestparamq) {
        this.woonplaatsenrequestparamq = woonplaatsenrequestparamq;
    }

    public String getWoonplaatsdetails() {
        return woonplaatsdetails;
    }

    public void setWoonplaatsdetails(String woonplaatsdetails) {
        this.woonplaatsdetails = woonplaatsdetails;
    }

    public String getPostcodes() {
        return postcodes;
    }

    public void setPostcodes(String postcodes) {
        this.postcodes = postcodes;
    }

    public String getPostcodesrequestparamq() {
        return postcodesrequestparamq;
    }

    public void setPostcodesrequestparamq(String postcodesrequestparamq) {
        this.postcodesrequestparamq = postcodesrequestparamq;
    }

    public String getPostcodesrequestparamfl() {
        return postcodesrequestparamfl;
    }

    public void setPostcodesrequestparamfl(String postcodesrequestparamfl) {
        this.postcodesrequestparamfl = postcodesrequestparamfl;
    }

    public String getPostcodesrequestparamfq() {
        return postcodesrequestparamfq;
    }

    public void setPostcodesrequestparamfq(String postcodesrequestparamfq) {
        this.postcodesrequestparamfq = postcodesrequestparamfq;
    }

    public String getPostcodesrequestparamrows() {
        return postcodesrequestparamrows;
    }

    public void setPostcodesrequestparamrows(String postcodesrequestparamrows) {
        this.postcodesrequestparamrows = postcodesrequestparamrows;
    }

    public String getBuurten() {
        return buurten;
    }

    public void setBuurten(String buurten) {
        this.buurten = buurten;
    }

    public String getBuurtenrequestparamselect() {
        return buurtenrequestparamselect;
    }

    public void setBuurtenrequestparamselect(String buurtenrequestparamselect) {
        this.buurtenrequestparamselect = buurtenrequestparamselect;
    }

    public String getBuurtenrequestparamfilter() {
        return buurtenrequestparamfilter;
    }

    public void setBuurtenrequestparamfilter(String buurtenrequestparamfilter) {
        this.buurtenrequestparamfilter = buurtenrequestparamfilter;
    }
}
