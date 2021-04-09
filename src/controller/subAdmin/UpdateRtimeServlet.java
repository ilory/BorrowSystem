package controller.subAdmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Person_Equipment;
import service.SubAdminService;
import service.impl.SubAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateRtime")
public class UpdateRtimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String status="-1";
        SubAdminService subAdminService = new SubAdminServiceImpl();
        System.out.println(req.getParameter("dataform"));
        ObjectMapper mapper=new ObjectMapper();
        Person_Equipment personEquipment = mapper.readValue(req.getParameter("dataform"),Person_Equipment.class);
        System.out.println(personEquipment);
        if(subAdminService.equipmentRepay(personEquipment)){
            status="0";
        }
        resp.setContentType("application/json;charset=utf-8");
        String json=mapper.writeValueAsString(status);
        resp.getWriter().write(json);
    }
}
