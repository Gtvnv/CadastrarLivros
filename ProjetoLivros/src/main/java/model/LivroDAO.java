package model;

import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private static List<Livro> livros = new ArrayList<>();

    public static void adicionar(Livro livro) {
        livros.add(livro);
    }

    public static List<Livro> listar() {
        return livros;
    }

    public static void removerPorIsbn(String isbn) {
        livros.removeIf(l -> l.getIsbn().equals(isbn));
    }
}
