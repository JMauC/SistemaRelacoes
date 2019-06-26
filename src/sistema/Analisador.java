package sistema;

public class Analisador {
    Relacao[] relacoes;

    //Construtor do analisador
    public Analisador(int numeroRelacoes) {
        if (numeroRelacoes == 0){
            numeroRelacoes = new Relacao[10];
        }
        numeroRelacoes = new Relacao[numeroRelacoes]; //Inicia o programa com o número de relações informad
        
             
        }
   
        //FAZ UM LOOP PELAS LINHAS DO ARRAY
    public static void outputArray(int[][] array)
    {
        //FAZ UM LOOP PELAS COLUNAS DA LINHA ATUAL
        for(int linha = 0; linha < array.length; linha++)
        {
            //FAZ LOOP PELAS COLUNAS DA LINHA ATUAL
            for( int coluna = 0; coluna < array[linha].length; coluna++)
                System.out.printf("%d ", array[linha][coluna]);     
            System.out.println();
        }
    }
}