package com.geertjankuip.mijnwoonplaats.apiclient;

import com.geertjankuip.mijnwoonplaats.datatransferobjects.BuurtDTO;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.Postcode4DTO;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.WoonplaatsDTO;
import com.geertjankuip.mijnwoonplaats.domainobjects.Buurt;
import com.geertjankuip.mijnwoonplaats.domainobjects.FetchAttempt;
import com.geertjankuip.mijnwoonplaats.domainobjects.Postcode4;
import com.geertjankuip.mijnwoonplaats.domainobjects.Woonplaats;
import com.geertjankuip.mijnwoonplaats.repositories.BuurtRepository;
import com.geertjankuip.mijnwoonplaats.repositories.FetchAttemptRepository;
import com.geertjankuip.mijnwoonplaats.repositories.Postcode4Repository;
import com.geertjankuip.mijnwoonplaats.repositories.WoonplaatsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class ApiService {

    ApiClient client;
    BuurtRepository buurtRepo;
    WoonplaatsRepository woonplaatsRepo;
    Postcode4Repository postcodeRepo;
    FetchAttemptRepository fetchAttemptRepository;
    ApiIntegrityCheck apiIntegrityCheck;

    public ApiService(ApiClient client, BuurtRepository buurtRepo, WoonplaatsRepository woonplaatsRepo,
                      Postcode4Repository postcodeRepo, FetchAttemptRepository fetchAttemptRepository, ApiIntegrityCheck apiIntegrityCheck) {
        this.client = client;
        this.buurtRepo = buurtRepo;
        this.woonplaatsRepo = woonplaatsRepo;
        this.postcodeRepo = postcodeRepo;
        this.fetchAttemptRepository = fetchAttemptRepository;
        this.apiIntegrityCheck = apiIntegrityCheck;
    }

    public List<Woonplaats> fetchWoonplaatsen(int rows, int start){

        List<WoonplaatsDTO> list =  client.getWoonplaatsen(rows, start);

        FetchAttempt fetchAttempt = new FetchAttempt(true, "Woonplaatsen");
        if (list.isEmpty() || list.size()<2400) fetchAttempt.setSuccess(false);
        fetchAttemptRepository.save(fetchAttempt);

        List<Woonplaats> saveList = new ArrayList<>();

        for (WoonplaatsDTO dto: list){
            Woonplaats woonplaats = new Woonplaats();

            woonplaats.setPdokId(dto.getId());
            woonplaats.setWeergavenaam(dto.getWeergavenaam());
            woonplaats.setWoonplaatscode(dto.getWoonplaatscode());
            woonplaats.setWoonplaatsnaam(dto.getWoonplaatsnaam());
            woonplaats.setGemeentecode(dto.getGemeentecode());
            woonplaats.setGemeentenaam(dto.getGemeentenaam());
            woonplaats.setProvincienaam(dto.getProvincienaam());
            saveList.add(woonplaats);
        }

        return (apiIntegrityCheck.isCompleteWoonplaatsFetchedList(saveList) ? saveList : Collections.emptyList());
    }

    @Transactional
    public void synchronizeTableWoonplaatsen(List<Woonplaats> saveList){

        woonplaatsRepo.deleteAllInBatch();
        woonplaatsRepo.saveAll(saveList);
    }

    public List<Buurt> fetchBuurten(){

        List<BuurtDTO> list =  client.getBuurten();

        FetchAttempt fetchAttempt = new FetchAttempt(true, "Buurten");
        if (list.isEmpty() || list.size()<14000) fetchAttempt.setSuccess(false);
        fetchAttemptRepository.save(fetchAttempt);

        List<Buurt> saveList = new ArrayList<>();

        for (BuurtDTO dto: list){
            Buurt buurt = new Buurt();

            buurt.setCbsId(dto.getId());
            buurt.setWijkenEnBuurten(dto.getWijkenEnBuurten());
            buurt.setGemeentenaam1(dto.getGemeentenaam_1());
            buurt.setMeestVoorkomendePostcode123(dto.getMeestVoorkomendePostcode_123());
            buurt.setAantalInwoners5(dto.getAantalInwoners_5());
            buurt.setK0To15Jaar8(dto.getK_0Tot15Jaar_8());
            buurt.setK65JaarOfOuder12(dto.getK_65JaarOfOuder_12());
            buurt.setGemiddeldInkomenPerInwoner81(dto.getGemiddeldInkomenPerInwoner_81());
            buurt.setAfstandTotGroteSupermarkt116(dto.getAfstandTotGroteSupermarkt_116());
            buurt.setAfstandTotSchool118(dto.getAfstandTotSchool_118());
            buurt.setMateVanStedelijkheid125(dto.getMateVanStedelijkheid_125());

            saveList.add(buurt);
        }

        return (apiIntegrityCheck.isCompleteBuurtFetchedList(saveList) ? saveList : Collections.emptyList());
    }

    @Transactional
    public void synchronizeTableBuurten(List<Buurt> saveList){

        buurtRepo.deleteAllInBatch();
        buurtRepo.saveAll(saveList);
    }

    public Set<Postcode4> fetchPostcodes(){

        Set<String> woonplaatsCodes = woonplaatsRepo.findAllWoonplaatscodes();
        Set<Postcode4DTO> list =  client.getPostcodes(woonplaatsCodes);

        FetchAttempt fetchAttempt = new FetchAttempt(true, "Postcodes");
        if (list.isEmpty() || list.size()<4000) fetchAttempt.setSuccess(false);
        fetchAttemptRepository.save(fetchAttempt);

        Set<Postcode4> saveSet = new HashSet<>();

        for (Postcode4DTO dto: list){

            Postcode4 postcode4 = new Postcode4(dto.getPostcode(), dto.getWoonplaatscode());
            saveSet.add(postcode4);
        }

        // Some postcodes need to be processed manually because of api limitations PDOK
        List<Postcode4> groteStedenPostcode4List = new ArrayList<>();

        // Amsterdam
        for (int i=1000; i<1100; i++){
            Postcode4 postcode4Amsterdam = new Postcode4("" + i, "3594");
            groteStedenPostcode4List.add(postcode4Amsterdam);
        }

        // Rotterdam
        for (int i=3000; i<3100; i++){
            Postcode4 postcode4Rotterdam = new Postcode4("" + i, "3086");
            groteStedenPostcode4List.add(postcode4Rotterdam);
        }
        // Den Haag (or 's-Gravenhage)
        for (int i=2490; i<2600; i++){
            Postcode4 postcode4DenHaag = new Postcode4("" + i, "1245");
            groteStedenPostcode4List.add(postcode4DenHaag);
        }

        groteStedenPostcode4List.add(new Postcode4("2244", "1245"));
        groteStedenPostcode4List.add(new Postcode4("2291", "1245"));

        saveSet.addAll(groteStedenPostcode4List);


        return (apiIntegrityCheck.isCompletePostcodesFetchedSet(saveSet) ? saveSet : Collections.emptySet());

    }

    @Transactional
    public void synchronizeTablePostcodes(Set<Postcode4> saveSet){

        postcodeRepo.deleteAllInBatch();
        postcodeRepo.saveAll(saveSet);
    }

}
