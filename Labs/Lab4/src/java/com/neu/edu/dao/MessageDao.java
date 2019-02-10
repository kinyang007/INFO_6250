/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.edu.dao;

import com.neu.edu.pojo.Message;
import java.sql.Connection;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author shiva
 */
public class MessageDao {

    public List<Message> getMessages(String user) {
        Connection connection = null;
        List<Message> result = null;
        try {
            Dao dbdao = new Dao();
            connection = dbdao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<List<Message>> h = new BeanListHandler<Message>(Message.class);
            String query = "SELECT * FROM messages where userName=?";
            result = queryRunner.query(connection, query, h, user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;

    }
     public int addMessages(String to,String from,String message) {
        Connection connection = null;
        int result = 0;
        try {
            Dao dbdao = new Dao();
            connection = dbdao.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String query = "INSERT INTO messages (fromUser, userName, message) VALUES (?,?,?)";
            result = queryRunner.update(connection, query,from,to,message);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return result;

    }
}
