package model.entity;

import java.time.LocalDate;

public class LegalPerson {
    private long id;
    private String company;
    private LocalDate RegisterDate;
    private String economicCode;

    public LegalPerson(String company, LocalDate registerDate, String economicCode) {
        this.company = company;
        RegisterDate = registerDate;
        this.economicCode = economicCode;
    }

    public LegalPerson(long id, String company, LocalDate registerDate, String economicCode) {
        this.id = id;
        this.company = company;
        RegisterDate = registerDate;
        this.economicCode = economicCode;
    }

    public LegalPerson() {
    }

    public long getId() {
        return id;
    }

    public LegalPerson setId(long id) {
        this.id = id;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public LegalPerson setCompany(String company) {
        this.company = company;
        return this;
    }

    public LocalDate getRegisterDate() {
        return RegisterDate;
    }

    public LegalPerson setRegisterDate(LocalDate registerDate) {
        RegisterDate = registerDate;
        return this;
    }

    public String getEconomicCode() {
        return economicCode;
    }

    public LegalPerson setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
        return this;
    }
}
