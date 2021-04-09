package controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Admin;
import entity.Person;
import org.apache.commons.beanutils.BeanUtils;
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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/applyForEquipment")
public class ApplyForEquipmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int eid = Integer.valueOf(req.getParameter("eid"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try{
             date = format.parse(req.getParameter("rtime"));
        }catch (Exception e){
            e.printStackTrace();
        }
        UserService userService = new UserServiceImpl();
        String status="-1";
        if(userService.applyForEquipment(eid,30002,date)){
            status = "0";
        }
        resp.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(status);
        resp.getWriter().write(json);
    }
}
