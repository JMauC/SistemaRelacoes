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

		System.out.print("reflexiva:"+ analisador.relacoes[0].reflexiva + "\n");
		System.out.print("irreflexiva:"+ analisador.relacoes[0].irreflexiva + "\n");
		System.out.print("simetrica:"+ analisador.relacoes[0].simetrica + "\n");
		System.out.print("antissimetrica:"+ analisador.relacoes[0].antissimetrica + "\n");
		System.out.print("transitiva:"+ analisador.relacoes[0].transitiva + "\n");
		/*for (int i = 0; i < analisador.relacoes[0].tamanhoDominio; i++) {
			for (int j = 0; j < analisador.relacoes[0].tamanhoDominio; j++) {
				
			}
			System.out.print("\n");
		}
		*/
		// System.out.println(arquivos.length); //Teste
		
	
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
	
	/*public void name() {
		
		String texto = "Os elementos s�o: {"
		for (int i = 0; i < ; i++) {
			
		}
		
		JOptionPane.showMessageDialog(null, ("Os elementos do dom�nio s�o: {" + ));
	}
	*/
}
