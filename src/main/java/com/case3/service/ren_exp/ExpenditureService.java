package com.case3.service.ren_exp;

import com.case3.config.ConnectionJDBC;
import com.case3.model.Category;
import com.case3.model.Expenditure;
import com.case3.service.category.CategoryExService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenditureService implements IRenExpService<Expenditure>{

    private static  final  String SELECT_ALL_EXPENDITURE = "SELECT*FROM expenditure";

    private static final String SELECT_EXPENDITURE_BY_ID = "SELECT*FROM expenditure WHERE id_ex=?";

    private static final String II_EXPENDITURE = "INSERT INTO expenditure(date_ex,money_ex,note,id_ec) " +
            "VALUE (?,?,?,?)";
    private static final String UPDATE_EXPENDITURE_BY_ID = "UPDATE expenditure " +
            "SET date_ex= ?, money_ex= ?, note= ?, id_ec= ?";

    Connection connection = ConnectionJDBC.getConnection();
    CategoryExService categoryExService = new CategoryExService();

    @Override
    public List<Expenditure> findAll() {
        List<Expenditure> expenditures = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_EXPENDITURE);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                Date date =  rs.getDate(2);
                int money = rs.getInt(3);
                String note = rs.getString(4);
                Category category = categoryExService.findById(rs.getInt(5));
                expenditures.add(new Expenditure (id,category,date,money,note));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return expenditures;
    }

    @Override
    public Expenditure findById(int id) {
        Expenditure expenditure = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_EXPENDITURE_BY_ID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Date date =  rs.getDate(2);
                int money = rs.getInt(3);
                String note = rs.getString(4);
                Category category = categoryExService.findById(rs.getInt(5));
                expenditure = new Expenditure(id,category,date,money,note);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return expenditure;
    }
    @Override
    public void save(Expenditure e) {
        try {
            PreparedStatement statement = connection.prepareStatement(II_EXPENDITURE);
            statement.setDate(1,e.getDate());
            statement.setInt(2,e.getMoney());
            statement.setString(3,e.getNote());
            statement.setInt(4,e.getCategory().getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void edit(Expenditure expenditure, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_EXPENDITURE_BY_ID);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
