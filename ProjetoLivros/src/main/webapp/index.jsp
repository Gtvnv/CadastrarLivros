<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bem-vindo! Esse é meu Projeto de Livros</title>
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
            max-width: 450px;
            margin: 20px auto;
            background-color: #ffffff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            text-align: center;
            border: 1px solid #b3e0ff;
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        h1 {
            color: #0d47a1;
            margin-bottom: 30px;
            font-size: 2.2em;
        }

        .button-link {
            display: block;
            background-color: #2196f3;
            color: white;
            padding: 15px 25px;
            border-radius: 8px;
            text-decoration: none;
            font-weight: bold;
            transition: background-color 0.3s ease, transform 0.2s;
            margin-bottom: 15px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .button-link:hover {
            background-color: #1976d2;
            transform: translateY(-2px);
        }

        .button-link:last-child {
            margin-bottom: 0;
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
        <h1>Sistema de Cadastro para Livros</h1>
        <a href="cadastro.jsp" class="button-link">Cadastrar Novo Livro</a>
        <a href="listagem.jsp" class="button-link">Ver Lista de Livros</a>
    </div>
    <footer class="footer">
		<p>&copy; 2025 Projeto de Cadastro para Livros da Imersão Profissional: Fábrica de Software da Faculdade Unicesumar.</p>
        <p>Desenvolvido por Gustavo Ventura Nery Vianna, R.A.: 23085943-5.</p>
    </footer>
</body>
</html>