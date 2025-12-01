package com.geertjankuip.mijnwoonplaats;

import com.geertjankuip.mijnwoonplaats.apiclient.ApiClient;
import com.geertjankuip.mijnwoonplaats.apiclient.ApiClientTestingExternalEndpoints;
import com.geertjankuip.mijnwoonplaats.apiclient.ApiIntegrityCheck;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.BuurtDTO;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.Postcode4DTO;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.WoonplaatsDTO;
import com.geertjankuip.mijnwoonplaats.domainobjects.Buurt;
import com.geertjankuip.mijnwoonplaats.domainobjects.Postcode4;
import com.geertjankuip.mijnwoonplaats.domainobjects.Woonplaats;
import com.geertjankuip.mijnwoonplaats.repositories.BuurtRepository;
import com.geertjankuip.mijnwoonplaats.repositories.Postcode4Repository;
import com.geertjankuip.mijnwoonplaats.repositories.WoonplaatsRepository;
import com.geertjankuip.mijnwoonplaats.textcreation.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class IntegrationTests {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ApiClient apiClient;
	@Autowired
	BuurtRepository buurtRepository;
	@Autowired
	Postcode4Repository postcode4Repository;
	@Autowired
	WoonplaatsRepository woonplaatsRepository;
	@Autowired
	TextService textService;
	@Autowired
	ApiClientTestingExternalEndpoints apiClientTestingExternalEndpoints;
	@Autowired
	ApiIntegrityCheck apiIntegrityCheck;

	@Test
	void testAllMyEndpoints() throws Exception{

		mockMvc.perform(get("/info/Rotterdam/1804")).andExpect(status().isOk());
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	void pdokCbsEndpointTest(){

		apiClientTestingExternalEndpoints.testWoonplaatsEndpoint();

		Boolean val1 = apiClientTestingExternalEndpoints.testWoonplaatsEndpoint();
		Boolean val2 = apiClientTestingExternalEndpoints.testWoonplaatsDetailsEndpoint();
		Boolean val3 = apiClientTestingExternalEndpoints.testBuurtEndpoint();
		Boolean val4 = apiClientTestingExternalEndpoints.testPostcodeEndpoint();

		assertThat(val1).isTrue();
		assertThat(val2).isTrue();
		assertThat(val3).isTrue();
		assertThat(val4).isTrue();
	}

	@Test
	void pdokWoonplaatsenEndpointJsonMapsCorrectlyToDTO() {

		List<WoonplaatsDTO> list = apiClient.getWoonplaatsen(1, 0);

		assertThat(list).hasSize(1);
		WoonplaatsDTO myDTO = list.getFirst();

		assertThat(myDTO.getId()).isNotNull();
		assertThat(myDTO.getWeergavenaam()).isNotNull();
		assertThat(myDTO.getWoonplaatscode()).isNotNull();
		assertThat(myDTO.getWoonplaatsnaam()).isNotNull();
		assertThat(myDTO.getGemeentecode()).isNotNull();
		assertThat(myDTO.getGemeentenaam()).isNotNull();
		assertThat(myDTO.getProvincienaam()).isNotNull();
	}

	@Test
	void pdokPostcodesEndpointJsonMapsCorrectlyToDTO(){

		List<WoonplaatsDTO> list = apiClient.getWoonplaatsen(1, 0);

		assertThat(list).hasSize(1);

		String wpc = list.getFirst().getWoonplaatscode();

		List<Postcode4DTO> pList = apiClient.fetchPostcodes(wpc);

		assertThat(pList).isNotEmpty();
	}

	@Test
	void cbsBuurtenEndpointJsonMapsCorrectlyToDto(){

		List<BuurtDTO> list = apiClient.fetchBuurten(10,0);

		assertThat(list).hasSize(10);

		BuurtDTO bdto = list.getFirst();

		assertThat(bdto.getGemeentenaam_1()).isNotNull();
	}

	@Test
	void textCreationTest(){

		TextObject text = textService.getText("3594");

		String firstParagraph = text.getText().getFirst();
		assertThat(firstParagraph).hasSizeGreaterThan(5);
	}

	@Test
	void dbTablesIntegrityCheck(){

		List<Woonplaats> list = woonplaatsRepository.findAll();
		assertThat(apiIntegrityCheck.isCompleteWoonplaatsFetchedList(list)).isTrue();

		List<Buurt> list2 = buurtRepository.findAll();
		assertThat(apiIntegrityCheck.isCompleteBuurtFetchedList(list2)).isTrue();

		List<Postcode4> list3 = postcode4Repository.findAll();
		Set<Postcode4> set = new HashSet<>(list3);
		set.add(new Postcode4(null,null));
		assertThat(apiIntegrityCheck.isCompletePostcodesFetchedSet(set)).isFalse();
	}

}


