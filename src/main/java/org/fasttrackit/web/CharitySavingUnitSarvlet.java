package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.service.CharitySavingUnitService;
import org.fasttrackit.transfer.SaveCharitySavingRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/charity_saving_units")
public class CharitySavingUnitSarvlet extends HttpServlet {
    private CharitySavingUnitService charitySavingUnitService = new CharitySavingUnitService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        SaveCharitySavingRequest request = objectMapper.readValue(req.getReader(), SaveCharitySavingRequest.class);
        try {
            charitySavingUnitService.createCharitySavingUnit(request);
        }
        catch (SQLException e){
            e.printStackTrace();
            resp.sendError(500, "There is an error! " + e.getMessage());  // 500 is Http service codes
        }
    }
}
