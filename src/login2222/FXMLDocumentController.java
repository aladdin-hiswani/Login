/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login2222;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import login2222.mysqlConnect;

/**
 *
 * @author alaah
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button log;

    @FXML
    private Button signUp;

    @FXML
    private AnchorPane LogPane;

    @FXML
    private TextField logTxt;

    @FXML
    private PasswordField logPass;

    @FXML
    private ComboBox LogChose;

    @FXML
    private Button LogBtn;

    @FXML
    private AnchorPane signUpPane;

    @FXML
    private ComboBox signChose;

    @FXML
    private TextField signEmail;

    @FXML
    private TextField signPass;

    @FXML
    private TextField signUser;

    @FXML
    private Button SignUpBtn;

    @FXML
    private ImageView img;

    private Connection conn = null;
    private ResultSet re = null;
    PreparedStatement pst = null;

    @FXML
    void loginPane(ActionEvent event) {

        LogPane.setVisible(true);
        signUpPane.setVisible(false);
    }

    @FXML
    void signUpPane(ActionEvent event) {
        LogPane.setVisible(false);
        signUpPane.setVisible(true);
    }

    @FXML
    void login(ActionEvent event) throws Exception {
        conn = mysqlConnect.connectDB();
        String sql = "Select * from user where username = ? and password = ? and type = ?"; //إشارة الاستفهام مرتبطة بـ PreparedStatement وعن طريقها يتم الاستعلام
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, logTxt.getText());
            pst.setString(2, logPass.getText());
            pst.setString(3, LogChose.getValue().toString());
            re = pst.executeQuery();
            if (re.next()) {
                JOptionPane.showMessageDialog(null, "Username and Password are correct");
            } else {
                JOptionPane.showMessageDialog(null, "Username and Password are not correct");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @FXML
    void signUp(ActionEvent event) throws Exception {
        conn = mysqlConnect.connectDB();
        String sql = "Select * from user where username = ? and password = ? and type = ?";
        try {
            signUser.setText(sql);

            pst = conn.prepareStatement(sql);
            pst.setString(1, logTxt.getText());
            pst.setString(2, logPass.getText());
            pst.setString(3, LogChose.getValue().toString());
            re = pst.executeQuery();
            if (re.next()) {

                LogBtn.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("Pane2.fxml"));

                Stage mainstage = new Stage();
                Scene scene = new Scene(root);
                mainstage.setScene(scene);
                mainstage.show();

            } else {
                JOptionPane.showMessageDialog(null, "Username and Password are not correct");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void add_users(ActionEvent event) {
        conn = mysqlConnect.connectDB();
        // String sql = "INSERT INTO `login2222`.`user` (`username`, `password`, `email`, `type`) VALUES ( '"+signUser.getText()+"', '"+signPass.getText()+"', '"+signEmail.getText()+"', '"+signUser.getText()+"');";
        String sql = "INSERT INTO `login2222`.`user` (`username`, `password`, `email`, `type`) VALUES (?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, signUser.getText());
            pst.setString(2, signPass.getText());
            pst.setString(3, signEmail.getText());
            pst.setString(4, signChose.getValue().toString());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Saved");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "not saved" + e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LogChose.getItems().addAll("Admin", "Client", "Cashier", "Storekeeper");
        signChose.getItems().addAll("Admin", "Client", "Cashier", "Storekeeper");

    }

}
