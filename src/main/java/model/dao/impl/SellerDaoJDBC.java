package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.SellerDao;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {
    @Override
    public void insert(Seller seller) {
        try {
            Connection conn = DB.getConnection();

            PreparedStatement stm = conn.prepareStatement("INSERT INTO seller (name, email, birthday, basesalary, department) VALUES (?, ?, ?, ?, ?) ");

            stm.setString(1, seller.getName());
            stm.setString(2, seller.getEmail());
            stm.setDate(3, Date.valueOf(seller.getBirthday()));
            stm.setDouble(4,seller.getBaseSalary());
            stm.setInt(5,seller.getDepartment().getId());

            stm.execute();


        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }


    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller selectById(Integer id) {
        return null;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
