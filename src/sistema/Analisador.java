package sistema;

import java.io.File;

public class Analisador {
    Relacao[] relacoes;

    //Construtor do analisador
    public Analisador(File[] arquivos) {
        relacoes = new Relacao[arquivos.length]; //Define um array de relações com tamanho igual ao número de arquivos
        
        for (int i = 0; i < arquivos.length; i++) { //Para cada arquivo, cria uma relação no array
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

        if(contador == relacao.tamanhoDominio){ // Se todo elemento do domínio faz par consigo mesmo, é reflexiva
            relacao.reflexiva = true;
        }

        if(contador == 0){ // Se nenhum elemento do domínio faz par consigo mesmo, é irreflexiva
            relacao.irreflexiva = true;
        }
    }
    

    private void checaSimetrica(Relacao relacao){
        for (int i = 0; i < relacao.tamanhoDominio; i++) { // Percorre todas as relações
            for (int j = 0; j < relacao.tamanhoDominio; j++) {
                if (relacao.matrizParesOrdenados[i][j] == true && // Se existe a relação (x,y) e não existe (y,x), não é simétrica. 
                    relacao.matrizParesOrdenados[j][i] == false){
                    
                    relacao.simetrica = false;
                    return;
                }
            }
        }
        relacao.simetrica = true; // Se não foi interrompido ao checar todos, então é simétrica
    }

    private void checaAntissimetrica(Relacao relacao){
        for (int i = 0; i < relacao.tamanhoDominio; i++) { // Percorre todas as relações
            for (int j = 0; j < relacao.tamanhoDominio; j++) { // Se existe a relação (x,y), existe (y,x) e x != y, não é antissimétrica.
                if (relacao.matrizParesOrdenados[i][j] == true && 
                    relacao.matrizParesOrdenados[j][i] == true && 
                    i != j){

                    relacao.antissimetrica = false;
                    return;
                }
            }
        }
        relacao.antissimetrica = true; // Se não foi interrompido ao checar todos, então é antissimétrica
    }

    private void checaTransitiva(Relacao relacao) {
        for (int i = 0; i < relacao.tamanhoDominio; i++) { // Percorre todas as relações
            for (int j = 0; j < relacao.tamanhoDominio; j++) { // Se existe a relação (x,y), existe (y,z) e não existe (x,z), não é transitiva.
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
        relacao.antissimetrica = true; // Se não foi interrompido ao checar todos, então é transitiva
    }
    
    private void checaFuncional(Relacao relacao) {
        int contador = 0;
        for (int i = 0; i < relacao.tamanhoDominio; i++) { // Confere se cada elemento x faz somente uma relação com o codomínio
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
        for (int j = 0; j < relacao.tamanhoDominio; j++) { // Confere se cada elemento y faz somente uma relação com o domínio
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
        for (int i = 0; i < relacao.tamanhoDominio; i++) { // Confere se todo elemento x faz ao menos uma relação com o codomínio
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
        for (int j = 0; j < relacao.tamanhoDominio; j++) { // Confere se todo elemento y faz ao menos uma relação com o domínio
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
        
        

    