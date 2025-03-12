package com.example.mcazdrugappsystem.Model;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
@Entity
@Table( name ="drugtable")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String CountryOfOrigin;
    private String capturedBy;
    private String approvedBy;
    private LocalDate dateAproved;
    private LocalDate expiryDate;
    private LocalTime approvedDate;
    @Enumerated(EnumType.STRING)
    private ApplicationStutus applicationStutus;
    @Enumerated(EnumType.STRING)
    private DosageForm dosageForm;
    public enum ApplicationStutus{
        APPROVED,REGECTED,PENDING
    }
    public enum DosageForm{
        ORAL,VIGANIAL,EXTERNAL
    }
    @PrePersist
    public void setAttributes(){
        this.applicationStutus = ApplicationStutus.PENDING;
        this.dateAproved = LocalDate.now();
        this.expiryDate = LocalDate.now().plusYears(3);
    }
}
