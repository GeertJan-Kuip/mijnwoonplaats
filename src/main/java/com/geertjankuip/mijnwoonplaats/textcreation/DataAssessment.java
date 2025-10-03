package com.geertjankuip.mijnwoonplaats.textcreation;

import org.springframework.stereotype.Component;

// This class takes the data for a town and returns a more abstract assessment, in the form of a DataAssessment object
@Component
public class DataAssessment {

    DataAggregator dataAggregator;

    public DataAssessment(DataAggregator dataAggregator) {
        this.dataAggregator = dataAggregator;
    }

    public WoonplaatsData getWoonplaatsDataWithAssessment(String woonplaatsCode){

        WoonplaatsData woonplaatsData = dataAggregator.getWoonplaatsData(woonplaatsCode);

        Characteristic grootte = assessGrootte(woonplaatsData.getnInwoners(), woonplaatsData.getWoonplaatsNaam());
        Characteristic ouderen = assessOuderen(woonplaatsData.getnInwoners(), woonplaatsData.getN65Plus());
        Characteristic kinderen = assessKinderen(woonplaatsData.getnInwoners(), woonplaatsData.getN15Min());
        Characteristic stedelijk = assessStedelijkheid(woonplaatsData.getStedelijkheid());
        Characteristic school = assessSchool(woonplaatsData.getAfstandSchool());
        Characteristic supermarkt = assessSupermarkt(woonplaatsData.getAfstandSupermarkt());
        Characteristic vergrijzing = assessBevolkingsprofiel(ouderen, kinderen);

        Assessment assessment = new Assessment(grootte, ouderen, kinderen, stedelijk, school, supermarkt, vergrijzing);
        woonplaatsData.setAssessment(assessment);
        return woonplaatsData;
    }

    private Characteristic assessGrootte(Integer bevolking, String woonplaatsNaam){

        if (bevolking==null || woonplaatsNaam==null) return Characteristic.NULL;

        if (bevolking>300_000 && woonplaatsNaam.equals("Amsterdam")) return Characteristic.AMSTERDAM;
        if (bevolking>300_000 && woonplaatsNaam.equals("Rotterdam")) return Characteristic.ROTTERDAM;
        if (bevolking>300_000 && woonplaatsNaam.equals("Den Haag")) return Characteristic.DEN_HAAG;
        if (bevolking>300_000 && woonplaatsNaam.equals("Utrecht")) return Characteristic.UTRECHT;

        if (bevolking>50_000) return Characteristic.STAD;
        if (bevolking>25_000) return Characteristic.KLEINE_STAD;
        if (bevolking>10_000) return Characteristic.PLAATS;
        if (bevolking>1_000) return Characteristic.DORP;
        if (bevolking>150) return Characteristic.KLEIN_DORP;
        return Characteristic.GEHUCHT;
    }

    private Characteristic assessOuderen(Integer bevolking, Integer ouderen){

        if (bevolking==null || ouderen==null) return Characteristic.NULL;
        double percentage = ((double) ouderen/bevolking)*100;

        if (percentage>NationalData2023.PERCENTAGE65PLUS.getValue()+7) return Characteristic.ERG_VEEL_OUDEREN;
        if (percentage>NationalData2023.PERCENTAGE65PLUS.getValue()+2) return Characteristic.VEEL_OUDEREN;
        if (percentage>NationalData2023.PERCENTAGE65PLUS.getValue()-2) return Characteristic.GEMIDDELD_OUDEREN;
        if (percentage>NationalData2023.PERCENTAGE65PLUS.getValue()-6) return Characteristic.WEINIG_OUDEREN;
        return Characteristic.ERG_WEINIG_OUDEREN;
    }

    private Characteristic assessKinderen(Integer bevolking, Integer kinderen){

        if (bevolking==null || kinderen==null) return Characteristic.NULL;

        double percentage = ((double) kinderen/bevolking)*100;

        if (percentage>NationalData2023.PERCENTAGE15MIN.getValue()+8) return Characteristic.ERG_VEEL_KINDEREN;
        if (percentage>NationalData2023.PERCENTAGE15MIN.getValue()+2) return Characteristic.RELATIEF_VEEL_KINDEREN;
        if (percentage>NationalData2023.PERCENTAGE15MIN.getValue()-2) return Characteristic.GEMIDDELD_AANTAL_KINDEREN;
        if (percentage>NationalData2023.PERCENTAGE15MIN.getValue()-6) return Characteristic.RELATIEF_WEINIG_KINDEREN;
        return Characteristic.ERG_WEINIG_KINDEREN;
    }

