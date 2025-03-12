package com.example.mcazdrugappsystem.Repository;

import com.example.mcazdrugappsystem.Model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface ApplicationDrugRepository extends JpaRepository<Drug, Integer> {

   List<Drug> findByDosageForm(Drug.ApplicationStutus applicationStutus);

}
