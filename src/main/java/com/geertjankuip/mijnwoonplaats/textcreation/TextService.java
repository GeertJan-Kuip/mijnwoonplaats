package com.geertjankuip.mijnwoonplaats.textcreation;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TextService {

    DataAssessment dataAssessment;
    TextCreator textCreator;

    public TextService(DataAssessment dataAssessment, TextCreator textCreator) {
        this.dataAssessment = dataAssessment;
        this.textCreator = textCreator;
    }

    public TextObject getText(String woonplaatsCode){

        WoonplaatsData woonplaatsData = dataAssessment.getWoonplaatsDataWithAssessment(woonplaatsCode);
        TextObject textObject;

        if (woonplaatsData!=null) {
            textObject = new TextObject(textCreator.getText(woonplaatsData));
        } else {
            textObject = new TextObject(List.of("Geen data kunnen vinden. Probeer een nieuwe zoekopdracht."));
        }
        return textObject;
    }
}
