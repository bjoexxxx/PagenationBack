package com.example.pagnationdel2.repository;

import com.example.pagnationdel2.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {
    Page<Car> findByBrand(String brand, Pageable pageable);
    Page<Car> findByModel(String model, Pageable pageable);
    Page<Car> findByColor(String color, Pageable pageable);

}
