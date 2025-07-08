<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Livros</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #e0f2f7;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
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
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            text-align: center;
            box-sizing: border-box;
            border: 1px solid #b3e0ff;
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        h1 {
            text-align: center;
            color: #0d47a1;
            margin-bottom: 25px;
            font-size: 2em;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            gap: 10px;
        }

        form label {
            display: block;
            margin-bottom: -5px;
            font-weight: bold;
            color: #1976d2;
        }

        input[type="text"],
        input[type="number"] {
            width: calc(100% - 20px);
            padding: 10px;
            font-size: 16px;
            border: 1px solid #90caf9;
            border-radius: 6px;
            box-sizing: border-box;
            transition: border-color 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="number"]:focus {
            border-color: #2196f3;
            outline: none;
            box-shadow: 0 0 0 2px rgba(33, 150, 243, 0.2);
        }

        input[type="submit"] {
            width: 100%;
            padding: 12px 15px;
            font-size: 17px;
            border: none;
            border-radius: 8px;
            background-color: #4caf50;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s;
            margin-top: 20x;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        input[type="submit"]:hover {
            background-color: #388e3c;
            transform: translateY(-2px);
        }

        p {
            margin-top: 15px;
            text-align: center;
        }

        p[style="color:red;"] {
            color: #d32f2f !important;
            font-weight: bold;
            font-size: 0.95em;
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
        <h1>Cadastrar Livro</h1>
        <form method="post" action="cadastro">
            <label for="titulo">Título:</label>
            <input type="text" id="titulo" name="titulo"><br>
            <label for="autor">Autor:</label>
            <input type="text" id="autor" name="autor"><br>
            <label for="ano">Ano de Publicação:</label>
            <input type="text" id="ano" name="ano"><br>
            <label for="isbn">ISBN:</label>
            <input type="text" id="isbn" name="isbn"><br>
            <input type="submit" value="Cadastrar">
        </form>
        <p style="color:red;">
            <%= request.getAttribute("erro") != null ? request.getAttribute("erro") : "" %>
        </p>
        <div class="navigation-buttons">
            <a href="listagem.jsp" class="btn-primary">Ver lista de livros</a>
            <a href="index.jsp" class="btn-secondary">Página Inicial</a>
        </div>
    </div>
    <footer class="footer">
        <p>&copy; 2025 Projeto de Cadastro para Livros da Imersão Profissional: Fábrica de Software da Faculdade Unicesumar.</p>
        <p>Desenvolvido por Gustavo Ventura Nery Vianna, R.A.: 23085943-5.</p>
    </footer>
</body>
</html>