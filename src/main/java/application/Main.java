package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Connection connection = DB.getConnection();


        Department dp = new Department(2,"test");
        Seller seller = new Seller(4,"Vhenus","fadfads@email.com",LocalDate.of(2015,12,20),2000.00,dp);

        DepartmentDao dps = DaoFactory.createDepartmentDao();
        SellerDao sellerDao = DaoFactory.createSellerDao();

        sellerDao.update(seller);

        System.out.println(sellerDao.findByDepartmentId(dp));

        DB.closeConnection();

    }
}