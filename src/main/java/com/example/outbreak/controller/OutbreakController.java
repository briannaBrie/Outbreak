package com.example.outbreak.controller;

import com.example.outbreak.model.OutbreakData;
import com.example.outbreak.service.OutbreakDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class OutbreakController {
    @Autowired
    private OutbreakDAO dao;

    @GetMapping("/outbreaks")
    public String viewHomePage(Model model) {
        List<OutbreakData> outbreakDataList = dao.list();
        //String today = Calendar.getInstance().getTime().toString();
        LocalDate today = LocalDate.now();
        String todayString = today.toString();
        model.addAttribute("outbreakDataList", outbreakDataList);
//        model.addAttribute("today", todayString);
        return "/outbreaks";
    }

    //insert
    @PostMapping("/outbreaks")
    public String save(@RequestParam(value = "disease") String disease,
                       @RequestParam(value = "region") String region,
                       @RequestParam(value = "cases") int cases,
                       Model model) {

        dao.save(new OutbreakData(disease, region, cases));
        model.addAttribute("outbreakDataList", dao.list());
        return "/outbreaks";
    }
    //update
    @PutMapping("/outbreaks/{id")
    public String update(@RequestParam(value = "disease") String disease,
                         @RequestParam(value = "region") String region,
                         @RequestParam(value = "cases") int cases,
                         Model model){
        dao.update(new OutbreakData(disease, region, cases));
        model.addAttribute("outbreakDataList", dao.list());
        return "/outbreaks/{id";
    }
    //edit
    /* */
    @RequestMapping("/outbreaks/{id")
    public ModelAndView edit(@PathVariable (name = "id") int id){
        ModelAndView mav = new ModelAndView("edit_form");
        OutbreakData outbreakData = dao.get(id);
        mav.addObject("outbreakData",outbreakData);

        return mav;
    }
}
