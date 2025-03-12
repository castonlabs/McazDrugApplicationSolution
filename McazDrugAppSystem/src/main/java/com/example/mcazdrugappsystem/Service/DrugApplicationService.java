package com.example.mcazdrugappsystem.Service;

import com.example.mcazdrugappsystem.Model.Drug;
import com.example.mcazdrugappsystem.Repository.ApplicationDrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrugApplicationService {
    ApplicationDrugRepository applicationDrugRepository;

    @Autowired
    public DrugApplicationService(ApplicationDrugRepository applicationDrugRepository) {
        this.applicationDrugRepository = applicationDrugRepository;
    }
//meethod to reject
    public List<Drug> rejectedDrugs() {
        List<Drug> drugs = applicationDrugRepository.findAll();
        for (Drug drug : drugs) {
            if (drug.getDosageForm() == Drug.DosageForm.VIGANIAL && drug.getApplicationStutus()== Drug.ApplicationStutus.PENDING) {

                drug.setApplicationStutus(Drug.ApplicationStutus.REGECTED);
                applicationDrugRepository.save(drug);
            }

        }
        return applicationDrugRepository.findByDosageForm(Drug.ApplicationStutus.REGECTED);
    }
    //method to approve

    //method to findAll
    public List<Drug> getAllDrugs(){
        return applicationDrugRepository.findAll();

    }
    //method to find drugs by id

    public Optional<Drug> getById(int id){
        return applicationDrugRepository.findById(id);
    }
    //delete all drugs
    public  void deleteDrug(){
        applicationDrugRepository.deleteAll();
    }
    public Drug deleteDrugsById(int id){
        applicationDrugRepository.deleteById(id);
        return null;
    }

// method to save drugs
    public void saveDrug (Drug drug){
        applicationDrugRepository.save(drug);
    }

}




