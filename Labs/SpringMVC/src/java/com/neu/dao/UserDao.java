/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.dao;

import com.neu.pojo.Login;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author shiva
 */
public class UserDao extends Dao {

    public Login authenticateLogin(String user, String password) {
        Connection connection = null;
        try {
            connection = super.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query = "SELECT * FROM userstable where UserName=? AND UserPassword=?";
            ResultSetHandler<Login> h = new BeanHandler<>(Login.class);
            Login l = queryRunner.query(connection, query, h, user, password);
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    public List<Login> getUsers(String searchString) {
        Connection connection = null;
        List<Login> result = null;
        try {
            connection = super.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Login>> h = new BeanListHandler<>(Login.class);
            String query = "SELECT * FROM userstable where UserName like '" + searchString + "%'";
            result = queryRunner.query(connection, query, h);
            System.out.println("the result of searching for a user" + result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
        return result;
    }

    public int addUser(String userName, String password) {
        Connection connection = null;
        int result = 0;
        try {
            connection = super.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query = "INSERT INTO userstable (UserName, UserPassword) VALUES( ?,?)";
            result = queryRunner.update(connection, query, userName, password);
            //System.out.println("result of insert"+result);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeConnection();
        }
        return result;

    }
    
}
