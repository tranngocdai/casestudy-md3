package com.case3.service.ren_exp;

import com.case3.model.Expenditure;

import java.util.Date;
import java.util.List;

public class ExpenditureService implements IRenExpService<Expenditure>{
    @Override
    public List<Expenditure> findAll() {
        return null;
    }

    @Override
    public Expenditure findById(int id) {
        return null;
    }

    @Override
    public void save(Expenditure expenditure) {

    }

    @Override
    public void edit(Expenditure expenditure, int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Expenditure> findByDay(Date date, int id_user) {
        return null;
    }

    @Override
    public List<Expenditure> findByWeek(Date date, int id_user) {
        return null;
    }

    @Override
    public List<Expenditure> findByMonth(Date date, int id_user) {
        return null;
    }
}
