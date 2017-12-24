/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourbot;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 * FXML Controller class
 *
 * @author User
 */
public class ChatController implements Initializable {
   private final String DB_USERNAME = "shakur999";
   private final String DB_PASSWORD = "pijonshakur999";
   private final String DB_HOSTNAME = "localhost";
   private final String DB_DBNAME = "chatbot";
   private final String DB_URL = "jdbc:mysql://"+DB_HOSTNAME+"/"+DB_DBNAME;
    @FXML
    private TextField textField;
    @FXML
    private Button sendButton;
    
   @FXML
    private ImageView imageView1;
    @FXML
    private TableView<chatRecord> tableView;
    private ObservableList<chatRecord>recordsList;
    @FXML
    private TableColumn<chatRecord, String> pijonTable;
    @FXML
    private TableColumn<chatRecord, String> meTable;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("src/ss.jpg");
        Image image = new Image(file.toURI().toString());
        imageView1.setImage(image);
        recordsList = FXCollections.observableArrayList();
        recordsList.addAll(getRecordList());
        textField.setText("type here...");
        tableView.setItems(recordsList);
       pijonTable.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getPijon()));
       meTable.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getUser()));
    }    
    
    private void insertChatRecord(chatRecord record){
        String user = "me ->"+record.getUser();
        String pijon = "pijon ->"+record.getPijon();
        String query = "insert into chatrecord values('"+user+"','"+pijon+"');";
        try{
            Connection connection = DriverManager.getConnection 
                     (DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);}
        catch(SQLException sqle){
            System.err.println(sqle);
        }
    }
    
     private ArrayList<chatRecord>getRecordList(){
            ArrayList<chatRecord>recordList = new ArrayList<>();
            try{
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                Statement statement = connection.createStatement();
                String query = "select * from chatrecord;";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    String user = resultSet.getString("user");
                    String pijon = resultSet.getString("pijon");
                    chatRecord record = new chatRecord(user, pijon);
                    recordList.add(record);
                    
                }
                
            }catch(SQLException sqle){
                System.err.println("sqle");}
            return recordList;
        }
    

   @FXML
    private void handleSendButtonAction(ActionEvent event) {
        ArrayList<String>questionList = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            String query = "select * from respons;";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String questions = resultSet.getString("question");
                questionList.add(questions);
            }
           }catch(SQLException sqle)
           {System.err.println("sqle");}
        
         ArrayList<String>responseList;
            responseList = new ArrayList<>();
            try{
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                Statement statement = connection.createStatement();
                String query = "select * from respons;";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    String responses = resultSet.getString("response");
                    responseList.add(responses);
                    }
                }catch(SQLException sqle){
                    System.err.println("Ssqle");}
            
          
           
            String user =  textField.getText();
            String pijon = "I dont understand";
            for(int i =0; i<questionList.size(); i++)
            { if(user.equals(questionList.get(i)))
                 {pijon = responseList.get(i);}
              }
             
        chatRecord record = new chatRecord(user,pijon);
        insertChatRecord(record);
        System.out.println(record);
        
        
       recordsList.clear();
       recordsList.addAll(getRecordList());
       
       tableView.setItems(recordsList);
       pijonTable.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getPijon()));
       meTable.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getUser()));
       textField.clear();
    }


    
    
}
