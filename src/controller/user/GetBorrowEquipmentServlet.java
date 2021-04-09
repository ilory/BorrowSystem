package controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Equipment;
import entity.Person;
import service.AdminService;
import service.UserService;
import service.impl.AdminServiceImpl;
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

@WebServlet("/allBorrowEquipment")
public class GetBorrowEquipmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = "-1";
        UserService userService = new UserServiceImpl();
        List<Equipment> allEquipment = userService.getAllEquipment();
        req.setCharacterEncoding("utf-8");
        ObjectMapper mapper=new ObjectMapper();
        Map<String,Object> map= new HashMap<>();
        if(allEquipment!=null){
            status="0";
            map.put("data",allEquipment);
            map.put("code",0);
            map.put("msg","");
            map.put("count",1000);
        }
        map.put("status",status);
        resp.setContentType("application/json;charset=utf-8");
        String json = mapper.writeValueAsString(map);
        resp.getWriter().write(json);
    }
}
