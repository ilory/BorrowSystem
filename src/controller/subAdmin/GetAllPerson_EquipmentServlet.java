package controller.subAdmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Person;
import entity.Person_Equipment;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/allApplicationForm")
public class GetAllPerson_EquipmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = "-1";
        SubAdminService subAdminService = new SubAdminServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        Person_Equipment personEquipment = new Person_Equipment(null,null,null,null,null,null,null);
        List<Person_Equipment> allApprove = subAdminService.getPersonEquipmentList(personEquipment);
        req.setCharacterEncoding("utf-8");
        Map<String,Object> map= new HashMap<>();
        if(allApprove!=null){
            status="0";
            map.put("data",allApprove);
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
