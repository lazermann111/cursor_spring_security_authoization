package com.cursor.hw17.controller;

import com.cursor.hw17.entity.Laptop;
import com.cursor.hw17.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/laptop")
public class LaptopController {
    private final LaptopService notebookService;

    @Autowired
    public LaptopController(LaptopService notebookService) {
        this.notebookService = notebookService;
    }

    @GetMapping("/create/{amount}")
    @Secured("ROLE_ADMIN")
    public List<Laptop> createLaptops(@PathVariable Integer amount) {
        return notebookService.createLaptops(amount);
    }

    @GetMapping("/all")
    @Secured("ROLE_READ")
    public List<Laptop> noteBookList() {
        return notebookService.getAll();
    }

    @GetMapping("/sort/brand")
    public List<Laptop> sortByBrand() {
        return notebookService.getByManufacturerAsc();
    }

    @GetMapping("/sort/ram/{mem}")
    public List<Laptop> sortByRam(@PathVariable int mem) {
        return notebookService.getByRamGreaterThan(mem);
    }

    @GetMapping("sort/used")
    public List<Laptop> sortByCondition() {
        return notebookService.getUsedIsTrue();
    }

}
