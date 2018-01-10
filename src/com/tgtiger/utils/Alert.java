package com.tgtiger.utils;

public class Alert {
    public void alert(String content) {
        javafx.scene.control.Alert information = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION,content);
        information.setTitle("Information");         //设置标题，不设置默认标题为本地语言的information
        information.setHeaderText("温馨提示：");    //设置头标题，默认标题为本地语言的information
        information.showAndWait();   //显示弹窗，同时后续代码等挂起
    }


    public void error(String content) {


        javafx.scene.control.Alert error = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR, content);
        error.showAndWait();
//
//
    }
}
