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
@WebServlet("/realPerson/findByNationalCode.do")
public class SearchByNationalCode extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nationalCode = req.getParameter("nationalCode");
            List<RealPerson> realPersonList= RealPersonService.getInstance().findByNationalCode(nationalCode);
            req.setAttribute("realPersons",realPersonList);
            req.getRequestDispatcher("/realPerson/index.jsp").forward(req,resp);
        }catch (Exception e){
            req.setAttribute("errorMessage", ExceptionWrapper.getMessage(e));
            resp.sendError(700);
        }
    }
}
