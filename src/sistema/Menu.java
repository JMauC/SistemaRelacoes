package sistema;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Menu {
	public static void main(String[] args) {
		// Explica o funcionamento do programa para o usuário

		// Inicializa o Analisador com base nos arquivos que são escolhidos
		
		File[] arquivos = Menu.selecionaArquivos(); //Chama janela para receber os arquivos para análise
		if (arquivos == null){ //Se o usuário opta por cancelar, fecha o pograma
			System.exit(0);
		}

		// Inicializa o Analisador com base nos arquivos que são escolhidos
		

		// System.out.println(arquivos.length); //Teste
		
	
	}

	private static File[] selecionaArquivos() {
		final JFileChooser seletor = new JFileChooser(); //Inializa elemento gráfico para seleção de arquivos
		seletor.setMultiSelectionEnabled(true); //Permite selecionar um ou mais arquivos simultâneos
		seletor.showOpenDialog(null); //Abre a seleção de arquivos.
		File[] arquivos = seletor.getSelectedFiles(); //Recebe um array contendo os arquivos.

		if (arquivos.length == 0) { //Se não foi selecionado nenhum arquivo
			while (arquivos.length == 0) { //Repete o processo até receber um array válido ou pedido de cancelamento 
				int opcaoUsuario = JOptionPane.showConfirmDialog(null, "Selecione ao menos um arquivo para análise\nPara sair do programa, clique em cancelar", "Nenhum arquivo selecionado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE); //Exibe um diálogo informando o erro e as opções disponíveis (OK ou Cancelar)
				
				if(opcaoUsuario == JOptionPane.CANCEL_OPTION){ //Se o usuário cancelar, fecha o programa
					return null;
				}

				seletor.showOpenDialog(null);
				arquivos = seletor.getSelectedFiles();// Abre o diálogo de seleção novamente
			}
		}


		return arquivos;
	}
}
