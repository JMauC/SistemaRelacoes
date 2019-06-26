package sistema;

import java.io.File;

public class Analisador {
    Relacao[] relacoes;

    //Construtor do analisador
    public Analisador(File[] arquivos) {
        relacoes = new Relacao[arquivos.length]; //Define um array de rela��es com tamanho igual ao n�mero de arquivos
        
        for (int i = 0; i < arquivos.length; i++) { //Para cada arquivo, cria uma rela��o no array
            relacoes[i] = new Relacao(arquivos[i]);
            analisaRelacao(relacoes[i]);
        }
    }
    
    private void analisaRelacao(Relacao relacao) {
        for (int i = 0; i < relacao.tamanhoDominio; i++) {
			for (int j = 0; j < relacao.tamanhoDominio; j++) {
                checaReflexivaIrreflexiva(relacao);
                checaSimetrica(relacao);
                checaAntissimetrica(relacao);
                checaTransitiva(relacao);
			}
		}
    }
    
    private void checaReflexivaIrreflexiva(Relacao relacao){
        int contador = 0;
        for (int i = 0; i < relacao.tamanhoDominio; i++) { // Confere quantos elementos fazem par consigo mesmo
            if (relacao.matrizParesOrdenados[i][i] == true){
                contador++;
            }
        }

        if(contador == relacao.tamanhoDominio){ // Se todo elemento do dom�nio faz par consigo mesmo, � reflexiva
            relacao.reflexiva = true;
        }

        if(contador == 0){ // Se nenhum elemento do dom�nio faz par consigo mesmo, � irreflexiva
            relacao.irreflexiva = true;
        }
    }
    

    private void checaSimetrica(Relacao relacao){
        for (int i = 0; i < relacao.tamanhoDominio; i++) { // Percorre todas as rela��es
            for (int j = 0; j < relacao.tamanhoDominio; j++) {
                if (relacao.matrizParesOrdenados[i][j] == true && // Se existe a rela��o (x,y) e n�o existe (y,x), n�o � sim�trica. 
                    relacao.matrizParesOrdenados[j][i] == false){
                    
                    relacao.simetrica = false;
                    return;
                }
            }
        }
        relacao.simetrica = true; // Se n�o foi interrompido ao checar todos, ent�o � sim�trica
    }

    private void checaAntissimetrica(Relacao relacao){
        for (int i = 0; i < relacao.tamanhoDominio; i++) { // Percorre todas as rela��es
            for (int j = 0; j < relacao.tamanhoDominio; j++) { // Se existe a rela��o (x,y), existe (y,x) e x != y, n�o � antissim�trica.
                if (relacao.matrizParesOrdenados[i][j] == true && 
                    relacao.matrizParesOrdenados[j][i] == true && 
                    i != j){

                    relacao.antissimetrica = false;
                    return;
                }
            }
        }
        relacao.antissimetrica = true; // Se n�o foi interrompido ao checar todos, ent�o � antissim�trica
    }

    private void checaTransitiva(Relacao relacao) {
        for (int i = 0; i < relacao.tamanhoDominio; i++) { // Percorre todas as rela��es
            for (int j = 0; j < relacao.tamanhoDominio; j++) { // Se existe a rela��o (x,y), existe (y,z) e n�o existe (x,z), n�o � transitiva.
                for (int z = 0; z < relacao.tamanhoDominio; z++) {
                    if (relacao.matrizParesOrdenados[i][j] == true && 
                        relacao.matrizParesOrdenados[j][z] == true && 
                        relacao.matrizParesOrdenados[i][z] == false){

                        relacao.transitiva = false;
                        return;
                    }
                }
            }
        }
        relacao.antissimetrica = true; // Se n�o foi interrompido ao checar todos, ent�o � transitiva
    }
    
}
        
        

    