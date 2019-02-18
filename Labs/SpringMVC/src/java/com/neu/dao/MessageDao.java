/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.dao;

import com.neu.pojo.Message;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author shiva
 */
public class MessageDao extends Dao {

    public List<Message> getMessages(String user) {
        Connection connection = null;
        List<Message> result = null;
        try {
            connection = super.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Message>> h = new BeanListHandler<>(Message.class);
            String query = "Select * from messages where userName=?";
            result = queryRunner.query(connection, query, h, user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
        return result;
    }

    public int addMessages(String to, String from, String message) {
        Connection connection = null;
        int result = 0;
        try {
            connection = super.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query = "Insert into messages (fromUser, userName, message) values (?,?,?)";
            result = queryRunner.update(connection, query, from, to, message);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}
