package com.geertjankuip.mijnwoonplaats;

import com.geertjankuip.mijnwoonplaats.apiclient.ApiScheduler;
import com.geertjankuip.mijnwoonplaats.apiclient.ApiService;
import com.geertjankuip.mijnwoonplaats.domainobjects.Buurt;
import com.geertjankuip.mijnwoonplaats.domainobjects.Postcode4;
import com.geertjankuip.mijnwoonplaats.domainobjects.Woonplaats;
import com.geertjankuip.mijnwoonplaats.repositories.BuurtRepository;
import com.geertjankuip.mijnwoonplaats.repositories.Postcode4Repository;
import com.geertjankuip.mijnwoonplaats.repositories.WoonplaatsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class StartupListener {

    private static final Logger log = LoggerFactory.getLogger(StartupListener.class);

    ApiScheduler apiScheduler;
    ApiService apiService;
    WoonplaatsRepository woonplaatsRepository;
    BuurtRepository buurtRepository;
    Postcode4Repository postcode4Repository;

    public StartupListener(ApiScheduler apiScheduler, ApiService apiService, WoonplaatsRepository woonplaatsRepository,
                           BuurtRepository buurtRepository, Postcode4Repository postcode4Repository) {
        this.apiScheduler = apiScheduler;
        this.apiService = apiService;
        this.woonplaatsRepository = woonplaatsRepository;
        this.buurtRepository = buurtRepository;
        this.postcode4Repository = postcode4Repository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {

        log.info("Startup script found!");

        long nRowsWoonplaatsen = woonplaatsRepository.count();
        long nRowsBuurten = buurtRepository.count();
        long nRowsPostcodes = postcode4Repository.count();

        if (nRowsWoonplaatsen<2400  ){

            log.info("Table woonplaatsen has {} rows which is less than requested minimum of 2400. Trying to fetch from PDOK api.", nRowsWoonplaatsen);
            List<Woonplaats> listWoonplaatsen = apiService.fetchWoonplaatsen(3000,0);

            if (listWoonplaatsen.size()> 2400) {
                apiService.synchronizeTableWoonplaatsen(listWoonplaatsen);
                log.info("Update table woonplaatsen ready. Table has {} rows now.", woonplaatsRepository.count());
            }else{
                log.warn("Table woonplaatsen not OK. It had {} rows at startup (less than requested 2400) and the fetch " +
                        "attempt yielded just {} items. Table update is skipped, application stopped, check the PDOK api.", nRowsWoonplaatsen, listWoonplaatsen.size());
                throw new RuntimeException("Unable to update table woonplaatsen.");
            }
        } else {

            log.info("Table woonplaatsen has {} rows, no update required.", nRowsWoonplaatsen);
        }

        if (nRowsBuurten<13000){

            log.info("Table buurten has {} rows which is less than requested minimum of 13000. Update starts.", nRowsBuurten);
            List<Buurt> listBuurten = apiService.fetchBuurten();

            if (listBuurten.size()>13000) {
                apiService.synchronizeTableBuurten(listBuurten);
                log.info("Update table buurten ready. Table has {} rows now.", buurtRepository.count());
            } else{
                log.warn("Table buurten not OK. It had {} rows at startup (less than requested 13000) and the fetch " +
                        "attempt yielded just {} items. Table update is skipped, check the CBS api.", nRowsBuurten, listBuurten.size());
                throw new RuntimeException("Unable to update table buurten.");
            }
        } else {

            log.info("Table buurten has {} rows, no update required.", nRowsBuurten);
        }

        if (nRowsPostcodes<4000){

            log.info("Table postcodes4 has {} rows which is less than requested minimum of 4000. Update starts", nRowsPostcodes);
            Set<Postcode4> listPostcodes = apiService.fetchPostcodes();

            if (listPostcodes.size()>4000) {
                apiService.synchronizeTablePostcodes(listPostcodes);
                log.info("Update table postcodes4 ready. Table has {} rows now.", postcode4Repository.count());
            } else {
                log.warn("Table postcodes4 not OK. It had {} rows at startup (less than requested 4000) and the fetch " +
                        "attempt yielded just {} items. Table update is skipped, check the PDOK api.", nRowsPostcodes, listPostcodes.size());
                throw new RuntimeException("Unable to update table postcodes4.");
            }
        } else {

            log.info("Table postcodes4 has {} rows, no update required.", nRowsPostcodes);
        }

    }

}
