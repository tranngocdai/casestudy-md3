package com.case3.service.ren_exp;

import com.case3.model.Revenue;

import java.util.Date;
import java.util.List;

public class RevenueService implements IRenExpService<Revenue>{
    @Override
    public List<Revenue> findAll() {
        return null;
    }

    @Override
    public Revenue findById(int id) {
        return null;
    }

    @Override
    public void save(Revenue revenue) {

    }

    @Override
    public void edit(Revenue revenue, int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Revenue> findByDay(Date date, int id_user) {
        return null;
    }

    @Override
    public List<Revenue> findByWeek(Date date, int id_user) {
        return null;
    }

    @Override
    public List<Revenue> findByMonth(Date date, int id_user) {
        return null;
    }
}
