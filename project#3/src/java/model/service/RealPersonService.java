package model.service;

import model.entity.RealPerson;
import model.repository.RealPersonDa;

import java.util.ArrayList;
import java.util.List;

public class RealPersonService {
    private static RealPersonService realPersonService=new RealPersonService();
    private RealPersonService() {}

    public static RealPersonService getInstance() {
        return realPersonService;
    }

    public void persist(RealPerson realPerson) throws Exception{
        try(RealPersonDa realPersonDa=new RealPersonDa()){
            realPersonDa.persist(realPerson);
        }
    }

    public void update(RealPerson realPerson) throws Exception{
        try(RealPersonDa realPersonDa=new RealPersonDa()){
            realPersonDa.update(realPerson);
        }
    }

    public void delete(RealPerson realPerson) throws Exception{
        try(RealPersonDa realPersonDa=new RealPersonDa()){
            realPersonDa.delete(realPerson);
        }
    }

    public List<RealPerson> findAll() throws Exception{
        try(RealPersonDa realPersonDa=new RealPersonDa()) {
           return realPersonDa.findAll();
        }
    }
    public List<RealPerson> findByName(String name) throws Exception{
        try(RealPersonDa realPersonDa=new RealPersonDa()) {
            return realPersonDa.findByName(name);
        }
    }
    public List<RealPerson> findByFamily(String family) throws Exception{
        try(RealPersonDa realPersonDa=new RealPersonDa()) {
            return realPersonDa.findByFamily(family);
        }
    }

    public List<RealPerson> findByNationalCode(String nationalCode) throws Exception{
        try(RealPersonDa realPersonDa=new RealPersonDa()){
            return realPersonDa.findByNationalCode(nationalCode);
        }
    }

    public List<RealPerson> findById(Long id) throws Exception{
        try(RealPersonDa realPersonDa=new RealPersonDa()){
            return realPersonDa.findById(id);
        }
    }

    public List<RealPerson> conditionalFind(RealPerson realPerson) throws Exception{
        StringBuilder where=new StringBuilder("select * from realPerson where ");
        List<String> paramerterList=new ArrayList<>();

        if( realPerson.getName()!=null && (!realPerson.getName().trim().equals("")) ){
            where.append("name=? and ");
            paramerterList.add(realPerson.getName());
        }
        if(realPerson.getFamily()!=null && (!realPerson.getFamily().trim().equals(""))){
            where.append("family=? and ");
            paramerterList.add(realPerson.getFamily());
        }
        if(realPerson.getFatherName()!=null && (!realPerson.getFatherName().trim().equals(""))){
            where.append("fathername=?");
            paramerterList.add(realPerson.getFatherName());
        }
        if(where.toString().endsWith("and ")){
            where.delete(where.toString().lastIndexOf("and"),where.length());
        }
        try(RealPersonDa realPersonDa=new RealPersonDa()){
            return realPersonDa.conditionalFind(where.toString(),paramerterList);
        }
    }
}
