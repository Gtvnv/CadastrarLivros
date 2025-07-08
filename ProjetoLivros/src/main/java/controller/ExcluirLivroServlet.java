package controller;

import model.LivroDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


@WebServlet("/excluir")
public class ExcluirLivroServlet extends HttpServlet {
    private static final long serialVersionUID = -1555182091229648935L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        LivroDAO.removerPorIsbn(isbn);
        response.sendRedirect("listagem.jsp");
    }
}
