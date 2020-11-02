/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import dao.UserDao;
import entities.Message;
import entities.User;
import helper.ConnectionProvider;
import helper.Helper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author RAHUL
 */
@MultipartConfig
public class EditServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            String useremail=request.getParameter("user_email");
            String username=request.getParameter("user_name");
            String userpassword=request.getParameter("user_password");
            String userabout=request.getParameter("about");
            Part part=request.getPart("image");
            String imagename=part.getSubmittedFileName();
            out.println("image name we are getting is "+imagename);
            out.println("user name we are getting is "+username);
            out.println("password we are getting is "+userpassword);
            out.println(" about we are getting is "+userabout);
            out.println("email we are getting is "+useremail);
                        
    
              //getSession
            HttpSession s=request.getSession();
            User user=(User)s.getAttribute("currentuser");
            String lastimage=user.getProfile();
            
            if(imagename=="")
            {
                imagename=lastimage;
            }
            user.setEmail(useremail);
            user.setName(username);
            user.setAbout(userabout);
            user.setProfile(imagename);
            user.setPassword(userpassword);
            UserDao dao=new UserDao(ConnectionProvider.getConnection());
            boolean ans=dao.updateUser(user);
            if(ans)
            {
                    if(!lastimage.equals("profile.png"))
                    {  
                     String path1=request.getRealPath("/")+"pics"+File.separator+lastimage;
                     Helper.deleteFile(path1);
                    }
                    
                    String path=request.getRealPath("/")+"pics"+File.separator+imagename;
                    if(Helper.saveFile(part.getInputStream(),path))
                    {
                         Message msg=new Message("profile details updated","success","alert-success");
                         s.setAttribute("msg",msg);         
                    }
                
            }
            else
            {
                 Message msg=new Message("something is wrong","error","alert-danger");
                         s.setAttribute("msg",msg); 
            }
            response.sendRedirect("profile.jsp");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
