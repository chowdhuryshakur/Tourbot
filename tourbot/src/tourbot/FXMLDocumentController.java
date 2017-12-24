/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourbot;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button startChat;
    @FXML
    private ImageView imageView;
    
   

        
        

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("src/tt.png");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }    

    @FXML
    private void handleStartChatActionButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("chat.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Pijon the tourbot");
        stage.show();
    }
    
}
