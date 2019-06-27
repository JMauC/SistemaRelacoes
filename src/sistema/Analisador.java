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
                
                checaFuncional(relacao);
                checaInjetora(relacao);
                checaTotal(relacao);
                checaSobrejetora(relacao);
                checaTipo(relacao);
                //Acho que alguns ifs concatenados, um pra cada possibilidade de tipo. Escreve o tipo na String tipo do objeto
                
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
    
    private void checaFuncional(Relacao relacao) {
        int contador = 0;
        for (int i = 0; i < relacao.tamanhoDominio; i++) { // Confere se cada elemento x faz somente uma rela��o com o codom�nio
            for (int j = 0; j < relacao.tamanhoDominio; j++) {
                if (relacao.matrizParesOrdenados[i][j] == true) {
                    contador++;
                }   
            }
            
            if(contador > 1){
                relacao.funcional = false;
                return;
            }
            contador = 0;
        }
        relacao.funcional = true;
    }
    
    private void checaInjetora(Relacao relacao) {
        int contador = 0;
        for (int j = 0; j < relacao.tamanhoDominio; j++) { // Confere se cada elemento y faz somente uma rela��o com o dom�nio
            for (int i = 0; i < relacao.tamanhoDominio; i++) {
                if (relacao.matrizParesOrdenados[i][j] == true) {
                    contador++;
                }   
            }
            
            if(contador > 1){
                relacao.injetora = false;
                return;
            }
            contador = 0;
        }
        relacao.injetora = true;
    }

    private void checaTotal(Relacao relacao) {
        int contador = 0;
        for (int i = 0; i < relacao.tamanhoDominio; i++) { // Confere se todo elemento x faz ao menos uma rela��o com o codom�nio
            for (int j = 0; j < relacao.tamanhoDominio; j++) {
                if (relacao.matrizParesOrdenados[i][j] == true) {
                    contador++;
                }   
            }
            
            if(contador < 1){
                relacao.total = false;
                return;
            }
            contador = 0;
        }
        relacao.total = true;
    }

    private void checaSobrejetora(Relacao relacao) {
        int contador = 0; 
        for (int j = 0; j < relacao.tamanhoDominio; j++) { // Confere se todo elemento y faz ao menos uma rela��o com o dom�nio
            for (int i = 0; i < relacao.tamanhoDominio; i++) {
                if (relacao.matrizParesOrdenados[i][j] == true) {
                    contador++;
                }   
            }
            
            if(contador < 1){
                relacao.sobrejetora = false;
                return;
            }
            contador = 0;
        }
        relacao.sobrejetora = true;
    }

    private void checaTipo(Relacao relacao) {
        if (relacao.total && relacao.injetora) {
            relacao.tipo = "Monomorfismo";
        } else if(relacao.funcional && relacao.sobrejetora) {
            relacao.tipo = "Epimorfismo";
        } else if(relacao.injetora && relacao.sobrejetora) {
            relacao.tipo = "Bijetora";
        } else if(relacao.total && relacao.funcional && relacao.injetora && relacao.sobrejetora) {
            relacao.tipo = "Isomorfismo";
        } else if(relacao.injetora) {
            relacao.tipo = "Injetora";
        } else if(relacao.sobrejetora) {
            relacao.tipo = "Sobrejetora";
        } else if(relacao.total) {
            relacao.tipo = "Total";
        } else if(relacao.funcional) {
            relacao.tipo = "Funcional";
        }
    }
}
        
        

    