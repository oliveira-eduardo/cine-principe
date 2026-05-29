package control;

import model.Critico;
import model.Filme;

public class ControlCritica {

    public String salvarCritica(Critico critico, Filme filme, String titulo, String comentario, String notaStr) {
        
        if (titulo == null || titulo.trim().isEmpty()) {
            return "O título da crítica não pode estar vazio.";
        }
        if (comentario == null || comentario.trim().isEmpty()) {
            return "O comentário não pode estar vazio.";
        }
        if (notaStr == null || notaStr.trim().isEmpty()) {
            return "A nota deve ser informada.";
        }

        double nota;
        try {
            nota = Double.parseDouble(notaStr.replace(",", "."));
        } catch (NumberFormatException e) {
            return "Digite uma nota numérica válida.";
        }

        if (nota < 0 || nota > 5) {
            return "A nota deve estar entre 0 e 5.";
        }

        try {
            critico.atribuirCritica(filme, nota, titulo, comentario);
            return "";
        } catch (Exception e) {
            return "Erro inesperado ao processar a crítica: " + e.getMessage();
        }
    }
}