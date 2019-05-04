package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.domain.CharityItem;
import org.fasttrackit.service.CharityItemService;
import org.fasttrackit.transfer.MarkItemDoneRequest;
import org.fasttrackit.transfer.SaveCharityRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/charity-budget-items")
public class CharityItemSarvlet extends HttpServlet {
    private CharityItemService charityItemService = new CharityItemService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeader(resp);

        ObjectMapper objectMapper = new ObjectMapper();
        SaveCharityRequest request = objectMapper.readValue(req.getReader(), SaveCharityRequest.class);
        try {
            charityItemService.createCharityItem(request);
        }
        catch (SQLException e){
            e.printStackTrace();
            resp.sendError(500, "There is an error! " + e.getMessage());  // 500 is Http service codes
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeader(resp);

        try {
            List<CharityItem> charityItems = charityItemService.getCharityItem();
            ObjectMapper objectMapper = new ObjectMapper();
            String JasonResponse = objectMapper.writeValueAsString(charityItems);

            resp.getWriter().print(JasonResponse);
            resp.getWriter().flush();  // to clean after we finish
            resp.getWriter().close();
        }

        catch (SQLException e){
            e.printStackTrace();
            resp.sendError(500, "There is an error: " + e.getMessage());
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeader(resp);

        String id = req.getParameter("id");
        ObjectMapper objectMapper = new ObjectMapper();
        MarkItemDoneRequest request = objectMapper.readValue(req.getReader(), MarkItemDoneRequest.class);

        try {
            CharityItemService.markCharityItemDone(Long.valueOf(id), request);
        }
        catch (SQLException e){
            e.printStackTrace();
            resp.sendError(500, "there is an Error" + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeader(resp);

        String id = req.getParameter("id");

        try {
            charityItemService.deleteCharityItem(Long.valueOf(id));
        }

        catch (SQLException e){
            e.printStackTrace();
            resp.sendError(500, "There is an Error: " + e.getMessage());
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeader(resp);

        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeader(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
