package com.cursor.hw17.service;

import com.cursor.hw17.entity.Laptop;
import com.cursor.hw17.repository.NotebookRepository;
import com.cursor.hw17.utils.LaptopGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class LaptopService {

    private final LaptopGenerator laptopGenerator = new LaptopGenerator();
    private final NotebookRepository notebookRepository;


    @Autowired
    LaptopService(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }


    public List<Laptop> createLaptops(int amount) {
        List<Laptop> laptopList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Laptop laptop = notebookRepository.save(
                    new Laptop(
                            laptopGenerator.Model(),
                            laptopGenerator.Brand(),
                            laptopGenerator.Processor(),
                            laptopGenerator.Ram(),
                            laptopGenerator.Used(),
                            laptopGenerator.CaseType(),
                            laptopGenerator.Price(),
                            laptopGenerator.dateOfProduction()));
            laptopList.add(laptop);
        }
        return laptopList;
    }

    public List<Laptop> getAll() {
        return new ArrayList<>(notebookRepository.findAll());
    }

    public List<Laptop> getByManufacturerAsc() {
        return new ArrayList<>(notebookRepository.findByOrderByBrandAsc());
    }

    public List<Laptop> getByRamGreaterThan(int ram) {
        return new ArrayList<>(notebookRepository.findByRamGreaterThan(ram));
    }

    public List<Laptop> getUsedIsTrue() {
        return new ArrayList<>(notebookRepository.findByUsedIsTrue());
    }
}
