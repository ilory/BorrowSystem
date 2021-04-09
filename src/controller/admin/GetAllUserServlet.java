package controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebServlet("/allUser")
public class GetAllUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = "-1";
        AdminService adminService = new AdminServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        List<Person> allUser = adminService.getAllUser(3);
        req.setCharacterEncoding("utf-8");
        Map<String,Object> map= new HashMap<>();
        if(allUser!=null){
            status="0";
            map.put("data",allUser);
            map.put("code","0");
            map.put("msg","0");
            map.put("count","10");
        }
        map.put("status",status);
        resp.setContentType("application/json;charset=utf-8");
        String json = mapper.writeValueAsString(map);
        resp.getWriter().write(json);
    }
}
