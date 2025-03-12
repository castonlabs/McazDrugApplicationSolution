package com.example.mcazdrugappsystem.Controller;

import com.example.mcazdrugappsystem.Model.Drug;
import com.example.mcazdrugappsystem.Repository.ApplicationDrugRepository;
import com.example.mcazdrugappsystem.Service.DrugApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/drugs")
public class ApplicationDrugController {
    private final ApplicationDrugRepository applicationDrugRepository;
    public DrugApplicationService drugApplicationService;
@Autowired
    public ApplicationDrugController(DrugApplicationService drugApplicationService, ApplicationDrugRepository applicationDrugRepository) {
        this.drugApplicationService = drugApplicationService;
    this.applicationDrugRepository = applicationDrugRepository;
}
    @PostMapping("/add")
    public ResponseEntity<String> addDrug(@RequestBody Drug drug){
    drugApplicationService.saveDrug(drug);
    return ResponseEntity.ok("the request has been processed");
    }
    @GetMapping("/all")
    public List<Drug> getAllDrugs(){
   return drugApplicationService.getAllDrugs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Drug>> getById(@PathVariable int id){
     Optional<Drug> drug = drugApplicationService.getById(id);
    return ResponseEntity.ok(drug);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDrug(@PathVariable int id, @RequestBody Drug updatedDrug) {
        Drug existingDrug = drugApplicationService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drug not found"));

        // Update only the necessary fields

        existingDrug.setDosageForm(updatedDrug.getDosageForm());
        existingDrug.setCountryOfOrigin(updatedDrug.getCountryOfOrigin());
        existingDrug.setExpiryDate(updatedDrug.getExpiryDate());

        drugApplicationService.saveDrug(existingDrug);
        return ResponseEntity.ok("Drug updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDrug(@PathVariable int id) {
        Drug existingDrug = drugApplicationService.deleteDrugsById(id);
        drugApplicationService.deleteDrug();
        return ResponseEntity.ok("Drug deleted successfully");
    }


}