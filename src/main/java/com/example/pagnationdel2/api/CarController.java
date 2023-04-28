package com.example.pagnationdel2.api;

import com.example.pagnationdel2.entity.Car;
import com.example.pagnationdel2.repository.CarRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping()
    public List<Car> getCars(Pageable pageable){
        Page<Car> carPage = carRepository.findAll(pageable);
        return carPage.getContent();
    }

    @GetMapping("/brand/{brand}")
    public List<Car> getBrandedCars(@PathVariable String brand, Pageable pageable){
        Page<Car> carPage = carRepository.findByBrand(brand, pageable);
        return carPage.getContent();
    }

    @GetMapping("/total")
    public long getTotalCars() {
        return carRepository.count();
    }

    @GetMapping("/filter")
    public List<Car> filterCars(@RequestParam String column, @RequestParam String val,
                                Pageable pageable){
        Page<Car> carPage;

        if ("brand".equalsIgnoreCase(column)) {
            carPage = carRepository.findByBrand(val, pageable);
        } else if ("color".equalsIgnoreCase(column)) {
            carPage = carRepository.findByColor(val, pageable);
        } else if ("model".equalsIgnoreCase(column)){
            carPage = carRepository.findByModel(val, pageable);
        } else {
            carPage = carRepository.findAll(pageable);
        }

        return carPage.getContent();
    }
/*
    @GetMapping("/filter")
    public List<Car> filterCars(@RequestParam String column, @RequestParam String val, Pageable pageable) {
        Specification<Car> carSpecification = (Root<Car> root, CriteriaQuery<?> query,
                                               CriteriaBuilder criteriaBuilder) -> {
            if ("brand".equalsIgnoreCase(column)) {
                return criteriaBuilder.equal(root.get("brand"), val);
            } else if ("color".equalsIgnoreCase(column)) {
                return criteriaBuilder.equal(root.get("color"), val);
            }
            return null;
        };

        Page<Car> carPage = carRepository.findAll(carSpecification, pageable);
        return carPage.getContent();
    }

 */
}
