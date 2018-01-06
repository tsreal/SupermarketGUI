package com.tgtiger.Controller;

import com.tgtiger.FXMLTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogLoginErrController implements Initializable {


    private FXMLTest application;

    @FXML
    private TextField text;


    public void setApp(FXMLTest application) {
        this.application = application;
    }

    public void setLabel(String info) {
        this.text.setText(info);
    }


    @FXML
    private void OUT_M(ActionEvent event) {
        application.useroutmain();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }
}
