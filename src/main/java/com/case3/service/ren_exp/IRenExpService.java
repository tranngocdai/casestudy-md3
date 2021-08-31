package com.case3.service.ren_exp;

import com.case3.service.IService;

import java.util.Date;
import java.util.List;

public interface IRenExpService<E> extends IService<E> {
    List<E> findByDay (Date date,int id_user);
    List<E> findByWeek (Date date,int id_user);
    List<E> findByMonth (Date date,int id_user);



}
