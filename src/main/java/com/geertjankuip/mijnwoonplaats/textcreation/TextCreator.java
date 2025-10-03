package com.geertjankuip.mijnwoonplaats.textcreation;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TextCreator {

    // Alle plaatsen behalve grootste vier
    String text01 = "%s is een %s %s, onderdeel van de gemeente %s. ";
    String text02 = "%s is een %s met %s inwoners, onderdeel van de gemeente %s. ";
    String text03 = "%s is een %s met %s inwoners, onderdeel van de gelijknamige gemeente. ";
    String text04 = "%s is een stad met %s inwoners. ";

    // Utrecht, Den Haag, Rotterdam, Amsterdam.
    String text05_Utrecht = "Met een populatie van %s is %s na Amsterdam, Rotterdam en Den Haag de vierde stad van het land. ";
    String text06_DenHaag = "Met een populatie van %s is %s na Amsterdam en Rotterdam de derde stad van het land. ";
    String text07_Rotterdam = "Met een populatie van %s is %s na Amsterdam de tweede stad van het land. ";
    String text08_Amsterdam = "Met een populatie van %s is %s de grootste stad van het land. ";


    String text14 = "Het percentage vijfenzestig-plussers ligt met %s procent veel lager dan landelijk (%s%%), ";
    String text15 = "Het percentage vijfenzestig-plussers ligt met %s procent lager dan landelijk (%s%%), ";
    String text16 = "Het percentage vijfenzestig-plussers ligt met %s procent ongeveer op het niveau van Nederland als geheel (%s%%), ";
    String text17 = "Het percentage vijfenzestig-plussers ligt met %s procent hoger dan landelijk (%s%%), ";
    String text18 = "Het percentage vijfenzestig-plussers ligt met %s procent flink hoger dan landelijk (%s%%), ";

    String text09 = "de leeftijdsgroep onder vijftien met %s procent juist veel kleiner is dan in de rest van Nederland (%s%%). ";
    String text09a = "de leeftijdsgroep onder vijftien is met %s procent veel kleiner dan in de rest van Nederland (%s%%). ";
    String text10 = "de leeftijdsgroep onder vijftien met %s procent juist kleiner is dan in de rest van Nederland (%s%%). ";
    String text10a = "de leeftijdsgroep onder vijftien is met %s procent kleiner dan in de rest van Nederland (%s%%). ";
    String text11 = "de leeftijdsgroep onder vijftien met %s procent niet veel afwijkt van het nationaal gemiddelde (%s%%). ";
    String text11a = "de leeftijdsgroep onder vijftien is met %s procent vrijwel even groot als in de rest van Nederland (%s%%). ";
    String text12 = "de leeftijdsgroep onder vijftien met %s procent juist groter is dan in de rest van Nederland (%s%%). ";
    String text12a = "de leeftijdsgroep onder vijftien is met %s procent groter dan in de rest van Nederland (%s%%). ";
    String text13 = "de leeftijdsgroep onder vijftien met %s procent juist veel groter is dan in de rest van Nederland (%s%%). ";
    String text13a = "de leeftijdsgroep onder vijftien is met %s procent veel groter dan in de rest van Nederland (%s%%). ";

    // Stedelijkheid
    String text20 = "De adresdichtheid is hier erg laag, wat betekent dat er ruimte te over is. ";
    String text21 = "De adresdichtheid is hier lager dan gemiddeld, wat betekent dat je niet al te dicht op je buren zit. ";
    String text22 = "De adresdichtheid is bovengemiddeld, wat betekent dat er per woning wat minder buitenruimte is. ";
    String text23 = "De adresdichtheid is hier erg hoog, wat betekent dat je vaak dicht op je buren zit. ";

    // Voorzieningenniveau
    String text30 = "Het nadeel hiervan is dat belangrijke voorzieningen op wat grotere afstand liggen. ";
    String text31 = "Hoewel deze ruime opzet vaak samengaat met moeilijk bereikbare voorzieningen, valt dat hier nog mee. ";
    String text32 = "Ruim wonen gaat hier samen met goed bereikbare basisvoorzieningen. ";
    String text33 = "Het voordeel hiervan is dat belangrijke voorzieningen vaak op loopafstand liggen. Dat is ook hier het geval. ";
    String text34 = "Het voordeel hiervan is dat belangrijke voorzieningen vaak op loopafstand liggen, maar dat is hier niet helemaal het geval. ";
    String text35 = "Desondanks is de afstand tot belangrijke basisvoorzieningen vrij groot. ";

    // School en supermarkt
    String text40 = "Een inwoner van %s moet gemiddeld %s kilometer afleggen naar een grote supermarkt en %s kilometer naar de dichtstbijzijnde basisschool. ";

    public String createLine1TypePlaats(WoonplaatsData wpd ){

        Characteristic characteristic = wpd.getAssessment().grootte();
        String woonplaatsNaam = wpd.getWoonplaatsNaam();
        String gemeente = wpd.getGemeenteNaam();
        Integer inwoners = wpd.getnInwoners();
        boolean equalNaming = wpd.getGemeenteNaam().equals(wpd.getWoonplaatsNaam());

        switch(characteristic){
            case Characteristic.AMSTERDAM: return (String.format(text08_Amsterdam, inwoners, woonplaatsNaam));
            case Characteristic.ROTTERDAM: return (String.format(text07_Rotterdam, inwoners, woonplaatsNaam));
            case Characteristic.DEN_HAAG: return (String.format(text06_DenHaag, inwoners, woonplaatsNaam));
            case Characteristic.UTRECHT: return (String.format(text05_Utrecht, inwoners, woonplaatsNaam));
        }

        if (equalNaming){
            switch(characteristic) {
                case Characteristic.PLAATS: return (String.format(text03, woonplaatsNaam, Characteristic.PLAATS.getDescription(), inwoners));
                case Characteristic.KLEINE_STAD: return (String.format(text03, woonplaatsNaam, Characteristic.KLEINE_STAD.getDescription(), inwoners));
                case Characteristic.STAD: return (String.format(text03, woonplaatsNaam, Characteristic.STAD.getDescription(), inwoners));
            }
        }

        switch(characteristic) {
            case Characteristic.GEHUCHT: return String.format(text01, woonplaatsNaam, inwoners, Characteristic.GEHUCHT.getDescription(),gemeente);
            case Characteristic.KLEIN_DORP: return String.format(text02, woonplaatsNaam, Characteristic.KLEIN_DORP.getDescription(), inwoners, gemeente);
            case Characteristic.DORP: return String.format(text02, woonplaatsNaam, Characteristic.DORP.getDescription(), inwoners, gemeente);
            case Characteristic.PLAATS: return String.format(text02, woonplaatsNaam, Characteristic.PLAATS.getDescription(), inwoners, gemeente);
            case Characteristic.KLEINE_STAD: return String.format(text02, woonplaatsNaam, Characteristic.KLEINE_STAD.getDescription(), inwoners, gemeente);
            case Characteristic.STAD: return String.format(text04, woonplaatsNaam, inwoners);
            case Characteristic.GROTE_STAD: return String.format(text04, woonplaatsNaam, inwoners);

            default: return "";
        }
    }

    public String createLine2Bevolkingsopbouw(WoonplaatsData wpd) {

        Characteristic bevolkingsopbouw = wpd.getAssessment().vergrijzing();

        if (bevolkingsopbouw==Characteristic.ZEER_JONG) return String.format("Ten opzichte van Nederland als geheel is de bevolking zeer jong, met veel gezinnen en weinig ouderen. ");
        if (bevolkingsopbouw==Characteristic.RELATIEF_JONG) return String.format("Ten opzichte van Nederland als geheel is de bevolking vrij jong, de vergrijzing is hier beperkt gebleven. ");
        if (bevolkingsopbouw==Characteristic.GEMIDDELD_VERGRIJSD) return String.format("Qua vergrijzing treffen we hier het Nederlands gemiddelde. ");
        if (bevolkingsopbouw==Characteristic.BOVENGEMIDDELD_VERGRIJSD) return String.format("De vergrijzing heeft hier harder toegeslagen dan in de rest van het land. ");
        if (bevolkingsopbouw==Characteristic.ZEER_STERK_VERGRIJSD) return String.format("De vergrijzing heeft hier opmerkelijk hard toegeslagen. ");
        if (bevolkingsopbouw==Characteristic.STEDELIJK_BEVOLKINGSPROFIEL) return String.format("We vinden hier een typisch stedelijke demografische opbouw, met veel inwoners in de leeftijdscategorie tussen 20 en 65. ");
        if (bevolkingsopbouw==Characteristic.ANTI_STEDELIJK_BEVOLKINGSPROFIEL) return String.format("De demografische opbouw is anti-stedelijk, met relatief weinig mensen in de leeftijdsgroep 20-65. ");
        if (bevolkingsopbouw==Characteristic.GEMIDDELD_BEVOLKINGSPROFIEL) return String.format("De leeftijdsopbouw van deze plaats lijkt erg op het landelijk gemiddelde. ");


        return Characteristic.NULL.getDescription();
    }

    public String createLine3AandeelOuderen(WoonplaatsData wpd){

        Integer inwoners = wpd.getnInwoners();
        Integer ouderen = wpd.getN65Plus();
        Double percentageOuderen = ((double) ouderen/inwoners)*100;
        String percercentageOuderenAsString = String.format("%.1f", percentageOuderen);
        Double verschil = NationalData2023.PERCENTAGE65PLUS.getValue() - percentageOuderen;
        Characteristic characteristic = wpd.getAssessment().ouderen();

        switch(characteristic){
            case Characteristic.ERG_WEINIG_OUDEREN: return String.format(text14, percercentageOuderenAsString, NationalData2023.PERCENTAGE65PLUS.getValue());
            case Characteristic.WEINIG_OUDEREN: return String.format(text15, percercentageOuderenAsString, NationalData2023.PERCENTAGE65PLUS.getValue());
            case Characteristic.GEMIDDELD_OUDEREN: return String.format(text16, percercentageOuderenAsString, NationalData2023.PERCENTAGE65PLUS.getValue());
            case Characteristic.VEEL_OUDEREN: return String.format(text17, percercentageOuderenAsString, NationalData2023.PERCENTAGE65PLUS.getValue());
            case Characteristic.ERG_VEEL_OUDEREN: return String.format(text18, percercentageOuderenAsString, NationalData2023.PERCENTAGE65PLUS.getValue());

            default: return "";
        }

    }

    private String createTussenvoegselOuderenKinderen(WoonplaatsData wpd){

        Integer kinderen = wpd.getAssessment().kinderen().getRank();
        Integer ouderen = wpd.getAssessment().ouderen().getRank();
        String tussenvoegsel = "";

        if(kinderen<3 && ouderen<3) tussenvoegsel = "en ook ";
        if(kinderen<3 && ouderen==3)  tussenvoegsel = "terwijl ";
        if(kinderen<3 && ouderen>3) tussenvoegsel = "terwijl ";
        if(kinderen==3 && ouderen<3) tussenvoegsel = "terwijl ";
        if(kinderen==3 && ouderen==3)  tussenvoegsel = "en ook ";
        if(kinderen==3 && ouderen>3) tussenvoegsel = "terwijl ";
        if(kinderen>3 && ouderen<3) tussenvoegsel = "terwijl ";
        if(kinderen>3 && ouderen==3) tussenvoegsel = "terwijl ";
        if(kinderen>3 && ouderen>3)  tussenvoegsel = "en ook ";

        return tussenvoegsel;
    }

    public String createLine4AandeelKinderen(WoonplaatsData wpd){

        Integer inwoners = wpd.getnInwoners();
        Integer nkinderen = wpd.getN15Min();
        Double percentageKinderen = ((double) nkinderen/inwoners)*100;
        String percercentageKinderenAsString = String.format("%.1f", percentageKinderen);



        String woonplaatsNaam = wpd.getWoonplaatsNaam();
        Characteristic kinderen = wpd.getAssessment().kinderen();
        Characteristic ouderen = wpd.getAssessment().ouderen();
        String tussenvoegsel = createTussenvoegselOuderenKinderen(wpd);

        if (tussenvoegsel=="terwijl ") {
            switch (kinderen) {
                case Characteristic.ERG_WEINIG_KINDEREN:
                    return String.format(text09,percercentageKinderenAsString,NationalData2023.PERCENTAGE15MIN.getValue());
                case Characteristic.RELATIEF_WEINIG_KINDEREN:
                    return String.format(text10,percercentageKinderenAsString,NationalData2023.PERCENTAGE15MIN.getValue());
                case Characteristic.GEMIDDELD_AANTAL_KINDEREN:
                    return String.format(text11,percercentageKinderenAsString,NationalData2023.PERCENTAGE15MIN.getValue());
                case Characteristic.RELATIEF_VEEL_KINDEREN:
                    return String.format(text12,percercentageKinderenAsString,NationalData2023.PERCENTAGE15MIN.getValue());
                case Characteristic.ERG_VEEL_KINDEREN:
                    return String.format(text13,percercentageKinderenAsString,NationalData2023.PERCENTAGE15MIN.getValue());
                default:
                    return "";
            }
        }
        if (tussenvoegsel=="en ook ") {
            switch (kinderen) {
                case Characteristic.ERG_WEINIG_KINDEREN:
                    return String.format(text09a,percercentageKinderenAsString,NationalData2023.PERCENTAGE15MIN.getValue());
                case Characteristic.RELATIEF_WEINIG_KINDEREN:
                    return String.format(text10a,percercentageKinderenAsString,NationalData2023.PERCENTAGE15MIN.getValue());
                case Characteristic.GEMIDDELD_AANTAL_KINDEREN:
                    return String.format(text11a,percercentageKinderenAsString,NationalData2023.PERCENTAGE15MIN.getValue());
                case Characteristic.ERG_VEEL_KINDEREN:
                    return String.format(text12a,percercentageKinderenAsString,NationalData2023.PERCENTAGE15MIN.getValue());
                case Characteristic.RELATIEF_VEEL_KINDEREN:
                    return String.format(text13a,percercentageKinderenAsString,NationalData2023.PERCENTAGE15MIN.getValue());
                default:
                    return "";
            }
        }
        return "";

    }

    public String createLine5Stedelijkheid(WoonplaatsData wpd) {


        Characteristic stedelijkheid = wpd.getAssessment().stedelijk();

        switch(stedelijkheid){
            case stedelijkheid.ZEER_LANDELIJK: return text20;
            case stedelijkheid.LANDELIJK: return text21;
            case stedelijkheid.STEDELIJK: return text22;
            case stedelijkheid.ZEER_STEDELIJK: return text23;
            default: return "";
        }
    }

    public String createLine6AfstandAlgemeen(WoonplaatsData wpd) {

        Integer stedelijkheid = wpd.getAssessment().stedelijk().getRank();
        Double distances = wpd.getAfstandSupermarkt() + wpd.getAfstandSchool();

        if (stedelijkheid<3){
            if (distances>5) return text30;
            if (distances>3) return text31;
            if (distances>0) return text32;
        }
        if (stedelijkheid>2){
            if (distances>3) return text35;
            if (distances>2) return text34;
            if (distances>0) return text33;
        }
        return "";
    }

    public String createLine7AfstandSpecifiek(WoonplaatsData wpd) {

        String woonplaatsNaam = wpd.getWoonplaatsNaam();
        Double distanceSupermarkt = wpd.getAfstandSupermarkt();
        String distanceSupermarktAsString = String.format("%.1f", distanceSupermarkt);
        Double distanceSchool = wpd.getAfstandSchool();
        String distanceSchoolAsString = String.format("%.1f", distanceSchool);

        return String.format(text40, woonplaatsNaam, distanceSupermarktAsString, distanceSchoolAsString);
    }

    public List<String> getText (WoonplaatsData wpd){

        List<String> list = new ArrayList<>();
        list.add(createLine1TypePlaats(wpd));
        list.add(createLine2Bevolkingsopbouw(wpd) + createLine3AandeelOuderen(wpd) + createTussenvoegselOuderenKinderen(wpd) + createLine4AandeelKinderen(wpd));
        list.add(createLine5Stedelijkheid(wpd) + createLine6AfstandAlgemeen(wpd) +  createLine7AfstandSpecifiek(wpd));

        return list;
    }
}
