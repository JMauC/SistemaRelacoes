package sistema;

import java.io.File;

import javax.swing.JFileChooser;

public class Menu {

	public static void main(String[] args) {
		// Explica o funcionamento do programa

		//Inicializa o Analisador com base nos arquivos que são escolhidos
		final JFileChooser seletor = new JFileChooser();
		int returnVal = seletor.showOpenDialog(null);
	}

}
