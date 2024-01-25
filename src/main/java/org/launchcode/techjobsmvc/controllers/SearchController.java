package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        model.addAttribute("columnChoices", columnChoices);
        if (searchTerm.equals("all") || searchTerm.equals("") || searchTerm.isBlank()){
            model.addAttribute("jobs",JobData.findAll());
            return "search.html";
        }
        model.addAttribute("jobs", JobData.findByColumnAndValue(searchType,searchTerm));
        return "search.html";
    }


}

