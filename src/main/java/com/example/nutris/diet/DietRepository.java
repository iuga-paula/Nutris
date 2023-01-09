package com.example.nutris.diet;
import com.example.nutris.physicalActivity.PhysicalActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {
    List<Diet> findByDateBetween(Date startDate, Date endDate);
}
