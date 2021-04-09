package controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Admin;
import entity.Person;
import org.apache.commons.beanutils.BeanUtils;
import service.AdminService;
import service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/register")
public class RegisterPersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
//        String sex = req.getParameter("sex");
//        System.out.println(sex);
        AdminService adminService = new AdminServiceImpl();
        ObjectMapper mapper=new ObjectMapper();
//        Map<String,String[]> map = req.getParameterMap();
        Person person = null;
        String status="-1";
        System.out.println(req.getParameter("dataform"));
        int level = Integer.parseInt(req.getParameter("level"));
        if (level==2){
            person = mapper.readValue(req.getParameter("dataform"),Admin.class);
        }else if(level==3){
            person = mapper.readValue(req.getParameter("dataform"),Person.class);
        }
        System.out.println(person);
        if(adminService.register(person)){
            status = "0";
        }
        resp.setContentType("application/json;charset=utf-8");
        String json=mapper.writeValueAsString(status);
        resp.getWriter().write(json);
    }
}
