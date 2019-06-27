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
		Analisador analisador = new Analisador(arquivos);
		//Mostra as informações relativas à cada relação recebida
		for (int i = 0; i < analisador.relacoes.length; i++) {
			mostraInfo(analisador.relacoes[i]);
		}

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
	
	public	static void mostraInfo(Relacao relacao) {
		String texto;
		int contador = 0; 

		//Mostra o Tipo da Relação
		texto = "O tipo da relação é: " + relacao.tipo + "\n";

		//Mostra os elementos do domínio
		texto = texto + "Os elementos do domínio são: {";
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
		
		//Mostra os pares da relação
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
		//Mostra as propriedades da relação
		texto = texto +	"Reflexiva:"+ ((relacao.reflexiva) ? " sim":" não") + "\n" +
		"Irreflexiva:"+ ((relacao.irreflexiva) ? " sim":" não") + "\n" +
		"Simetrica:"+ ((relacao.simetrica) ? " sim":" não") + "\n" +
		"Antissimetrica:"+ ((relacao.antissimetrica) ? " sim":" não") + "\n" + 
		"Transitiva:"+ ((relacao.transitiva) ? " sim":" não") + "\n";

		JOptionPane.showMessageDialog(null, texto, "Propriedades da Relação", JOptionPane.INFORMATION_MESSAGE, null);
		//JOptionPane.showConfirmDialog(null, texto, "Propriedades da relação", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE); //Exibe um diálogo informando o erro e as opções disponíveis (OK ou Cancelar)
	}
	
}
