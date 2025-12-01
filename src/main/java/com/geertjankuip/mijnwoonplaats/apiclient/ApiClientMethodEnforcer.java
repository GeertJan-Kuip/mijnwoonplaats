package com.geertjankuip.mijnwoonplaats.apiclient;

import com.geertjankuip.mijnwoonplaats.datatransferobjects.BuurtDTO;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.Postcode4DTO;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.WoonplaatsDTO;
import java.util.List;
import java.util.Set;

public interface ApiClientMethodEnforcer {

    List<WoonplaatsDTO> getWoonplaatsen(int rows, int start);
    List<WoonplaatsDTO> fetchWoonplaatsen(int rows, int start);
    void fetchWoonplaatsDetail(List<WoonplaatsDTO> list);

    List<BuurtDTO> getBuurten();
    List<BuurtDTO> fetchBuurten(int top, int skip);

    Set<Postcode4DTO> getPostcodes(Set<String> woonplaatsCodes);
    List<Postcode4DTO> fetchPostcodes(String woonplaatscode);

}
