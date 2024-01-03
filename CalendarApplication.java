// Itay Mizikov ; ID: 315541615 ; 20/5/23
package q2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalendarApplication extends Application{
	public void start(Stage stage) throws Exception{ 
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("CalendarApplication.fxml")); 
		Scene scene = new Scene(root); 
		stage.setTitle("My Calendar"); 
		stage.setScene(scene); 
		stage.show(); 
	} 
	
	public static void main(String[] args){ 
		launch(args); 
		System.out.println();
	} 
}
