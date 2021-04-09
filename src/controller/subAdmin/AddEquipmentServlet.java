package controller.subAdmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Admin;
import entity.Person;
import service.AdminService;
import service.SubAdminService;
import service.impl.AdminServiceImpl;
import service.impl.SubAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addEquipment")
public class AddEquipmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        SubAdminService subAdminService = new SubAdminServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        String status="-1";
        String ename = req.getParameter("ename");
        if(ename!=null&&subAdminService.addEquipment(ename)){
            status = "0";
        }
        resp.setContentType("application/json;charset=utf-8");
        String json=mapper.writeValueAsString(status);
        resp.getWriter().write(json);
    }
}
