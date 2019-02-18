/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author Adi
 */
public class Dao {

    private Connection connection = null;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://newton.neu.edu:3306/usersdb";
    static final String USER = "student";
    static final String PASSWORD = "p@ssw0rd";

    public Connection getConnection() throws Exception {
        try {
            if (connection == null) {
                DbUtils.loadDriver(JDBC_DRIVER);
                this.connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception");
            ex.printStackTrace();
            throw new Exception();
        }

        return this.connection;
    }

    public void closeConnection() {
        if (this.connection != null) {
            try {
                DbUtils.close(this.connection);
            } catch (SQLException ex) {
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }
        }
    }

}
