/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author RAHUL
 */
import entities.Category;
import entities.Post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PostDao 
{
    Connection con;

    public PostDao(Connection con)
    {
        this.con = con;
    }
    
    public ArrayList<Category> getCategories()
    {
        ArrayList<Category> list=new ArrayList();
        try
        {
            String query="select * from categories";
            Statement st=this.con.createStatement();
            ResultSet set=st.executeQuery(query);
            while(set.next())
            {
                int cid=set.getInt("cid");
                String name=set.getString("name");
                String description=set.getString("description");
                Category c=new Category(cid,name,description);
                list.add(c);
                System.out.print(c);
            }
            return list;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
    public boolean savePost(Post p)
    {
        boolean f=false;
        try
        {
            String q="insert into posts(ptitle,pcontent,pcode,ppic,cid,userid) values(?,?,?,?,?,?)";
            PreparedStatement pstmt=con.prepareStatement(q);
            pstmt.setString(1,p.getPtitle());
            pstmt.setString(2,p.getPcontent());
            pstmt.setString(3,p.getPcode());
            pstmt.setString(4,p.getPpic());
            pstmt.setInt(5,p.getCid());
            pstmt.setInt(6,p.getUserid());
            pstmt.executeUpdate();
            f=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }
    
    public List<Post> getAllPosts()
    {
        List<Post>list=new ArrayList<>();
        try
        {
            PreparedStatement p=con.prepareStatement("select * from posts order by pid desc");
            ResultSet set=p.executeQuery();
            while(set.next())
            {
                int pid=set.getInt("pid");
                String ptitle=set.getString("ptitle");
                String pcontent=set.getString("pcontent");
                String pcode=set.getString("pcode");
                String ppic=set.getString("ppic");
                Timestamp pdate=set.getTimestamp("pdate");
                int cid=set.getInt("cid");
                int userid=set.getInt("userid");
                Post ppost=new Post(pid,ptitle,pcontent,pcode,ppic,pdate,cid,userid);
                list.add(ppost);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
        
        return list;
    }
    public List<Post> getPostByCatId(int catId)
    {
        List<Post>list=new ArrayList<>();
        try
        {
            PreparedStatement p=con.prepareStatement("select * from posts where cid=?");
            p.setInt(1, catId);
            ResultSet set=p.executeQuery();
            while(set.next())
            {
                int pid=set.getInt("pid");
                String ptitle=set.getString("ptitle");
                String pcontent=set.getString("pcontent");
                String pcode=set.getString("pcode");
                String ppic=set.getString("ppic");
                Timestamp pdate=set.getTimestamp("pdate");
                int userid=set.getInt("userid");
                Post ppost=new Post(pid,ptitle,pcontent,pcode,ppic,pdate,catId,userid);
                list.add(ppost);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        
        
        
        return list;
        
    }
    
}
