package sistema;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Relacao {
    int tamanhoDominio; // Define o número de linhas/colunas da matriz de relações
    int numeroDeRelacoes; // Define o número de linhas puladas antes de finalizar a execução do construtor
    boolean[][] matrizParesOrdenados; // Representa cada par ordenado da relação

    // Construtor do analisador
    public Relacao(File arquivo){
        try{
            FileReader leitorArquivo = new FileReader(arquivo); // Abre o arquivo
            BufferedReader leitor  = new BufferedReader(leitorArquivo); // Inicia um buffer 
            
            tamanhoDominio = Integer.parseInt(leitor.readLine()); // Le aprimeira linha, converte para int e salva como tamanho do domínio
            matrizParesOrdenados = new boolean[tamanhoDominio][tamanhoDominio]; //Inicializa a matriz com o tamanho especificado
            numeroDeRelacoes = Integer.parseInt(leitor.readLine()); // Lê a segunda linha

            //System.out.println(tamanhoDominio);
            //System.out.println(numeroDeRelacoes);
            for (int contagem = 0; contagem < numeroDeRelacoes; contagem++) {// Le linha a linha até o contador chegar ao número de relações indicado no arquivo
               
               String linha = leitor.readLine();// Le uma linha e salva como String
               String[] elementos = linha.split(" ");// Divide a String em substrings utilizando o espaço como demarcador
               int i = Integer.parseInt(elementos[0]);// Coloca o primeiro elemento como número da linha
               int j = Integer.parseInt(elementos[1]);// Coloca o segundo elemento como número da coluna
                
               matrizParesOrdenados[i][j] = true; // Marca o par ordenado como existente 
            }
            
            leitorArquivo.close(); // Fecha o leitor para evitar vazamento de memória

        } catch (FileNotFoundException e) { //Tratamento de erros
        System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        } catch (IOException e){
            System.err.printf("Erro desconhecido: %s.\n", e.getMessage());
        }
        
    }
    
}