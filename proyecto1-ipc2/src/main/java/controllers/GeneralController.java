package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import until.ApiException;

public class GeneralController extends HttpServlet {

    protected void sendResponse(HttpServletResponse response, Object object) throws IOException {
       
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(object));
    }

    protected void sendError (HttpServletResponse response, ApiException e) throws IOException  {
        response.setContentType("application/json");
        response.sendError(e.getCode(), e.getMessage());
    }
}
