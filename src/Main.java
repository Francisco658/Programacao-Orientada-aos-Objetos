import exceptions.InputIncorreto;
import mvc.*;
import parser.Parser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class  Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InputIncorreto {

        IModel model = new Model();
        IController controller = new Controller(model);
        File stateFile = new File("output/state.dat");
        if (stateFile.exists() && stateFile.length() > 0) {
            // load state from file
            controller.carregarEstado("output/state.dat");
        } else {
            // if state file not found, read logs file and pass info to model
            parser.Parser p = new parser.Parser("Logs.txt");
            p.lerFicheiro(model);
            controller.setTempoDoSistema(LocalDate.now());
        }
        IView view = new View(controller);
        controller.setView(view);
        view.runMenuInicial();

    }
}