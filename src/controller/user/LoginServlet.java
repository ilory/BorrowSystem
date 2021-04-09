package controller.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        ObjectMapper mapper=new ObjectMapper();
        System.out.println(req.getParameter("dataform"));
        ArrayList<String> list = mapper.readValue(req.getParameter("dataform"), new TypeReference<ArrayList<String>>() {
        });
        String userId = list.get(0);
        String password = list.get(1);
        int level = Integer.valueOf(list.get(2));
        LoginServiceImpl loginService = new LoginServiceImpl();
        String status="-1";
        Map<String,Object> map= new HashMap<>();
        if(loginService.login(userId,password,level)){
            HttpSession session = req.getSession();
            String userName = loginService.getUserName(userId);
            session.setAttribute("userName",userName);
            status = "1";
            map.put("userName",userName);
            map.put("level",level);
        }
        map.put("status",status);
        resp.setContentType("application/json;charset=utf-8");
        String json=mapper.writeValueAsString(map);
        resp.getWriter().write(json);
    }
}
