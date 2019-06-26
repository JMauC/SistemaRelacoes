package sistema;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Window;

public class Gui{


    public static List<File> seletorArquivos(Window janela) throws IOException{
        List<File> arquivos = new ArrayList<>();
        
        FileChooser seletor = new FileChooser();
        seletor.setTitle("Selecione os arquivos para análise");
        seletor.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Endorelações", "*.txt"));
        
        arquivos = seletor.showOpenMultipleDialog(janela);
        
        if(arquivos == null){
            throw new IOException("Por favor, selecione ao menos um arquivo txt.");
        }
        
        return arquivos;
    }

    public static void main(String[] args) {
        try {
            List<File> arquivos = Gui.seletorArquivos(null);
        } catch (Exception e) {
            
        }
        
    }
}


