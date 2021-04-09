package controller.subAdmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Equipment;
import entity.Person;
import service.SubAdminService;
import service.impl.SubAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteEquipment")
public class DeleteEquipmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        ObjectMapper mapper=new ObjectMapper();
        SubAdminService subAdminService = new SubAdminServiceImpl();
        List<Equipment> list = mapper.readValue(req.getParameter("dataform"),List.class);
        String status = "-1";
        for(int i=0;i<list.size();i++){
            System.out.println(mapper.writeValueAsString(list.get(i)));
            Equipment equipment = mapper.readValue(mapper.writeValueAsString(list.get(i)),Equipment.class);
            System.out.println(equipment.getEid());
            if(!subAdminService.delEquipment(equipment.getEid())){
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
