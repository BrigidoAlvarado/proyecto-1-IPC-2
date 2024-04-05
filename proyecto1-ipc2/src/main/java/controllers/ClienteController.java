/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import entities.Cliente;
import services.ClienteService;
import until.ApiException;

/**
 *
 * @author Personal
 */

@WebServlet(name = "ClienteController", urlPatterns = "/Clientes/*")
public class ClienteController extends GeneralController {

    private ClienteService clienteService = new ClienteService(); 

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                try {
                    if (request.getPathInfo() != null) {
                        
                        String pathParam = request.getPathInfo().replace("/", "");
                        this.sendResponse(response, clienteService.getClienteByNit(Integer.parseInt(pathParam)));
                    
                    }

                } catch (IOException | NumberFormatException | ApiException e) {
                   
                    this.sendError(response, ApiException.builder()
                    .code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build());
                }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                try {
                    
                    Gson gson = new Gson();
                    Cliente cliente = gson.fromJson(request.getReader(), Cliente.class);
                    this.sendResponse(response, clienteService.inssertCliente(cliente));
                    
                } catch (ApiException e) {
                    this.sendError(response, ApiException.builder()
                    .code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build());
                }
    }

    


}
