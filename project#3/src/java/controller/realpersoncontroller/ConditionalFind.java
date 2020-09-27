package controller.realpersoncontroller;

import commons.ExceptionWrapper;
import model.entity.RealPerson;
import model.service.RealPersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/realPerson/conditionalFind")
public class ConditionalFind extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RealPerson realPerson = new RealPerson();
            String name=req.getParameter("name");
            String family=req.getParameter("family");
            String fatherName =req.getParameter("fatherName");
            if (name==null && family==null && fatherName==null) {
                System.out.println(req.getHeader("referer"));
                resp.sendRedirect(req.getHeader("referer"));
                return;
            }
            if(name.trim().equals("") && family.trim().equals("") && fatherName.trim().equals("")){
                resp.sendRedirect(req.getHeader("referer"));
                return;
            }
            realPerson.setName(name);
            realPerson.setFamily(family);
            realPerson.setFatherName(fatherName);
            List<RealPerson> realPersonList=RealPersonService.getInstance().conditionalFind(realPerson);
            req.setAttribute("realPersons",realPersonList);
            req.getRequestDispatcher("/realPerson/index.jsp").forward(req,resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", ExceptionWrapper.getMessage(e));
            req.getRequestDispatcher("/realPerson/index.jsp").forward(req,resp);
        }
    }
}
