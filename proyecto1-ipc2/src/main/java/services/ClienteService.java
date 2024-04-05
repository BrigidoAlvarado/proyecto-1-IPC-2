package services;

import javax.servlet.http.HttpServletResponse;

import DAO.ClienteDao;
import entities.Cliente;
import until.ApiException;

public class ClienteService {

    private ClienteDao clienteDao = new ClienteDao();

    public Cliente getClienteByNit (Integer nit) throws ApiException {
       
        //clienteDao = new ClienteDao();

        if (nit == null) {
            System.out.println(nit);

            throw ApiException.builder()
            .code(HttpServletResponse.SC_BAD_REQUEST)
            .message("Es necesario ingresar el NIT")
            .build();
        }

        Cliente cliente = clienteDao.getByNit(nit);

        if (cliente == null) {
            throw ApiException.builder()
            .code(HttpServletResponse.SC_NOT_FOUND)
            .message("Clinete no encontrado")
            .build();
        }
        return cliente;

    }

    public Cliente inssertCliente ( Cliente cliente) throws ApiException {

        if (cliente.getNombre() == null) {
            
            throw ApiException.builder()
            .code(HttpServletResponse.SC_BAD_REQUEST)
            .message("Se requeiere ingresar el nombre del cliente")
            .build();
        }
        return clienteDao.insert(cliente);
    }
}


