package model.service;

import model.entity.LegalPerson;
import model.repository.LegalPersonDa;

import java.util.List;

public class LegalPersonService {
    private static LegalPersonService legalPersonService=new LegalPersonService();
    private LegalPersonService(){}

    public static LegalPersonService getInstance() {
        return legalPersonService;
    }

    public void persist(LegalPerson legalPerson) throws Exception{
        try(LegalPersonDa legalPersonDa=new LegalPersonDa()) {
            legalPersonDa.persist(legalPerson);
            System.out.println("ok");
        }
    }

    public void update(LegalPerson legalPerson) throws Exception{
        try(LegalPersonDa legalPersonDa=new LegalPersonDa()) {
            legalPersonDa.update(legalPerson);
        }
    }

    public void delete(LegalPerson legalPerson) throws Exception{
        try(LegalPersonDa legalPersonDa=new LegalPersonDa()) {
            legalPersonDa.delete(legalPerson);
        }
    }

    public List<LegalPerson> findAll() throws Exception{
        try(LegalPersonDa legalPersonDa=new LegalPersonDa()) {
           return legalPersonDa.findAll();
        }
    }

    public List<LegalPerson> findByCompany(String company) throws Exception{
        try(LegalPersonDa legalPersonDa=new LegalPersonDa()) {
            return legalPersonDa.findByCompany(company);
        }
    }

    public List<LegalPerson> findByEconomicCode(String economicCode) throws Exception{
        try(LegalPersonDa legalPersonDa=new LegalPersonDa()) {
            return legalPersonDa.findByEconomicCode(economicCode);
        }
    }

    public List<LegalPerson> findById(long id) throws Exception{
        try(LegalPersonDa legalPersonDa=new LegalPersonDa()) {
            return legalPersonDa.findById(id);
        }
    }


}
