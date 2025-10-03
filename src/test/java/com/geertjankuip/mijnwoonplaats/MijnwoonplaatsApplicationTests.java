package com.geertjankuip.mijnwoonplaats;

import com.geertjankuip.mijnwoonplaats.apiclient.ApiClient;
import com.geertjankuip.mijnwoonplaats.apiclient.ApiClientTestingExternalEndpoints;
import com.geertjankuip.mijnwoonplaats.apiclient.ApiIntegrityCheck;
import com.geertjankuip.mijnwoonplaats.apiclient.ApiService;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.BuurtDTO;
import com.geertjankuip.mijnwoonplaats.domainobjects.Buurt;
import com.geertjankuip.mijnwoonplaats.domainobjects.Postcode4;
import com.geertjankuip.mijnwoonplaats.domainobjects.Woonplaats;
import com.geertjankuip.mijnwoonplaats.repositories.BuurtRepository;
import com.geertjankuip.mijnwoonplaats.repositories.Postcode4Repository;
import com.geertjankuip.mijnwoonplaats.repositories.WoonplaatsRepository;
import com.geertjankuip.mijnwoonplaats.textcreation.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MijnwoonplaatsApplicationTests {

	@Autowired
	ApiClient apiClient;
	@Autowired
	ApiService apiService;
	@Autowired
	BuurtRepository buurtRepository;
	@Autowired
	Postcode4Repository postcode4Repository;
	@Autowired
	WoonplaatsRepository woonplaatsRepository;
	@Autowired
	DataAggregator dataAnalysis;
	@Autowired
	DataAssessment dataAssessment;
	@Autowired
	TextService textService;
	@Autowired
	ApiClientTestingExternalEndpoints apiClientTestingExternalEndpoints;
	@Autowired
	ApiIntegrityCheck apiIntegrityCheck;

	@Test
	void randomTest(){

//		List<Woonplaats> list = woonplaatsRepository.findAll();
//		System.out.println(apiIntegrityCheck.isCompleteWoonplaatsFetchedList(list));

//		List<Buurt> list = buurtRepository.findAll();
//		System.out.println(apiIntegrityCheck.isCompleteBuurtFetchedList(list));

		List<Postcode4> list = postcode4Repository.findAll();
		Set<Postcode4> set = new HashSet<>(list);

		set.add(new Postcode4(null,null));

		System.out.println(apiIntegrityCheck.isCompletePostcodesFetchedSet(set));


	}

}


