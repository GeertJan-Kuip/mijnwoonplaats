package com.geertjankuip.mijnwoonplaats;

import com.geertjankuip.mijnwoonplaats.repositories.WoonplaatsRepository;
import com.geertjankuip.mijnwoonplaats.textcreation.TextObject;
import com.geertjankuip.mijnwoonplaats.textcreation.TextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Controller
public class ControllerClass {

    private static final Logger log = LoggerFactory.getLogger(ControllerClass.class);

    WoonplaatsRepository woonplaatsRepo;
    TextService textService;

    @Value("${homepage.textfile}")
    private Path homepageFile;


    public ControllerClass(WoonplaatsRepository woonplaatsRepo, TextService textService) {
        this.woonplaatsRepo = woonplaatsRepo;
        this.textService = textService;
    }

    private String renderLeftText(){
        StringBuilder strb = new StringBuilder();
        if (Files.exists(homepageFile)) {
            try {
                Files.lines(homepageFile).forEach(strb::append);
            }catch(IOException e){ }
        }
        return strb.toString();
    }

    @GetMapping("/")
    public String getBasePage(Model model){
        model.addAttribute("resultText", "Geert-Jan");

        model.addAttribute("textLeft", renderLeftText());

        return "main";
    }

    @GetMapping("/info/{simpleName}/{id}")
    public String townInfo(@PathVariable("simpleName") String simpleName,
                           @PathVariable("id") Long id, Model model) {
        model.addAttribute("textLeft", renderLeftText());
        return "main";
    }

    @GetMapping("api/town/{id}")
    public @ResponseBody String getTownInfo(@PathVariable Long id) {

        String woonplaatsCode = woonplaatsRepo.findWoonplaatscodeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plaats niet gevonden."));

        TextObject textObject = textService.getText(woonplaatsCode);

        StringBuilder returnValue = new StringBuilder();

        for(String paragraph: textObject.getText()) {
            returnValue.append("<p>");
            returnValue.append(paragraph);
            returnValue.append("</p>");
        }

        log.info("New town being displayed. Id: " + id);
        return returnValue.toString();
    }

    @GetMapping("/api/suggestions")
    public @ResponseBody List<Map<String, String>> suggestTowns(@RequestParam("q") String query){

        if (query.length() < 3) {
            return List.of();
        }

        return woonplaatsRepo.findByWeergavenaamStartingWithIgnoreCase(query)
                .stream()
                .map(t -> Map.of("id", t.getId().toString(), "name", t.getWeergavenaam()))
                .toList();
    }
}
