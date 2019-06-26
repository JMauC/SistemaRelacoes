package sistema;

import java.io.File;

public class Analisador {
    Relacao[] relacoes;

    //Construtor do analisador
    public Analisador(File[] arquivos) {
        relacoes = new Relacao[arquivos.length]; //Define um array de rela��es com tamanho igual ao n�mero de arquivos
        
        for (int i = 0; i < arquivos.length; i++) { //Para cada arquivo, cria uma rela��o no array
            relacoes[i] = new Relacao(arquivos[i]);
        }
    }
    
}