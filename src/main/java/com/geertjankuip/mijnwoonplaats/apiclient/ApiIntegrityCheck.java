package com.geertjankuip.mijnwoonplaats.apiclient;


import com.geertjankuip.mijnwoonplaats.domainobjects.Buurt;
import com.geertjankuip.mijnwoonplaats.domainobjects.Postcode4;
import com.geertjankuip.mijnwoonplaats.domainobjects.Woonplaats;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ApiIntegrityCheck {

    private static final Logger log = LoggerFactory.getLogger(ApiIntegrityCheck.class);

    public boolean isCompleteWoonplaatsFetchedList(List<Woonplaats> listWoonplaatsen){

        boolean returnValue = true;

        int nNullsPdokId = 0;
        int nNullsWeergavenaam = 0;
        int nNullsWoonplaatscode = 0;
        int nNullsWoonplaatsnaam = 0;
        int nNullsGemeentecode = 0;
        int nNullsGemeentenaam = 0;
        int nNullsProvincienaam = 0;

        for (Woonplaats wp : listWoonplaatsen){

            if (wp.getPdokId()==null) nNullsPdokId++;
            if (wp.getWeergavenaam()==null) nNullsWeergavenaam++;
            if (wp.getWoonplaatscode()==null) nNullsWoonplaatscode++;
            if (wp.getWoonplaatsnaam()==null) nNullsWoonplaatsnaam++;
            if (wp.getGemeentecode()==null) nNullsGemeentecode++;
            if (wp.getGemeentenaam()==null) nNullsGemeentenaam++;
            if (wp.getProvincienaam()==null) nNullsProvincienaam++;
        }

        if (nNullsPdokId>0){log.warn("{} null values found in fetched Woonplaatsen, field pdokId.", nNullsPdokId); returnValue = false;}
        if (nNullsWeergavenaam>0){log.warn("{} null values found in fetched Woonplaatsen, field weergavenaam.", nNullsWeergavenaam); returnValue = false;}
        if (nNullsWoonplaatscode>0){log.warn("{} null values found in fetched Woonplaatsen, field woonplaatscode.", nNullsWoonplaatscode); returnValue = false;}
        if (nNullsWoonplaatsnaam>0){log.warn("{} null values found in fetched Woonplaatsen, field woonplaatsnaam.", nNullsWoonplaatsnaam); returnValue = false;}
        if (nNullsGemeentecode>0){log.warn("{} null values found in fetched Woonplaatsen, field gemeentecode.", nNullsGemeentecode); returnValue = false;}
        if (nNullsGemeentenaam>0){log.warn("{} null values found in fetched Woonplaatsen, field gemeentenaam.", nNullsGemeentenaam); returnValue = false;}
        if (nNullsProvincienaam>0){log.warn("{} null values found in fetched Woonplaatsen, field provincienaam.", nNullsProvincienaam); returnValue = false;}

        return returnValue;
    }

    public boolean isCompleteBuurtFetchedList(List<Buurt> listBuurten){

        boolean returnValue = true;

        int nNullsCbsId = 0;
        int nNullsWijkenEnBuurten = 0;
        int nNullsGemeentenaam1 = 0;
        int nNullsMeestVoorkomendePostcode123 = 0;
        int nNullsAantalInwoners5 = 0;
        int nNullsK0To15Jaar8 = 0;
        int nNullsK65JaarOfOuder12 = 0;
        int nNullsGemiddeldInkomenPerInwoner81 = 0;
        int nNullsAfstandTotGroteSupermarkt116 = 0;
        int nNullsAfstandTotSchool118 = 0;
        int nNullsMateVanStedelijkheid125 = 0;

        for (Buurt bu : listBuurten){

            if (bu.getCbsId()==null) nNullsCbsId++;
            if (bu.getWijkenEnBuurten()==null) nNullsWijkenEnBuurten++;
            if (bu.getGemeentenaam1()==null) nNullsGemeentenaam1++;
            if (bu.getMeestVoorkomendePostcode123()==null) nNullsMeestVoorkomendePostcode123++;
            if (bu.getAantalInwoners5()==null) nNullsAantalInwoners5++;
            if (bu.getK0To15Jaar8()==null) nNullsK0To15Jaar8++;
            if (bu.getK65JaarOfOuder12()==null) nNullsK65JaarOfOuder12++;
            if (bu.getGemiddeldInkomenPerInwoner81()==null) nNullsGemiddeldInkomenPerInwoner81++;
            if (bu.getAfstandTotGroteSupermarkt116()==null) nNullsAfstandTotGroteSupermarkt116++;
            if (bu.getAfstandTotSchool118()==null) nNullsAfstandTotSchool118++;
            if (bu.getMateVanStedelijkheid125()==null) nNullsMateVanStedelijkheid125++;
        }

        if (nNullsCbsId>0){log.warn("{} null values found in fetched Buurten, field cbsId.", nNullsCbsId); returnValue = false;}
        if (nNullsWijkenEnBuurten>0){log.warn("{} null values found in fetched Buurten, field wijkenEnBuurten.", nNullsWijkenEnBuurten); returnValue = false;}
        if (nNullsGemeentenaam1>0){log.warn("{} null values found in fetched Buurten, field gemeentenaam1.", nNullsGemeentenaam1); returnValue = false;}
        if (nNullsMeestVoorkomendePostcode123>0){log.warn("{} null values found in fetched Buurten, field meestVoorkomendePostcode123.", nNullsMeestVoorkomendePostcode123); returnValue = false;}
        if (nNullsAantalInwoners5>0){log.warn("{} null values found in fetched Buurten, field aantalInwoners5.", nNullsAantalInwoners5); returnValue = false;}
        if (nNullsK0To15Jaar8>0){log.warn("{} null values found in fetched Buurten, field k0To15Jaar8.", nNullsK0To15Jaar8); returnValue = false;}
        if (nNullsK65JaarOfOuder12>0){log.warn("{} null values found in fetched Buurten, field k65JaarOfOuder12.", nNullsK65JaarOfOuder12); returnValue = false;}
        if (nNullsGemiddeldInkomenPerInwoner81>0){log.warn("{} null values found in fetched Buurten, field gemiddeldInkomenPerInwoner81.", nNullsGemiddeldInkomenPerInwoner81); returnValue = false;}
        if (nNullsAfstandTotGroteSupermarkt116>0){log.warn("{} null values found in fetched Buurten, field afstandTotGroteSupermarkt116.", nNullsAfstandTotGroteSupermarkt116); returnValue = false;}
        if (nNullsAfstandTotSchool118>0){log.warn("{} null values found in fetched Buurten, field afstandTotSchool118.", nNullsAfstandTotSchool118); returnValue = false;}
        if (nNullsMateVanStedelijkheid125>0){log.warn("{} null values found in fetched Buurten, field mateVanStedelijkheid125.", nNullsMateVanStedelijkheid125); returnValue = false;}

        if (nNullsCbsId>0 || nNullsWijkenEnBuurten>0
            || nNullsGemeentenaam1>0
            || nNullsMeestVoorkomendePostcode123>0
            || nNullsAantalInwoners5>0
            || nNullsK0To15Jaar8>0
            || nNullsK65JaarOfOuder12>0
            || nNullsAfstandTotGroteSupermarkt116>1000
            || nNullsAfstandTotSchool118>1000
            || nNullsMateVanStedelijkheid125>1000) {

            returnValue = false;
        }else {
            returnValue=true;
        }



        return returnValue;
    }

    public boolean isCompletePostcodesFetchedSet(Set<Postcode4> setPostcodes){

        boolean returnValue = true;

        int nNullsPostcode4 = 0;
        int nNullsWoonplaatscode = 0;

        for (Postcode4 pcode : setPostcodes){

            if (pcode.getPdokPostcode4()==null) nNullsPostcode4++;
            if (pcode.getPdokWoonplaatscode()==null) nNullsWoonplaatscode++;
        }
        if (nNullsPostcode4>0){log.warn("{} null values found in fetched Postcodes4, field pdokPostcode4.", nNullsPostcode4); returnValue = false;}
        if (nNullsWoonplaatscode>0){log.warn("{} null values found in fetched Postcodes4, field pdokWoonplaatscode.", nNullsWoonplaatscode); returnValue = false;}

        return returnValue;
    }


}
