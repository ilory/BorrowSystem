package controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Admin;
import entity.Person;
import service.AdminService;
import service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/findAdmin")
public class FindAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = "-1";
        AdminService adminService = new AdminServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        Map<String,Object> map= new HashMap<>();
        Admin admin = mapper.readValue(req.getParameter("searchParams"),Admin.class);
        System.out.println(admin);
        admin.setLevel(2);
        List<Person> allSelectPerson = adminService.getSelectPerson(admin);
        req.setCharacterEncoding("utf-8");
        if(allSelectPerson!=null){
            status="0";
            map.put("data",allSelectPerson);
            map.put("msg","");
        }
        map.put("code","0");
        map.put("count","10");
        map.put("status",status);
        resp.setContentType("application/json;charset=utf-8");
        String json = mapper.writeValueAsString(map);
        resp.getWriter().write(json);
    }
}
