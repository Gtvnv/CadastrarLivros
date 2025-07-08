package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Livro;

@WebServlet(urlPatterns = {"/livro/listar", "/livro/cadastrar"})
public class LivroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final List<Livro> livros = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("livros", livros);
        request.getRequestDispatcher("/livro-lista.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String ano = request.getParameter("anoPublicacao");
        String isbn = request.getParameter("isbn");

        if (titulo == null || autor == null || ano == null || isbn == null ||
            titulo.isEmpty() || autor.isEmpty() || ano.isEmpty() || isbn.isEmpty()) {
            response.getWriter().println("Erro: Todos os campos devem ser preenchidos.");
            return;
        }

        try {
            int anoPub = Integer.parseInt(ano);
            Livro novoLivro = new Livro(titulo, autor, anoPub, isbn);
            livros.add(novoLivro);
            response.sendRedirect("listar"); // redireciona para a lista
        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: Ano de publicação inválido.");
        }
    }
}

