package com.case3.service.ren_exp;

import com.case3.config.ConnectionJDBC;
import com.case3.model.Category;
import com.case3.model.Expenditure;
import com.case3.service.category.CategoryExService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenditureService implements IRenExpService<Expenditure>{



    private static  final  String SELECT_ALL_EXPENDITURE = "SELECT*FROM expenditure";

    private static final String SELECT_EXPENDITURE_BY_ID = "SELECT*FROM expenditure WHERE id_ex=?";

    private static final String II_EXPENDITURE = "INSERT INTO expenditure(date_ex,money_ex,note,id_ec) " +
            "VALUE (?,?,?,?)";

    private static final String UPDATE_EXPENDITURE_BY_ID = "UPDATE expenditure " +
            "SET date_ex= ?, money_ex= ?, note= ?, id_ec= ?, WHERE id_ex=?";

    private static final String DELETE_EXPENDITURE_BY_ID = "DELETE FROM expenditure WHERE id_ex=?";

    private static final String SELECT_EXPENDITURE_BY_DAY_AND_USER_ID =
            "SELECT e.id_ex\n" +
            "FROM expenditure e JOIN expenditure_Categories eC on eC.id_ec = e.id_ec\n" +
            "WHERE eC.id_user=?  AND e.date_ex=?";

    private static final String SELECT_EXPENDITURE_BY_USER_ID_AND_WEEK_OF_DATE =
            "SELECT e.id_ex\n" +
            "FROM expenditure e JOIN expenditure_Categories eC on eC.id_ec = e.id_ec\n" +
            "WHERE eC.id_user=? AND weekofyear(e.date_ex) =  weekofyear(?)" ;

    private static final String SELECT_EXPENDITURE_BY_USER_ID_AND_MONTH_OF_DATE =
            "SELECT e.id_ex\n" +
            "FROM expenditure e JOIN expenditure_Categories eC on eC.id_ec = e.id_ec\n" +
            "WHERE eC.id_user=? AND month(e.date_ex) =  month(?)" ;

    private static final String SELECT_EXPENDITURE_BY_MONEY_AND_USER_ID =
            "SELECT e.id_ex\n" +
            "FROM expenditure e JOIN expenditure_Categories eC ON eC.id_ec = e.id_ec\n" +
            "WHERE eC.id_user = ? AND (e.money_ex BETWEEN ? AND ?)";


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
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            statement.setString(1, dateFormat.format(e.getDate()));
            statement.setInt(2,e.getMoney());
            statement.setString(3,e.getNote());
            statement.setInt(4,e.getCategory().getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void edit(Expenditure e, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_EXPENDITURE_BY_ID);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            statement.setString(1, dateFormat.format(e.getDate()));
            statement.setInt(2,e.getMoney());
            statement.setString(3,e.getNote());
            statement.setInt(4,e.getCategory().getId());
            statement.setInt(5,id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_EXPENDITURE_BY_ID);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Expenditure> findByDay(Date date, int id_user) {
        List<Expenditure> expenditures = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_EXPENDITURE_BY_DAY_AND_USER_ID);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            statement.setInt(1,id_user);
            statement.setString(2,dateFormat.format(date));
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                expenditures.add(findById(id));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return expenditures;
    }

    @Override
    public List<Expenditure> findByWeek(Date date, int id_user) {
        List<Expenditure> expenditures = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_EXPENDITURE_BY_USER_ID_AND_WEEK_OF_DATE);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            statement.setInt(1,id_user);
            statement.setString(2,dateFormat.format(date));
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                expenditures.add(findById(id));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return expenditures;
    }

    @Override
    public List<Expenditure> findByMonth(Date date, int id_user) {
        List<Expenditure> expenditures = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_EXPENDITURE_BY_USER_ID_AND_MONTH_OF_DATE);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            statement.setInt(1,id_user);
            statement.setString(2,dateFormat.format(date));
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                expenditures.add(findById(id));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return expenditures;
    }

    @Override
    public List<Expenditure> findByMoney(int minMoney,int maxMoney, int id_user) {
        List<Expenditure> expenditures = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_EXPENDITURE_BY_MONEY_AND_USER_ID);
            statement.setInt(1,id_user);
            statement.setInt(2,minMoney);
            statement.setInt(3,maxMoney);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                expenditures.add(findById(id));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return expenditures;
    }
}
