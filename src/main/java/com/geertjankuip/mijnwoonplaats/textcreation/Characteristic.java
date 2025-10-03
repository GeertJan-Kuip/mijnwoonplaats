package com.geertjankuip.mijnwoonplaats.textcreation;

public enum Characteristic {

    NULL("Data missing or inappropriate format",-1),

    GEHUCHT("zielen tellend gehucht",1),
    KLEIN_DORP("klein dorp",2),
    DORP("dorp",3),
    PLAATS("plaats",4),
    KLEINE_STAD("kleine stad",5),
    STAD("stad",6),
    GROTE_STAD("grote stad",7),

    AMSTERDAM("Amsterdam",1),
    ROTTERDAM("Rotterdam",2),
    UTRECHT("Utrecht",3),
    DEN_HAAG("Den Haag",4),

    ERG_WEINIG_OUDEREN("erg weinig ouderen",1),
    WEINIG_OUDEREN("weinig ouderen",2),
    GEMIDDELD_OUDEREN("gemiddeld aandeel ouderen",3),
    VEEL_OUDEREN("veel ouderen",4),
    ERG_VEEL_OUDEREN("erg veel ouderen",5),

    ERG_WEINIG_KINDEREN("nauwelijks kinderen",1),
    RELATIEF_WEINIG_KINDEREN("relatief weinig kinderen",2),
    GEMIDDELD_AANTAL_KINDEREN("gemiddeld percentage kinderen",3),
    RELATIEF_VEEL_KINDEREN("relatief veel kinderen",4),
    ERG_VEEL_KINDEREN("erg veel kinderen",5),

    ZEER_JONG("zeer jong",-1),
    RELATIEF_JONG("relatief jong",-1),
    GEMIDDELD_VERGRIJSD("gemiddeld vergrijsd",-1),
    BOVENGEMIDDELD_VERGRIJSD("bovengemiddeld vergrijsd",-1),
    ZEER_STERK_VERGRIJSD("zeer sterk vergrijsd",-1),
    STEDELIJK_BEVOLKINGSPROFIEL("stedelijk bevolkingsprofiel",-1),
    ANTI_STEDELIJK_BEVOLKINGSPROFIEL("anti-stedelijk bevolkingsprofiel",-1),
    GEMIDDELD_BEVOLKINGSPROFIEL("gemiddeld bevolkingsprofiel",-1),

    ZEER_LANDELIJK("afgelegen",1),
    LANDELIJK("relatief rustig",2),
    STEDELIJK("stedelijk",3),
    ZEER_STEDELIJK("hoog stedelijk",4),

    SCHOOL_VER("basisschool is ver weg",1),
    SCHOOL_BEST_VER("basisschool is even fietsen",2),
    SCHOOL_DICHTBIJ("basisschool is dichtbij",3),
    SCHOOL_ZEER_DICHTBIJ("basisschool is vaak op loopafstand",4),

    SUPERMARKT_ZEER_VER("grote supermarkt is erg ver weg",1),
    SUPERMARKT_VER("grote supermarkt relatief ver weg",2),
    SUPERMARKT_DICHTBIJ("grote supermarkt is relatief dichtbij",3),
    SUPERMARKT_ZEER_DICHTBIJ("grote supermarkt is vaak zeer dichtbij",4);

    private final String description;
    private final Integer rank;

    Characteristic(String description, Integer rank) {
        this.description = description;
        this.rank = rank;
    }

    public String getDescription() {
        return description;
    }

    public Integer getRank() {
        return rank;
    }
}

