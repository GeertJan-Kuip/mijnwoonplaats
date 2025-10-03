package com.geertjankuip.mijnwoonplaats.textcreation;

import com.geertjankuip.mijnwoonplaats.domainobjects.Buurt;
import com.geertjankuip.mijnwoonplaats.domainobjects.Woonplaats;
import com.geertjankuip.mijnwoonplaats.repositories.BuurtRepository;
import com.geertjankuip.mijnwoonplaats.repositories.WoonplaatsRepository;
import org.springframework.stereotype.Component;

import java.util.List;

// This class collects all data from all 'buurten' belonging to the selected town and aggregates it
@Component
public class DataAggregator {

    BuurtRepository buurtrepository;
    WoonplaatsRepository woonplaatsRepository;

    public DataAggregator(BuurtRepository buurtrepository,WoonplaatsRepository woonplaatsRepository) {
        this.buurtrepository = buurtrepository;
        this.woonplaatsRepository = woonplaatsRepository;
    }

    public WoonplaatsData getWoonplaatsData(String woonplaatsCode){

        List<Buurt> buurtList = getBuurtObjects(woonplaatsCode);

        Integer nInwoners = getInwonerAantal(buurtList);
        Integer n65Plus = get65PlusAantal(buurtList);
        Integer n15Min = get15MinAantal(buurtList);
        Double stedelijkheid = getStedelijkheid(buurtList);
        Double afstandSchool = getAfstandTotSchoolGemiddeld(buurtList);
        Double afstandSupermarkt = getAfstandTotGroteSupermarktGemiddeld(buurtList);

        Woonplaats woonplaats = woonplaatsRepository.findFirstByWoonplaatscode(woonplaatsCode)
                .orElseThrow(() -> new IllegalArgumentException("Woonplaats niet gevonden."));

        return new WoonplaatsData(woonplaatsCode, nInwoners,n65Plus,n15Min,stedelijkheid,afstandSchool,afstandSupermarkt,
                woonplaats.getWoonplaatsnaam(), woonplaats.getGemeentenaam(), woonplaats.getProvincienaam());
    }

    public List<Buurt> getBuurtObjects(String woonplaatsCode){

        List<Buurt> buurtList = buurtrepository.findBuurtenByWoonplaatscode(woonplaatsCode);
        return buurtList;
    }

    private Integer getInwonerAantal(List<Buurt> buurtList){

        Integer res=null;
        try{
            res = buurtList.stream().mapToInt(b->b.getAantalInwoners5()).sum();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    private Integer get65PlusAantal(List<Buurt> buurtList){
        Integer res=null;
        try{
            res = buurtList.stream().mapToInt(b->b.getK65JaarOfOuder12()).sum();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    private Integer get15MinAantal(List<Buurt> buurtList){
        Integer res=null;
        try{
            res = buurtList.stream().mapToInt(b->b.getK0To15Jaar8()).sum();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    private Double getStedelijkheid(List<Buurt> buurtList){

        Double res = null;
        Integer stedelijkheidAccumulator = 0;
        Integer inwonerAccumulator = 0;

        for (Buurt buurt : buurtList) {

            if(buurt.getAantalInwoners5()!=null && buurt.getMateVanStedelijkheid125()!=null){
                stedelijkheidAccumulator += buurt.getAantalInwoners5() * buurt.getMateVanStedelijkheid125();
                inwonerAccumulator += buurt.getAantalInwoners5();
            }
        }

        if(inwonerAccumulator!=0) res = ((double) stedelijkheidAccumulator)/inwonerAccumulator;
        return res;
    }

    private Double getAfstandTotSchoolGemiddeld(List<Buurt> buurtList){

        Double res = null;
        Double afstandTotSchoolAccumulator = 0.0;
        Integer inwonerAccumulator = 0;

        for (Buurt buurt : buurtList) {

            if(buurt.getAantalInwoners5()!=null && buurt.getAfstandTotSchool118()!=null){
                afstandTotSchoolAccumulator += buurt.getAantalInwoners5() * buurt.getAfstandTotSchool118();
                inwonerAccumulator += buurt.getAantalInwoners5();
            }
        }

        if (inwonerAccumulator!=0) res=afstandTotSchoolAccumulator/inwonerAccumulator;
        return res;
    }

    private Double getAfstandTotGroteSupermarktGemiddeld(List<Buurt> buurtList){

        Double res = null;
        Double afstandTotSupermarktAccumulator = 0.0;
        Integer inwonerAccumulator = 0;

        for (Buurt buurt : buurtList) {

            if(buurt.getAantalInwoners5()!=null && buurt.getAfstandTotGroteSupermarkt116()!=null){
                afstandTotSupermarktAccumulator += buurt.getAantalInwoners5() * buurt.getAfstandTotGroteSupermarkt116();
                inwonerAccumulator += buurt.getAantalInwoners5();
            }
        }

        if(inwonerAccumulator!=0) res = afstandTotSupermarktAccumulator/inwonerAccumulator;
        return res;
    }
}
