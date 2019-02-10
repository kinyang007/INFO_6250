/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author Adi
 */
public class Dao {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://newton.neu.edu:3306/usersdb";
    static final String USER = "student";
    static final String PASSWORD = "p@ssw0rd";
    private Connection connection;

    public Connection getConnection() throws Exception {
        try {
            Connection connection = null;
            DbUtils.loadDriver(JDBC_DRIVER);
            this.connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException ex) {
//            endConnection();
            System.out.println("SQL Exception");
            ex.printStackTrace();
            throw new Exception();
        }
//            endConnection();
        return this.connection;
    }

   
}
