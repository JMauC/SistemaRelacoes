package sistema;

import java.io.File;

public class Analisador {
    Relacao[] relacoes;

    //Construtor do analisador
    public Analisador(File[] arquivos) {
        relacoes = new Relacao[arquivos.length]; //Define um array de relações com tamanho igual ao número de arquivos
        
        for (int i = 0; i < arquivos.length; i++) { //Para cada arquivo, cria uma relação no array
            relacoes[i] = new Relacao(arquivos[i]);
        }
    }
    
}