package model.entity;

import java.time.LocalDate;

public class RealPerson {
    private Long id;
    private String name;
    private String family;
    private String fatherName;
    private LocalDate birthDate;
    private String nationalCode;

    public RealPerson(String name, String family, String fatherName, LocalDate birthDate, String nationalCode) {
        this.name = name;
        this.family = family;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.nationalCode = nationalCode;
    }

    public RealPerson(Long id, String name, String family, String fatherName, LocalDate birthDate, String nationalCode) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.nationalCode = nationalCode;
    }

    public RealPerson() {
    }

    public Long getId() {
        return id;
    }

    public RealPerson setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RealPerson setName(String name) {
        this.name = name;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public RealPerson setFamily(String family) {
        this.family = family;
        return this;
    }

    public String getFatherName() {
        return fatherName;
    }

    public RealPerson setFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public RealPerson setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public RealPerson setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
        return this;
    }
}
