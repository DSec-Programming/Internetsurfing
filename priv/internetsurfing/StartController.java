package priv.internetsurfing;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class StartController
{    
    private Stage stage;

    private Scene scene;

    private FXMLLoader fxmlLoader;
    
    private Node node;
    
    private Media sound = new Media(this.getClass().getResource("sounds/buttonSound.mp3").toExternalForm());
    
    private MediaPlayer mediaPlayer = new MediaPlayer(sound);

    @FXML
    private AnchorPane root;

    @FXML
    private GridPane background;

    @FXML
    private Button contin, cancel;

    @FXML
    private Label header;   

    @FXML
    public void cancelClicked(ActionEvent e)        //action when cancel button is clicked
    {
        Platform.exit();
    }

    @FXML
    public void continueClicked(ActionEvent e)      //action when continue button is clicked
    {
        showMainMenu(e);
    }
    
    public void buttonEntered()                 //action when mouse is over button
    {        
        if(contin.isHover())
        {
            contin.setTextFill(Color.RED);
            mediaPlayer.play();
        }
        
        if(cancel.isHover())
        {
            cancel.setTextFill(Color.RED);
            mediaPlayer.play();
        }
    }
    
    public void buttonExited()              //action when mouse leaves button
    {
        try
        {
            Paint temp = Paint.valueOf("#e0eeee");
            cancel.setTextFill(temp);
            contin.setTextFill(temp);
            mediaPlayer.stop();
        }
        catch(NullPointerException e)
        {
            
        }      
    }

   
    public void showMainMenu(ActionEvent e)         //change scene to new one
    {
        try
        {
            node = (Node) e.getSource();                        // node becomes a button
            stage = (Stage) node.getScene().getWindow();        //find out on which stage the button is placed
            stage.close();                                      //delete "start" stage
            fxmlLoader = new FXMLLoader(getClass().getResource("ui/Internetsurfing_Mainmenu.fxml"));       
            root = (AnchorPane) fxmlLoader.load();
            scene = new Scene(root);
            
            stage.setScene(scene);          //setting new scene
            stage.show();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }

    public void setView(AnchorPane pane)
    {
        this.root = pane;
    }

    public AnchorPane getView()
    {
        return this.root;
    }
}
