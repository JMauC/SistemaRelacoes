package sistema;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Menu {
	public static void main(String[] args) {
		// Explica o funcionamento do programa para o usu�rio

		// Inicializa o Analisador com base nos arquivos que s�o escolhidos
		
		File[] arquivos = Menu.selecionaArquivos(); //Chama janela para receber os arquivos para an�lise
		if (arquivos == null){ //Se o usu�rio opta por cancelar, fecha o pograma
			System.exit(0);
		}

		// Inicializa o Analisador com base nos arquivos que s�o escolhidos
		Analisador analisador = new Analisador(arquivos);
		//Mostra as informa��es relativas � cada rela��o recebida
		for (int i = 0; i < analisador.relacoes.length; i++) {
			mostraInfo(analisador.relacoes[i]);
		}

	}

	private static File[] selecionaArquivos() {
		final JFileChooser seletor = new JFileChooser(); //Inializa elemento gr�fico para sele��o de arquivos
		seletor.setMultiSelectionEnabled(true); //Permite selecionar um ou mais arquivos simult�neos
		seletor.showOpenDialog(null); //Abre a sele��o de arquivos.
		File[] arquivos = seletor.getSelectedFiles(); //Recebe um array contendo os arquivos.

		if (arquivos.length == 0) { //Se n�o foi selecionado nenhum arquivo
			while (arquivos.length == 0) { //Repete o processo at� receber um array v�lido ou pedido de cancelamento 
				int opcaoUsuario = JOptionPane.showConfirmDialog(null, "Selecione ao menos um arquivo para an�lise\nPara sair do programa, clique em cancelar", "Nenhum arquivo selecionado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE); //Exibe um di�logo informando o erro e as op��es dispon�veis (OK ou Cancelar)
				
				if(opcaoUsuario == JOptionPane.CANCEL_OPTION){ //Se o usu�rio cancelar, fecha o programa
					return null;
				}

				seletor.showOpenDialog(null);
				arquivos = seletor.getSelectedFiles();// Abre o di�logo de sele��o novamente
			}
		}


		return arquivos;
	}
	
	public	static void mostraInfo(Relacao relacao) {
		String texto;
		int contador = 0; 

		//Mostra o Tipo da Rela��o
		texto = "O tipo da rela��o �: " + relacao.tipo + "\n";

		//Mostra os elementos do dom�nio
		texto = texto + "Os elementos do dom�nio s�o: {";
		for (int i = 0; i < relacao.tamanhoDominio; i++) {
			texto = texto + i;
			contador++;
			if(relacao.tamanhoDominio == contador){
				texto = texto + "}\n";
				contador = 0;
			} else{
				texto = texto + ", ";
			}
		}
		
		//Mostra os pares da rela��o
		texto = texto + "R = {" ;
		for (int i = 0; i < relacao.tamanhoDominio; i++) {
			for (int j = 0; j < relacao.tamanhoDominio; j++) {
				if (relacao.matrizParesOrdenados[i][j] == true) {
					texto = texto + "(" + i + "," + j + ")";
					contador++;
					
					if(relacao.numeroDeRelacoes == contador){
						texto = texto + "}\n\n";
						contador = 0;
					} else{
						texto = texto + " , ";
					}
				}
			}
		}
		//Mostra as propriedades da rela��o
		texto = texto +	"Reflexiva:"+ ((relacao.reflexiva) ? " sim":" n�o") + "\n" +
		"Irreflexiva:"+ ((relacao.irreflexiva) ? " sim":" n�o") + "\n" +
		"Simetrica:"+ ((relacao.simetrica) ? " sim":" n�o") + "\n" +
		"Antissimetrica:"+ ((relacao.antissimetrica) ? " sim":" n�o") + "\n" + 
		"Transitiva:"+ ((relacao.transitiva) ? " sim":" n�o") + "\n";

		JOptionPane.showMessageDialog(null, texto, "Propriedades da Rela��o", JOptionPane.INFORMATION_MESSAGE, null);
		//JOptionPane.showConfirmDialog(null, texto, "Propriedades da rela��o", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE); //Exibe um di�logo informando o erro e as op��es dispon�veis (OK ou Cancelar)
	}
	
}
