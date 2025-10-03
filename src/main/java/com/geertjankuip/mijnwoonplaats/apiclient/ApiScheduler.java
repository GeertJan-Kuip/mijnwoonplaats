package com.geertjankuip.mijnwoonplaats.apiclient;

import com.geertjankuip.mijnwoonplaats.domainobjects.Buurt;
import com.geertjankuip.mijnwoonplaats.domainobjects.EndpointsTest;
import com.geertjankuip.mijnwoonplaats.domainobjects.Postcode4;
import com.geertjankuip.mijnwoonplaats.domainobjects.Woonplaats;
import com.geertjankuip.mijnwoonplaats.repositories.EndpointsTestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ApiScheduler {

    private static final Logger log = LoggerFactory.getLogger(ApiScheduler.class);

    private ApiClientTestingExternalEndpoints apiClientTestingExternalEndpoints;
    private EndpointsTestRepository endpointsTestRepository;
    private ApiService apiService;

    public ApiScheduler(ApiClientTestingExternalEndpoints apiClientTestingExternalEndpoints,
                        EndpointsTestRepository endpointsTestRepository, ApiService apiService) {
        this.apiClientTestingExternalEndpoints = apiClientTestingExternalEndpoints;
        this.endpointsTestRepository = endpointsTestRepository;
        this.apiService = apiService;
    }

    @Scheduled(cron = "0 15 2 * * *")
    public void retrieveOnceADay() {

        log.info("This is the retrieveOnceADay() method running the EndPointsTest!");

        Boolean val1 = apiClientTestingExternalEndpoints.testWoonplaatsEndpoint();
        Boolean val2 = apiClientTestingExternalEndpoints.testWoonplaatsDetailsEndpoint();
        Boolean val3 = apiClientTestingExternalEndpoints.testBuurtEndpoint();
        Boolean val4 = apiClientTestingExternalEndpoints.testPostcodeEndpoint();

        EndpointsTest endpointsTest = new EndpointsTest(val1,val2,val3,val4);

        if (val1 && val2 && val3 && val4) {
            log.info("Endpointstest result: " + val1 + ", " + val2 + ", " + val3 + ", " + val4);
        } else {
            log.warn("Endpointstest result (at least one endpoint failed): " + val1 + ", " + val2 + ", " + val3 + ", " + val4);
        }

        endpointsTestRepository.save(endpointsTest);
    }

    @Scheduled(cron = "0 0 1 1 * ?")
    public void fullUpdate(){

        log.info("Start monthly update of tables woonplaatsen, buurten and postcodes4.");

        List<Woonplaats> listWoonplaatsen = apiService.fetchWoonplaatsen(3000,0);
        if(listWoonplaatsen.size()>2400) {
            apiService.synchronizeTableWoonplaatsen(listWoonplaatsen);
            log.info("Table woonplaatsen synchronized.");
        }else{
            log.warn("Method apiService.fetchWoonplaatsen() returned collection with {} woonplaatsen, which is less than the required 2400. " +
                    "Table woonplaatsen not synchronized.", listWoonplaatsen.size());
        }

        List<Buurt> listBuurten = apiService.fetchBuurten();
        if(listBuurten.size()>13000) {
            apiService.synchronizeTableBuurten(listBuurten);
            log.info("Table buurten synchronized.");
        }else{
            log.warn("Method apiService.fetchBuurten() returned collection with {} buurten, which is less than the required 13000. " +
                    "Table buurten not synchronized.", listBuurten.size());
        }

        Set<Postcode4> setPostcodes = apiService.fetchPostcodes();
        if(setPostcodes.size()>4000) {
            apiService.synchronizeTablePostcodes(setPostcodes);
            log.info("Table postcodes4 synchronized.");
        }else{
            log.warn("Method apiService.setPostcodes() returned collection with {} postcodes, which is less than the required 4000. " +
                    "Table postcodes4 not synchronized.", setPostcodes.size());
        }

        log.info("Monthly update of tables woonplaatsen, buurten and postcodes4 finished.");
    }
}
