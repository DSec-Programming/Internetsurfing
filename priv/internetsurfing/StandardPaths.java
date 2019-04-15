package priv.internetsurfing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class StandardPaths
{
    private File file = new File(System.getProperty("user.home")+"/InternetSurfing.properties");
    
    private Properties props = new Properties();
    
    private FileOutputStream output;
    
    File firefoxPath = new File("C:/Program Files (x86)/Mozilla Firefox/firefox.exe");

    File chromePath = new File("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");

    File operaPath = new File("C:/Program Files/Opera/launcher.exe");

    File internetExplorerPath = new File("C:/Program Files/Internet Explorer/iexplore.exe");

    File torPath = new File("C:/Program Files/Tor Browser/Browser/firefox.exe");
    
    public void createFile()
    {
        try
        {
            if(file.exists())
            {
                System.out.println("properties data exists and gets loaded");
            }
            else
            {
            output = new FileOutputStream(file);           

            props.setProperty("Firefox", firefoxPath.toString());
            props.setProperty("Chrome", chromePath.toString());
            props.setProperty("Opera", operaPath.toString());
            props.setProperty("Internet Explorer", internetExplorerPath.toString());
            props.setProperty("Tor Browser", torPath.toString());
            props.store(output, null);
            output.close();
            System.out.println("properties data created");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
