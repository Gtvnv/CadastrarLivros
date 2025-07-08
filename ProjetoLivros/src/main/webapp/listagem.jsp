<%@ page import="java.util.List" %>
<%@ page import="model.Livro" %>
<%@ page import="model.LivroDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Livros</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #e0f2f7;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            align-items: center;
            min-height: 100vh;
            position: relative;
        }

        .header-image {
            position: absolute;
            top: 20px;
            right: 20px;
            width: 100px;
            height: 100px;
            border-radius: 50%;
            object-fit: cover;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .container {
            max-width: 900px;
            width: 90%;
            margin: 20px auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            box-sizing: border-box;
            border: 1px solid #b3e0ff;
            flex-grow: 1; 
        }

        h1 {
            text-align: center;
            color: #0d47a1; 
            margin-bottom: 25px;
            font-size: 2em;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fcfdff;
            border-radius: 8px; 
            overflow: hidden; 
        }

        table, th, td {
            border: 1px solid #e3f2fd;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #90caf9;
            color: #1a237e;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #e0f2f7;
        }

        tr:hover {
            background-color: #bbdefb;
        }

        input[type="submit"] {
            padding: 8px 12px;
            font-size: 14px;
            border: none;
            border-radius: 6px;
            background-color: #ef5350;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        input[type="submit"]:hover {
            background-color: #d32f2f;
            transform: translateY(-1px);
        }

        .navigation-buttons {
            margin-top: 25px;
            display: flex;
            justify-content: center;
            gap: 15px;
        }

        .navigation-buttons a {
            display: inline-block;
            padding: 12px 18px;
            border-radius: 8px;
            text-decoration: none;
            color: white;
            font-weight: bold;
            transition: background-color 0.3s ease, transform 0.2s;
            flex-grow: 1;
            max-width: 220px;
            text-align: center;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .btn-primary {
            background-color: #2196f3;
        }

        .btn-primary:hover {
            background-color: #1976d2;
            transform: translateY(-2px);
        }

        .btn-secondary {
            background-color: #607d8b;
        }

        .btn-secondary:hover {
            background-color: #455a64;
            transform: translateY(-2px);
        }

        .footer {
            margin-top: 40px;
            padding: 20px;
            background-color: #1a237e;
            color: white;
            text-align: center;
            font-size: 0.9em;
            border-top: 3px solid #64b5f6;
            width: 100%;
            box-sizing: border-box;
        }

        .footer p {
            margin: 5px 0;
            color: #e3f2fd;
        }

        .footer a {
            color: #90caf9;
            text-decoration: none;
            font-weight: bold;
        }

        .footer a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <img src="img/logo.png" alt="Logo do Sistema" class="header-image">
    <div class="container">
        <h1>Livros Cadastrados</h1>
        <table>
            <thead>
                <tr>
                    <th>Título</th>
                    <th>Autor</th>
                    <th>Ano</th>
                    <th>ISBN</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Livro> livros = LivroDAO.listar();
                    for (Livro livro : livros) {
                %>
                <tr>
                    <td><%= livro.getTitulo() %></td>
                    <td><%= livro.getAutor() %></td>
                    <td><%= livro.getAnoPublicacao() %></td>
                    <td><%= livro.getIsbn() %></td>
                    <td>
                        <form method="post" action="excluir" style="display:inline;">
                            <input type="hidden" name="isbn" value="<%= livro.getIsbn() %>">
                            <input type="submit" value="Excluir">
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <div class="navigation-buttons">
            <a href="cadastro.jsp" class="btn-primary">Cadastrar novo livro</a>
            <a href="index.jsp" class="btn-secondary">Página Inicial</a>
        </div>
    </div>
    <footer class="footer">
        <p>&copy; 2025 Projeto de Cadastro para Livros da Imersão Profissional: Fábrica de Software da Faculdade Unicesumar.</p>
        <p>Desenvolvido por Gustavo Ventura Nery Vianna, R.A.: 23085943-5.</p>
    </footer>
</body>
</html>