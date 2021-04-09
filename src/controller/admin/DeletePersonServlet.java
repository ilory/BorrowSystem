package controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Person;
import entity.Person_Equipment;
import service.AdminService;
import service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/deletePerson")
public class DeletePersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        ObjectMapper mapper=new ObjectMapper();
        AdminService adminService = new AdminServiceImpl();
        List<Person> list = mapper.readValue(req.getParameter("dataform"),List.class);
        String status = "-1";
        for(int i=0;i<list.size();i++){
            System.out.println(mapper.writeValueAsString(list.get(i)));
            Person person = mapper.readValue(mapper.writeValueAsString(list.get(i)),Person.class);
            if(!adminService.delUser(person.getUid())){
                status="-1";
                break;
            }
            status="0";
        }
        resp.setContentType("application/json;charset=utf-8");
        String json=mapper.writeValueAsString(status);
        resp.getWriter().write(json);
    }
}
