/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author RAHUL
 */
public class UserDao {
    
    private Connection con;
    boolean f=false;
    public UserDao(Connection con) {
        this.con = con;
    }
    public boolean saveUser(User user)
    {
        try
        {
            String query="insert into user(name,email,password,gender,about) values(?,?,?,?,?)";
            PreparedStatement pstmt=this.con.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getGender());
            pstmt.setString(5, user.getAbout());
            pstmt.executeUpdate();
            f=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }
    public User getUserByUserEmailAndPassword(String email,String password)
    {
        User user=null;
        try
        {
            String query="select * from user where email=? and password=?";
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1,email);
            pstmt.setString(2,password);
            ResultSet set=pstmt.executeQuery();
            if(set.next())
            {
                user=new User();
                String name=set.getString("name");
                user.setName(name);
                user.setId(set.getInt("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setAbout(set.getString("about"));
                user.setGender(set.getString("gender"));
                user.setDatetime(set.getTimestamp("reg_date"));
                user.setProfile(set.getString("profile"));
                System.out.print(user.getProfile());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }
    public boolean updateUser(User user)
    {
        boolean f=false;
        try
        {
            String query="update user set name=?,email=?,password=?,about=?,profile=? where id=?";
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1,user.getName());
            pstmt.setString(2,user.getEmail());
            pstmt.setString(3,user.getPassword());
            pstmt.setString(4,user.getAbout());
            pstmt.setString(5,user.getProfile());
            pstmt.setInt(6,user.getId());
            pstmt.executeUpdate();
            f=true;
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }
    
}
