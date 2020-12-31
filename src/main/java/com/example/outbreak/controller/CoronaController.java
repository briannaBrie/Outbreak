package com.example.outbreak.controller;

import com.example.outbreak.model.CoronaStats;
import com.example.outbreak.service.CoronaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class CoronaController {
    @Autowired
    CoronaDataService coronaDataService;
    //Ebola1Service ebola1Service;

    @GetMapping("/corona")
    public String getCoronaCases(Model model){//return a template of ui html value
        //here we will put the daily statistics from DiseaseDataService.java
        List<CoronaStats> allStats = coronaDataService.getAllStats();

        //map all the integer values for each country/state and then sum them up for the total cases globally
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "/corona";//return templates/home.html
    }
}
