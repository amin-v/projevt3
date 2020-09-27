package model.repository;

import configControll.DBCP2;
import commons.exceptions.BusinessException;
import model.entity.LegalPerson;
import model.entity.RealPerson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static commons.ExceptionMessages.*;

public class LegalPersonDa implements AutoCloseable{
    Connection connection;
    PreparedStatement preparedStatement;

    public LegalPersonDa() throws Exception{
        connection= DBCP2.getConnection();
        connection.setAutoCommit(false);
    }

    public void persist(LegalPerson legalPerson) throws Exception{
        try {
            preparedStatement = connection.prepareStatement("select person_seq.nextval from dual");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            legalPerson.setId(resultSet.getLong(1));
            preparedStatement = connection.prepareStatement("insert into LegalPerson (id,company,registerDate,economicCode) values (?,?,?,?) ");
            preparedStatement.setLong(1, legalPerson.getId());
            preparedStatement.setString(2, legalPerson.getCompany());
            preparedStatement.setDate(3, Date.valueOf(legalPerson.getRegisterDate().toString()));
            preparedStatement.setString(4, legalPerson.getEconomicCode());
            preparedStatement.executeUpdate();
        }catch (SQLIntegrityConstraintViolationException e){
            throw new BusinessException(REPETETIVE_ECONOMIC_CODE);
        }
    }

    public void update(LegalPerson legalPerson) throws Exception{
        try {
            preparedStatement = connection.prepareStatement("update legalperson set company=?,registerDate=?,economicCode=? where id=?");
            preparedStatement.setString(1, legalPerson.getCompany());
            preparedStatement.setDate(2, Date.valueOf(legalPerson.getRegisterDate().toString()));
            preparedStatement.setString(3, legalPerson.getEconomicCode());
            preparedStatement.setLong(4, legalPerson.getId());
            preparedStatement.executeUpdate();
        }catch (SQLIntegrityConstraintViolationException e){
            throw new BusinessException(REPETETIVE_ECONOMIC_CODE);
        }
    }

    public void delete(LegalPerson legalPerson) throws Exception{
        preparedStatement=connection.prepareStatement("delete from legalperson where id=?");
        preparedStatement.setLong(1,legalPerson.getId());
        preparedStatement.executeUpdate();
    }

    public List<LegalPerson> findAll() throws Exception{
        preparedStatement=connection.prepareStatement("select * from legalPerson");
        ResultSet resultSet=preparedStatement.executeQuery();
        List<LegalPerson> legalPersonList=new ArrayList<>();
        while (resultSet.next()) {
            LegalPerson legalPerson=new LegalPerson().setId(resultSet.getLong("id")).setCompany(resultSet.getString("company"))
                                                        .setRegisterDate(resultSet.getDate("registerDate").toLocalDate()).setEconomicCode(resultSet.getString("economicCode"));
            legalPersonList.add(legalPerson);
        }
        return legalPersonList;
    }

    public List<LegalPerson> findByCompany(String company) throws Exception{

            preparedStatement = connection.prepareStatement("select * from legalPerson where company=?");
            preparedStatement.setString(1, company);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<LegalPerson> legalPersonList = new ArrayList<>();
            if(resultSet.next()) {
                do {
                    LegalPerson legalPerson = new LegalPerson().setId(resultSet.getLong("id")).setCompany(resultSet.getString("company"))
                            .setRegisterDate(resultSet.getDate("registerDate").toLocalDate()).setEconomicCode(resultSet.getString("economicCode"));
                    legalPersonList.add(legalPerson);
                } while (resultSet.next());
            }else {
                throw new BusinessException(COMPANY_NOT_FOUND);
            }
            return legalPersonList;
    }

    public List<LegalPerson> findByEconomicCode(String economicCode) throws Exception{
        preparedStatement=connection.prepareStatement("select * from legalPerson where economicCode=?");
        preparedStatement.setString(1,economicCode);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<LegalPerson> legalPersonList=new ArrayList<>();
        if(resultSet.next()){
           LegalPerson legalPerson=  new LegalPerson().setId(resultSet.getLong("id")).setCompany(resultSet.getString("company"))
                    .setRegisterDate(resultSet.getDate("registerDate").toLocalDate()).setEconomicCode(resultSet.getString("economicCode"));
             legalPersonList.add(legalPerson);
        } else {
            throw new BusinessException(ENTITY_NOT_FOUND);
        }
        return legalPersonList;
    }


    public List<LegalPerson> findById(long id) throws Exception{
        preparedStatement=connection.prepareStatement("select * from legalPerson where id=?");
        preparedStatement.setLong(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<LegalPerson> legalPersonList=new ArrayList<>();
        if(resultSet.next()){
            LegalPerson legalPerson=  new LegalPerson().setId(resultSet.getLong("id")).setCompany(resultSet.getString("company"))
                    .setRegisterDate(resultSet.getDate("registerDate").toLocalDate()).setEconomicCode(resultSet.getString("economicCode"));
            legalPersonList.add(legalPerson);
        } else {
            throw new BusinessException(ENTITY_NOT_FOUND);
        }
        return legalPersonList;
    }

    @Override
    public void close() throws Exception {
        connection.commit();
        preparedStatement.close();
        connection.close();
    }
}
