package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.Main;
import sample.utils.JabberMessageUtils;
import sample.utils.ModelView;
import server.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Post implements ModelView {
	@FXML
	private TextField T1;  //post content
	@FXML private Button B1;  //post
	@FXML private Button B2;  //backindex
	@FXML
	private Label label;

	@Override
	public void prepare() throws Exception {

	}

	@Override
	public void refreshView() throws Exception {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void post(ActionEvent event) throws IOException, ClassNotFoundException //RQ3
	{
		String jabtext = T1.getText();
		String request = "post " + jabtext;
		String r = JabberMessageUtils.sendMessage(request);
		System.out.println("post message " + r);
	
		if (r.equalsIgnoreCase("posted")) {
			label.setText("posted");
		}
	}
	public void backIndex(ActionEvent event) throws Exception  
	{

		new Main().gotoIndexFromPost();
	}
}

