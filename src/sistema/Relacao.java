package sistema;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Relacao {
    int tamanhoDominio; // Define o n�mero de linhas/colunas da matriz de rela��es
    int numeroDeRelacoes; // Define o n�mero de linhas puladas antes de finalizar a execu��o do construtor
    boolean[][] matrizParesOrdenados; // Representa cada par ordenado da rela��o

    // Construtor do analisador
    public Relacao(File arquivo){
        try{
            FileReader leitorArquivo = new FileReader(arquivo); // Abre o arquivo
            BufferedReader leitor  = new BufferedReader(leitorArquivo); // Inicia um buffer 
            
            tamanhoDominio = Integer.parseInt(leitor.readLine()); // Le aprimeira linha, converte para int e salva como tamanho do dom�nio
            matrizParesOrdenados = new boolean[tamanhoDominio][tamanhoDominio]; //Inicializa a matriz com o tamanho especificado
            numeroDeRelacoes = Integer.parseInt(leitor.readLine()); // L� a segunda linha

            //System.out.println(tamanhoDominio);
            //System.out.println(numeroDeRelacoes);
            for (int contagem = 0; contagem < numeroDeRelacoes; contagem++) {// Le linha a linha at� o contador chegar ao n�mero de rela��es indicado no arquivo
               
               String linha = leitor.readLine();// Le uma linha e salva como String
               String[] elementos = linha.split(" ");// Divide a String em substrings utilizando o espa�o como demarcador
               int i = Integer.parseInt(elementos[0]);// Coloca o primeiro elemento como n�mero da linha
               int j = Integer.parseInt(elementos[1]);// Coloca o segundo elemento como n�mero da coluna
                
               matrizParesOrdenados[i][j] = true; // Marca o par ordenado como existente 
            }
            
            leitorArquivo.close(); // Fecha o leitor para evitar vazamento de mem�ria

        } catch (FileNotFoundException e) { //Tratamento de erros
        System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        } catch (IOException e){
            System.err.printf("Erro desconhecido: %s.\n", e.getMessage());
        }
        
    }
    
}