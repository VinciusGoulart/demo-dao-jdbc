package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;


    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement stm = null;
        try {

            stm = conn.prepareStatement("INSERT INTO department(Name) VALUES(?)");

            stm.setString(1, department.getName());

            stm.execute();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(stm);
        }

    }

    @Override
    public void update(Department department) {
        PreparedStatement stm = null;
        try {

            stm = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");

            stm.setString(1, department.getName());
            stm.setInt(2, department.getId());


            stm.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(stm);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement("UPDATE seller SET DepartmentId = null WHERE DepartmentId = ?");
            stm.setInt(1, id);
            stm.executeUpdate();

            stm = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
            stm.setInt(1, id);
            stm.execute();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(stm);
        }
    }

    @Override
    public Department selectById(Integer id) {
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {

            stm = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");

            stm.setInt(1, id);
            rs = stm.executeQuery();

            if (rs.next()) {
                Department dp = instantiateDepartment(rs);
                return dp;
            }
            return null;

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(stm);
        }

    }

    @Override
    public List<Department> findAll() {
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement("SELECT * FROM department");
            List<Department> list = new ArrayList<>();
            rs = stm.executeQuery();

            while (rs.next()) {
                Department dp = instantiateDepartment(rs);
                list.add(dp);

            }
            return list;

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(stm);
        }

    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dp = new Department();
        dp.setId(rs.getInt("DepartmentId"));
        dp.setName(rs.getString("Name"));

        return dp;
    }
}
