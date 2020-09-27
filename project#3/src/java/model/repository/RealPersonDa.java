package model.repository;


import commons.ExceptionMessages;
import commons.exceptions.BusinessException;
import configControll.DBCP2;
import model.entity.RealPerson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static commons.ExceptionMessages.*;

public class RealPersonDa implements AutoCloseable {
    Connection connection;
    PreparedStatement preparedStatement;

    public RealPersonDa() throws Exception {
        connection = DBCP2.getConnection();
        connection.setAutoCommit(false);
    }

    public void persist(RealPerson realPerson) throws Exception {
        try {
            preparedStatement = connection.prepareStatement("select person_seq.nextval from dual");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            realPerson.setId(resultSet.getLong(1));
            preparedStatement = connection.prepareStatement("insert into realPerson (id,name,family,fatherName,birthDate,nationalCode) values (?,?,?,?,?,?)");
            preparedStatement.setLong(1, realPerson.getId());
            preparedStatement.setString(2, realPerson.getName());
            preparedStatement.setString(3, realPerson.getFamily());
            preparedStatement.setString(4, realPerson.getFatherName());
            preparedStatement.setDate(5, Date.valueOf(realPerson.getBirthDate().toString()));
            preparedStatement.setString(6, realPerson.getNationalCode());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new BusinessException(REPETETIVE_NATIONAL_CODE);
        }
    }

    public void update(RealPerson realPerson) throws Exception {
        try {
            preparedStatement = connection.prepareStatement("update realperson set name=?,family=?,fathername=?,birthDate=?,nationalCode=? where id=?");
            preparedStatement.setString(1, realPerson.getName());
            preparedStatement.setString(2, realPerson.getFamily());
            preparedStatement.setString(3, realPerson.getFatherName());
            preparedStatement.setDate(4, Date.valueOf(realPerson.getBirthDate().toString()));
            preparedStatement.setString(5, realPerson.getNationalCode());
            preparedStatement.setLong(6, realPerson.getId());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new BusinessException(REPETETIVE_NATIONAL_CODE);
        }
    }

    public void delete(RealPerson realPerson) throws Exception {
        preparedStatement = connection.prepareStatement("delete from realperson where id=?");
        preparedStatement.setLong(1, realPerson.getId());
        preparedStatement.executeUpdate();

    }

    public List<RealPerson> findAll() throws Exception {
        preparedStatement = connection.prepareStatement("select * from realPerson");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<RealPerson> realPersonList = new ArrayList<>();
        while (resultSet.next()) {
            RealPerson realPerson = new RealPerson().setId(resultSet.getLong("id")).setName(resultSet.getString("name"))
                    .setFamily(resultSet.getString("family")).setFatherName(resultSet.getString("fathername"))
                    .setBirthDate(resultSet.getDate("birthDate").toLocalDate()).setNationalCode(resultSet.getString("nationalCode"));
            realPersonList.add(realPerson);
        }
        return realPersonList;
    }

    public List<RealPerson> findByName(String name) throws Exception {
        preparedStatement = connection.prepareStatement("select * from realPerson where name=?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<RealPerson> realPersonList = new ArrayList<>();
        if (resultSet.next()) {
            do {
                RealPerson realPerson = new RealPerson().setId(resultSet.getLong("id")).setName(resultSet.getString("name"))
                        .setFamily(resultSet.getString("family")).setFatherName(resultSet.getString("fathername"))
                        .setBirthDate(resultSet.getDate("birthDate").toLocalDate()).setNationalCode(resultSet.getString("nationalCode"));
                realPersonList.add(realPerson);
            } while (resultSet.next());
        } else {
            throw new BusinessException(NAME_NOT_FOUND);
        }
        return realPersonList;
    }

    public List<RealPerson> findByFamily(String family) throws Exception {
        preparedStatement = connection.prepareStatement("select * from realPerson where family=?");
        preparedStatement.setString(1, family);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<RealPerson> realPersonList = new ArrayList<>();
        if (resultSet.next()) {
            do {
                RealPerson realPerson = new RealPerson().setId(resultSet.getLong("id")).setName(resultSet.getString("name"))
                        .setFamily(resultSet.getString("family")).setFatherName(resultSet.getString("fathername"))
                        .setBirthDate(resultSet.getDate("birthDate").toLocalDate()).setNationalCode(resultSet.getString("nationalCode"));
                realPersonList.add(realPerson);
            } while (resultSet.next());
        } else {
            throw new BusinessException(FAMILY_NOT_FOUND);
        }
        return realPersonList;
    }

    public List<RealPerson> findByNationalCode(String nationalCode) throws Exception {
        preparedStatement = connection.prepareStatement("select * from realPerson where nationalCode=?");
        preparedStatement.setString(1, nationalCode);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<RealPerson> realPersonList=new ArrayList<>();
        if (resultSet.next()) {
            RealPerson realPerson= new RealPerson().setId(resultSet.getLong("id")).setName(resultSet.getString("name"))
                    .setFamily(resultSet.getString("family")).setFatherName(resultSet.getString("fathername"))
                    .setBirthDate(resultSet.getDate("birthDate").toLocalDate()).setNationalCode(resultSet.getString("nationalCode"));
            realPersonList.add(realPerson);
        } else {
            throw new BusinessException(RealCustomer_Entity_NOT_FOUND);
        }
        return realPersonList;
    }

    public List<RealPerson> findById(Long id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from realPerson where id=?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<RealPerson> realPersonList=new ArrayList<>();
        if (resultSet.next()) {
            RealPerson realPerson= new RealPerson().setId(resultSet.getLong("id")).setName(resultSet.getString("name"))
                    .setFamily(resultSet.getString("family")).setFatherName(resultSet.getString("fathername"))
                    .setBirthDate(resultSet.getDate("birthDate").toLocalDate()).setNationalCode(resultSet.getString("nationalCode"));
             realPersonList.add(realPerson);
        } else {
            throw new BusinessException(RealCustomer_Entity_NOT_FOUND);
        }
        return realPersonList;
    }

    public List<RealPerson> conditionalFind(String where,List<String> parameterList) throws Exception{
        preparedStatement=connection.prepareStatement(where);
        int i=1;
        System.out.println(where);
        for (String parameter : parameterList) {
            System.out.println(parameter);
            System.out.println(i);
            preparedStatement.setString(i,parameter);
            i++;
        }
        ResultSet resultSet=preparedStatement.executeQuery();
        List<RealPerson> realPersonList=new ArrayList<>();
        if(resultSet.next()){
            do {
                RealPerson realPerson = new RealPerson().setId(resultSet.getLong("id")).setName(resultSet.getString("name"))
                        .setFamily(resultSet.getString("family")).setFatherName(resultSet.getString("fathername"))
                        .setBirthDate(resultSet.getDate("birthDate").toLocalDate()).setNationalCode(resultSet.getString("nationalCode"));
                realPersonList.add(realPerson);
            }while (resultSet.next());
        }else {
            throw new BusinessException(ExceptionMessages.REALCUSTOMER_NOT_FOUND);
        }
        resultSet.close();
        return realPersonList;
    }

    @Override
    public void close() throws Exception {
        connection.commit();
        preparedStatement.close();
        connection.close();
    }
}
