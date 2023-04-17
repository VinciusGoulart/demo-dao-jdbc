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


        Department dp = new Department(1,"test");
        Seller seller = new Seller(1,"Vhenus","fadfads@email.com",LocalDate.of(1999,03,10),2000.00,dp);

        DepartmentDao dps = DaoFactory.createDepartmentDao();
        SellerDao sellerDao = DaoFactory.createSellerDao();

        dps.deleteById(1);

        System.out.println(sellerDao.findAll());

        DB.closeConnection();

    }
}