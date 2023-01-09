package com.example.nutris.physicalActivity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhysicalActivityRepository extends JpaRepository<PhysicalActivity, Long> {
    Optional<PhysicalActivity> findByName(String name);

    List<PhysicalActivity> findByDateBetween(Date startDate, Date endDate);

}

