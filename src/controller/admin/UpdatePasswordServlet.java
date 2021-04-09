package controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.AdminService;
import service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status="-1";
        int uid = Integer.valueOf(req.getParameter("uid"));
        String newPassword = req.getParameter("newPassword");
        AdminService adminService = new AdminServiceImpl();
        if (adminService.editUserPassword(uid,newPassword)){
            System.out.println("修改");
            status="0";
        }
        //resp.setCharacterEncoding("application/json;charset=utf-8");
        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(status);
        resp.getWriter().write(json);
    }
}
