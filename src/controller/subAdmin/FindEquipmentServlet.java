package controller.subAdmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Equipment;
import service.SubAdminService;
import service.UserService;
import service.impl.SubAdminServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/findEquipment")
public class FindEquipmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = "-1";
        SubAdminService subAdminService = new SubAdminServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
        Map<String,Object> map= new HashMap<>();
        Equipment equipment = mapper.readValue(req.getParameter("searchParams"),Equipment.class);
        System.out.println(equipment);
        List<Equipment> allSelectEquipment = subAdminService.getSelectEquipment(equipment);
        req.setCharacterEncoding("utf-8");
        if(allSelectEquipment!=null){
            status="0";
            map.put("data",allSelectEquipment);
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