    private Characteristic assessStedelijkheid(Double stedelijkheid){

        if (stedelijkheid==null) return Characteristic.NULL;
        if (stedelijkheid>4) return Characteristic.ZEER_LANDELIJK;
        if (stedelijkheid>3) return Characteristic.LANDELIJK;
        if (stedelijkheid>2) return Characteristic.STEDELIJK;
        return Characteristic.ZEER_STEDELIJK;
    }

    private Characteristic assessSchool(Double afstand){

        if (afstand==null) return Characteristic.NULL;
        if (afstand>4) return Characteristic.SCHOOL_VER;
        if (afstand>2) return Characteristic.SCHOOL_BEST_VER;
        if (afstand>1) return Characteristic.SCHOOL_DICHTBIJ;
        return Characteristic.SCHOOL_ZEER_DICHTBIJ;
    }

    private Characteristic assessSupermarkt(Double afstand){

        if (afstand==null) return Characteristic.NULL;
        if (afstand>4) return Characteristic.SUPERMARKT_ZEER_VER;
        if (afstand>2) return Characteristic.SUPERMARKT_VER;
        if (afstand>1) return Characteristic.SUPERMARKT_DICHTBIJ;
        return Characteristic.SUPERMARKT_ZEER_DICHTBIJ;
    }

    private Characteristic assessBevolkingsprofiel(Characteristic ouderen, Characteristic kinderen){

        // create a score for age
        Integer ouderenNumeriekeScore = 0;
        Integer kinderenNumeriekeScore = 0;

        if (ouderen==null || kinderen==null) return Characteristic.NULL;

        if (ouderen==Characteristic.ERG_VEEL_OUDEREN) ouderenNumeriekeScore = 5;
        if (ouderen==Characteristic.VEEL_OUDEREN) ouderenNumeriekeScore = 4;
        if (ouderen==Characteristic.GEMIDDELD_OUDEREN) ouderenNumeriekeScore = 3;
        if (ouderen==Characteristic.WEINIG_OUDEREN) ouderenNumeriekeScore = 2;
        if (ouderen==Characteristic.ERG_WEINIG_OUDEREN) ouderenNumeriekeScore = 1;

        if (kinderen==Characteristic.ERG_WEINIG_KINDEREN) kinderenNumeriekeScore = 5;
        if (kinderen==Characteristic.RELATIEF_WEINIG_KINDEREN) kinderenNumeriekeScore = 4;
        if (kinderen==Characteristic.GEMIDDELD_AANTAL_KINDEREN) kinderenNumeriekeScore = 3;
        if (kinderen==Characteristic.RELATIEF_VEEL_KINDEREN) kinderenNumeriekeScore = 2;
        if (kinderen==Characteristic.ERG_VEEL_KINDEREN) kinderenNumeriekeScore = 1;

        if (ouderenNumeriekeScore==5) return Characteristic.ZEER_STERK_VERGRIJSD;
        if (ouderenNumeriekeScore>3 && kinderenNumeriekeScore==5) return Characteristic.ZEER_STERK_VERGRIJSD;
        if (ouderenNumeriekeScore>3 && kinderenNumeriekeScore>3) return Characteristic.BOVENGEMIDDELD_VERGRIJSD;


        if (ouderenNumeriekeScore<3 && kinderenNumeriekeScore>3) return Characteristic.STEDELIJK_BEVOLKINGSPROFIEL;
        if (ouderenNumeriekeScore>3 && kinderenNumeriekeScore<3) return Characteristic.ANTI_STEDELIJK_BEVOLKINGSPROFIEL;

        if (ouderenNumeriekeScore+kinderenNumeriekeScore<4) return Characteristic.ZEER_JONG;
        if (ouderenNumeriekeScore<4 && kinderenNumeriekeScore<3) return Characteristic.RELATIEF_JONG;
        if (ouderenNumeriekeScore<3 && kinderenNumeriekeScore<4) return Characteristic.RELATIEF_JONG;
        return Characteristic.GEMIDDELD_BEVOLKINGSPROFIEL;


    }

}
