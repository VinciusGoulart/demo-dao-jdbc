package model.dao.impl;

import db.DB;
import db.DBException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller seller) {
        PreparedStatement stm = null;
        try {

            stm = conn.prepareStatement("INSERT INTO seller (Name, Email, Birthday, BaseSalary, DepartmentId, DepName) VALUES (?, ?, ?, ?, ?, ?)");

            stm.setString(1, seller.getName());
            stm.setString(2, seller.getEmail());
            stm.setDate(3, Date.valueOf(seller.getBirthday()));
            stm.setDouble(4, seller.getBaseSalary());
            stm.setInt(5, seller.getDepartment().getId());
            stm.setString(6, seller.getDepartment().getName());

            stm.execute();


        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(stm);
        }


    }

    @Override
    public void update(Seller seller) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement("UPDATE seller SET Name = ?, Email = ?, Birthday = ?, BaseSalary = ?, DepartmentId = ?, DepName = ? WHERE Id = ?");

            stm.setString(1, seller.getName());
            stm.setString(2, seller.getEmail());
            stm.setDate(3, Date.valueOf(seller.getBirthday()));
            stm.setDouble(4, seller.getBaseSalary());
            stm.setInt(5, seller.getDepartment().getId());
            stm.setString(6, seller.getDepartment().getName());
            stm.setInt(7, seller.getId());

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

            stm = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");

            stm.setInt(1, id);

            stm.execute();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(stm);
        }

    }

    @Override
    public Seller selectById(Integer id) {
        PreparedStatement smt = null;
        ResultSet rs = null;
        try {
            smt = conn.prepareStatement("SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE seller.Id = ?");

            smt.setInt(1, id);
            rs = smt.executeQuery();

            if (rs.next()) {
                Department dp = instantiateDepartment(rs);

                Seller sl = instantiateSeller(rs, dp);

                return sl;
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(smt);
            DB.closeResultSet(rs);
        }
        return null;
    }

    public List<Seller> findByDepartmentId(Department department) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Seller> list = new ArrayList<>();
        Map<Integer, Department> map = new HashMap<>();

        try {
            stm = conn.prepareStatement("SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE DepartmentId = ? ORDER BY Name");

            stm.setInt(1, department.getId());
            rs = stm.executeQuery();


            while (rs.next()) {
                Department dp = map.get(rs.getInt("DepartmentId"));
                if (dp == null) {
                    dp = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dp);
                }

                Seller sl = instantiateSeller(rs, dp);

                list.add(sl);
            }
            return list;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(stm);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findAll() {
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement("SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id");
            List<Seller> list = new ArrayList<>();
            rs = stm.executeQuery();

            while (rs.next()) {
                Department dp = instantiateDepartment(rs);
                Seller sl = instantiateSeller(rs, dp);

                list.add(sl);
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
        dp.setName(rs.getString("DepName"));

        return dp;
    }

    private Seller instantiateSeller(ResultSet rs, Department dp) throws SQLException {
        Seller sl = new Seller();
        sl.setId(rs.getInt("Id"));
        sl.setName(rs.getString("Name"));
        sl.setEmail(rs.getString("Email"));
        sl.setBirthday(rs.getDate("Birthday").toLocalDate());
        sl.setBaseSalary(rs.getDouble("BaseSalary"));
        sl.setDepartment(dp);

        return sl;
    }
}
