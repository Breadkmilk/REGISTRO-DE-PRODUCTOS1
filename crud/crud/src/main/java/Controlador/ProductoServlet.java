/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.ProductoDAO;

@WebServlet("/producto")
public class ProductoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String accion = request.getParameter("accion");
    ProductoDAO dao = new ProductoDAO();

    if (accion == null || accion.equals("listar")) {

        request.setAttribute("lista", dao.listar());
        request.getRequestDispatcher("listar.jsp").forward(request, response);

    } else if (accion.equals("nuevo")) {

        request.getRequestDispatcher("form.jsp").forward(request, response);

    } else if (accion.equals("editar")) {

        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("p", dao.obtener(id));
        request.getRequestDispatcher("form.jsp").forward(request, response);

    } else if (accion.equals("eliminar")) {

        dao.eliminar(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("producto");
    }
}

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductoDAO dao = new ProductoDAO ();
        Producto p = new Producto();
        
        p.setNombre(request.getParameter("nombre"));
        p.setPrecio(Double.parseDouble(request.getParameter("precio")));
        
        String id = request.getParameter("id");
        
        if (id == null || id.isEmpty()){
            dao.agregar(p);
        } else {
            p.setId(Integer.parseInt(id));
            dao.actualizar(p);
        }
        
        response.sendRedirect ("producto"); 
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
