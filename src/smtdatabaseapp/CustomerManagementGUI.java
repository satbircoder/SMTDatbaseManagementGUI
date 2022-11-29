
package smtdatabaseapp;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerManagementGUI extends Application {
    
    @Override
        public void start(Stage stage) throws Exception {
        stage.setTitle("SMT Customer Management");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SMTGUI.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
    }
    public static void main(String[] args) {
        try
        {
          CreateDatabase.createCustomerDB();
          launch(args);
         
        }
        catch(Exception e)
        {
            System.out.println(e.getCause());
        }
    }

}
