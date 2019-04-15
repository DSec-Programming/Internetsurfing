package priv.internetsurfing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Font.loadFont(getClass().getResourceAsStream("fonts/ALIEE13.TTF"), 14);
        
        StartController root = new StartController();         //initialize root Pane
        root.setView(FXMLLoader.load(getClass().getResource("ui/Internetsurfing.fxml")));
        Scene scene = new Scene(root.getView());   
        
        StandardPaths sp = new StandardPaths();
        sp.createFile();
        
        primaryStage.setTitle("Internetsurfing- Own Way");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/gears.png")));    //add picture to stage
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
