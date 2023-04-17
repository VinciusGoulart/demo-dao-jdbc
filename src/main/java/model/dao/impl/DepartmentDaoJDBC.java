package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentDaoJDBC implements DepartmentDao {
    @Override
    public void insert(Department department) {

        try {
            Connection conn = DB.getConnection();

            PreparedStatement stm = conn.prepareStatement("INSERT INTO department(name) VALUES(?)");

            stm.setString(1, department.getName());

            stm.execute();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

    }

    @Override
    public void update(Department department) {
        try {
            Connection conn = DB.getConnection();

            PreparedStatement stm = conn.prepareStatement("UPDATE department SET name = ? WHERE id = ?");

            stm.setString(1, department.getName());
            stm.setInt(2, department.getId());


            stm.execute();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

    }

    @Override
    public void deleteById(Integer id) {
        try {
            Connection conn = DB.getConnection();

            PreparedStatement stm = conn.prepareStatement("DELETE FROM department WHERE id = ?");

            stm.setInt(1, id);

            stm.execute();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Department selectById(Integer id) {
        try {
            Connection conn = DB.getConnection();

            PreparedStatement stm = conn.prepareStatement("SELECT * FROM department WHERE id = ?");

            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                int x = rs.getInt("id");
                String name = rs.getString("name");
                Department dp = new Department(x, name);
                return dp;
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            Connection conn = DB.getConnection();

            stm = conn.prepareStatement("SELECT * FROM department");


            rs = stm.executeQuery();

            while (rs.next()) {
                int x = rs.getInt("id");
                String name = rs.getString("name");

                Department dp = new Department(x, name);

                List<Department> list = new ArrayList<>();
                list.add(dp);

                return list;
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(stm);
        }
        return null;
    }
}
