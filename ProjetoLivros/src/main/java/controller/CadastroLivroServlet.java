package controller;

import model.Livro;
import model.LivroDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


@WebServlet("/cadastro")
public class CadastroLivroServlet extends HttpServlet {
    private static final long serialVersionUID = -1089012228843952354L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String anoStr = request.getParameter("ano");
        String isbn = request.getParameter("isbn");

        if (titulo.isEmpty() || autor.isEmpty() || anoStr.isEmpty() || isbn.isEmpty()) {
            request.setAttribute("erro", "Todos os campos são obrigatórios.");
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
            return;
        }

        try {
            int ano = Integer.parseInt(anoStr);
            Livro livro = new Livro(titulo, autor, ano, isbn);
            LivroDAO.adicionar(livro);
            response.sendRedirect("listagem.jsp");
        } catch (NumberFormatException e) {
            request.setAttribute("erro", "Ano deve ser numérico.");
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
        }
    }
}
