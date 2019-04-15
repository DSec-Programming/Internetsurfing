package priv.internetsurfing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainMenuController
{    
    private Properties browserInitializer = new Properties();
    
    private FileInputStream input; 

    private FileOutputStream output; 
    
    private Media buttonHoverSound = new Media(this.getClass().getResource("sounds/buttonSound.mp3").toExternalForm());
    
    private MediaPlayer mediaPlayer = new MediaPlayer(buttonHoverSound);    
    
    @FXML
    private AnchorPane root;

    @FXML
    private Button search, delete, searchBrowser;

    @FXML
    private TextField searchbar;

    @FXML
    private ComboBox<String> comboBox, style;

    @FXML
    private Label label, labelStyle;
    
    @FXML
    private CheckBox soundCheckBox;

    private Stage fileStage, stage;
    
    private FXMLLoader fxmlLoader;
    
    private Scene scene;
    
    private Node node;

    private ObservableList<String> browserList = FXCollections.observableArrayList(); // add names of browsers to list                                                                                                                                             
   
    public void deleteClicked() // action when delete button is pressed
    {
        searchbar.clear();
        resetLabel();
    }

    public void search() // action when search button is pressed
    {
        try
        {
            resetLabel();
            openBrowser();
            
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    public void searchBrowserClicked() // action when the searchBrowser button is clicked
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose browser path");
        fileChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter(".exe", "*.exe"), new FileChooser.ExtensionFilter("All Files", "*"));
        fileChooser.setInitialDirectory(new File(System.getProperty("file.separator")));
       
        try
        {
            output = new FileOutputStream(System.getProperty("user.home")+"/InternetSurfing.properties");
            String test = fileChooser.showOpenDialog(fileStage).getPath(); 
        
        if(test.contains("firefox.exe") && !test.contains("Browser") && !comboBox.getItems().contains("Firefox"))       //controls if the data is selected. When true than add it to the comboBox
        {
            browserInitializer.setProperty("Firefox", test);          
            browserList.add("Firefox");
            comboBox.getItems().add("Firefox");
        }    
        
        if(test.contains("chrome.exe") && !comboBox.getItems().contains("Chrome"))
        {
            browserInitializer.setProperty("Chrome", test);
            browserList.add("Chrome");
            comboBox.getItems().add("Chrome");
        }
        
        if(test.contains("launcher.exe") && !comboBox.getItems().contains("Opera"))
        {
            browserInitializer.setProperty("Opera", test);
            browserList.add("Opera");
            comboBox.getItems().add("Opera");
        }
        
        if(test.contains("iexplore.exe") && !comboBox.getItems().contains("Internet Explorer"))
        {
            browserInitializer.setProperty("Internet Explorer", test);;
            browserList.add("Internet Explorer");
            comboBox.getItems().add("Internet Explorer");
        }
        
        if(test.contains("Browser") && !comboBox.getItems().contains("Tor Browser"))
        {
            browserInitializer.setProperty("Tor Browser", test);
            browserList.add("Tor Browser");           
            comboBox.getItems().add("Tor Browser");
        }
        
        comboBox.getSelectionModel().select(0);
        browserInitializer.store(output, null);

        }       
        catch (IOException e)
        {
            e.printStackTrace();
        }        
        
        catch(NullPointerException e)
        {
            try
            {
                browserInitializer.store(output, null);
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
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

    public void openBrowser() // finds out which browser is selected and opens it with the given URL of the searchbar. --> blank field = standard side of the browser
    {
        if (comboBox.getSelectionModel().getSelectedItem() == "Firefox")
        {
            try
            {
                String url = searchbar.getText();
                new ProcessBuilder(browserInitializer.getProperty("Firefox"), url).start();
                label.setText("Side succsessfully opened!");
            }
            catch (IOException e) // when the program path is wrong or the program is not installed an error occurs
            {
                Alert error = new Alert(AlertType.INFORMATION);
                error.setHeaderText(null);
                error.getDialogPane().getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
                error.setTitle("Error");
                error.setContentText("The selected browser is currently not installed on your system or the set path is wrong. Please try another one.");
                error.show();
            }
        }

        if (comboBox.getSelectionModel().getSelectedItem() == "Chrome")
        {
            try
            {
                String url = searchbar.getText();
                new ProcessBuilder(browserInitializer.getProperty("Chrome"), url).start();
                label.setText("Side succsessfully opened!");
            }
            catch (IOException e)
            {
                Alert error = new Alert(AlertType.INFORMATION);
                error.setHeaderText(null);
                error.getDialogPane().getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
                error.setTitle("Error");
                error.setContentText("The selected browser is currently not installed on your system or the set path is wrong. Please try another one.");
                error.show();
            }
        }

        if (comboBox.getSelectionModel().getSelectedItem() == "Opera")
        {
            try
            {
                String url = searchbar.getText();
                new ProcessBuilder(browserInitializer.getProperty("Opera"), url).start();
                label.setText("Side succsessfully opened!");
            }
            catch (IOException e)
            {
                Alert error = new Alert(AlertType.INFORMATION);
                error.setHeaderText(null);
                error.getDialogPane().getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
                error.setTitle("Error");
                error.setContentText("The selected browser is currently not installed on your system or the set path is wrong. Please try another one.");
                error.show();
            }
        }

        if (comboBox.getSelectionModel().getSelectedItem() == "Internet Explorer")
        {
            try
            {
                String url = searchbar.getText();
                new ProcessBuilder(browserInitializer.getProperty("Internet Explorer"), url).start();
                label.setText("Side succsessfully opened!");
            }
            catch (IOException e)
            {
                Alert error = new Alert(AlertType.INFORMATION);
                error.setHeaderText(null);
                error.getDialogPane().getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
                error.setTitle("Error");
                error.setContentText("The selected browser is currently not installed on your system or the set path is wrong. Please try another one.");
                error.show();
            }
        }

        if (comboBox.getSelectionModel().getSelectedItem() == "Tor Browser")
        {
            try
            {
                String url = searchbar.getText();
                new ProcessBuilder(browserInitializer.getProperty("Tor Browser"), url).start();
                label.setText("Side succsessfully opened!");
            }
            catch (IOException e)
            {
                Alert error = new Alert(AlertType.INFORMATION);
                error.setHeaderText(null);
                error.getDialogPane().getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
                error.setTitle("Error");
                error.setContentText("The selected browser is currently not installed on your system or the set path is wrong. Please try another one.");
                error.show();
            }
        }
    }

    public void resetLabel() // sets the label blank
    {
        this.label.setText(null);
    }

    public void searchbarClicked() // rests the label when mouse is over searchbar
    {
        resetLabel();
    }

    public void buttonEntered() // action when mouse is over button
    {
        if (search.isHover())
        {
            search.setTextFill(Color.RED);
            mediaPlayer.play();
        }

        if (delete.isHover())
        {
            delete.setTextFill(Color.RED);
            mediaPlayer.play();
        }

        if (searchBrowser.isHover())
        {
            searchBrowser.setTextFill(Color.RED);
            mediaPlayer.play();
        }
    }

    public void buttonExited() // action when mouse leaves button
    {
        Paint temp = Paint.valueOf("#e0eeee");
        delete.setTextFill(temp);
        search.setTextFill(temp);
        mediaPlayer.stop();
    }
    
    public void searchBrowserExited()
    {
        Paint temp = Paint.valueOf("#e0eeee");
        searchBrowser.setTextFill(temp);
        mediaPlayer.stop();
    }
    
    public void buttonExited2()
    {
        Paint temp = Paint.valueOf("#000000");
        searchBrowser.setTextFill(temp);
        mediaPlayer.stop();
    }

    public void initSearchBrowser()     //initializes the comboBox with the existing browsers
    {    
        try
        {
            File firefoxPath = new File(browserInitializer.getProperty("Firefox"));
        
            File chromePath = new File(browserInitializer.getProperty("Chrome"));
        
            File operaPath = new File(browserInitializer.getProperty("Opera"));
        
            File internetExplorerPath = new File(browserInitializer.getProperty("Internet Explorer"));
        
            File torPath = new File(browserInitializer.getProperty("Tor Browser"));
        
        
            if(firefoxPath.exists())
            {
                browserList.add("Firefox");
            }
        
            if(chromePath.exists())
            {
                browserList.add("Chrome");
            }
        
            if(operaPath.exists())
            {
                browserList.add("Opera");
            }
        
            if(internetExplorerPath.exists())
            {
                browserList.add("Internet Explorer");
            }
        
            if(torPath.exists())
            {
                browserList.add("Tor Browser");
            }      
        }
        catch(NullPointerException e)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("The propertie data is empty, pls search browsers manually");
            alert.showAndWait();
        }
      
    }
    
    public void comboBoxClicked()
    {
        resetLabel();
    }
    
    public void initStyleComboBox()      //initializes the combo box of the styles
    {
        this.style.getItems().add("Standard");
        this.style.getItems().add("Silver");
    }
  
    
    public void changeStyle(ActionEvent e)      //method to change the style of the main menu
    {
        try
        {
            if(style.getSelectionModel().isSelected(0))
            {
                node = (Node) e.getSource();
                stage = (Stage) node.getScene().getWindow();
                fxmlLoader = new FXMLLoader(getClass().getResource("ui/Internetsurfing_Mainmenu.fxml"));
                root = (AnchorPane) fxmlLoader.load();
                scene = new Scene(root);
                stage.setScene(scene);  
            }           
            
            if(style.getSelectionModel().isSelected(1))
            {
                node = (Node) e.getSource();
                stage = (Stage) node.getScene().getWindow();
                fxmlLoader = new FXMLLoader(getClass().getResource("ui/Internetsurfing_Mainmenu_Style2.fxml"));
                root = (AnchorPane) fxmlLoader.load();
                scene = new Scene(root);
                stage.setScene(scene); 
            }                 
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        
    }
    
    public void styleBoxClicked(ActionEvent e)      //action when another style gets selected
    {
        if(style.isPressed())
        {
            resetLabel();
        }
        changeStyle(e);
    }
    
    public void setButtonSound()        //sets the volume of the button sound on or off
    {
        if(soundCheckBox.isSelected())
        {
            this.mediaPlayer.setVolume(100);
        }
        else
        {
            this.mediaPlayer.setVolume(0);
        }      
    }
    
    public void initBrowserPaths()
    {
        try
        {             
            browserInitializer.load(input);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void initFileInputStream()
    {
        try
        {
            input = new FileInputStream(System.getProperty("user.home")+"/InternetSurfing.properties");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }        
    }
    
    @FXML
    public void initialize() // initializes the class MainMenuController
    {          
        initFileInputStream();
        initBrowserPaths();    
        initSearchBrowser();
        initStyleComboBox();
        this.comboBox.getItems().addAll(browserList);
        this.comboBox.getSelectionModel().select(0);  
    }
}
