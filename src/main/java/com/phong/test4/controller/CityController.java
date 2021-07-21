package com.phong.test4.controller;

import com.phong.test4.model.City;
import com.phong.test4.model.Country;
import com.phong.test4.service.ICityService;
import com.phong.test4.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class CityController {
    @Autowired
    private ICityService cityService;
    @Autowired
    private ICountryService countryService;
    @ModelAttribute("countries")
    public Iterable<Country> countries() {
        return countryService.findAll();
    }

    //City
    @GetMapping("/")
    public ModelAndView listCities() {
        Iterable<City> cities = cityService.findAll();
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/create-city")
    public ModelAndView showCreateCity() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/create-city")
    public ModelAndView saveCity(@Validated @ModelAttribute("city") City city, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/create");
            modelAndView.addObject("city", new City());
            modelAndView.addObject("message", "Name City error !!!");
            return modelAndView;
        }
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("city", new City());
        modelAndView.addObject("message", "New city created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-city/{id}")
    public ModelAndView showEditCity(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("city", city.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-city")
    public ModelAndView updateCity(@Validated @ModelAttribute("city") City city, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("city", city);
            modelAndView.addObject("message", "Name City error !!!");
            return modelAndView;
        }
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("city", city);
        modelAndView.addObject("message", "City updated successfully");
        return modelAndView;
    }

    @GetMapping("/show-city/{id}")
    public ModelAndView showCity(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/show");
            modelAndView.addObject("city", city.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @GetMapping("/delete-city/{id}")
    public ModelAndView showDeleteCity(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("city", city.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-city")
    public String deleteCity(@ModelAttribute("city") City city) {
        cityService.remove(city.getIdCity());
        return "redirect:/";
    }
}
