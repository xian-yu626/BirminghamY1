package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.Main;
import sample.utils.JabberMessageUtils;
import sample.utils.ModelView;
import sample.utils.SocketUtils;
import server.Timeline;
import server.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Follow implements ModelView {


	@FXML private TableView<User> userTable;
	@FXML private TableColumn<User, String> username;
//	@FXML private TableColumn<Timeline, String > jabtext;
//	@FXML private TableColumn<Timeline, String > jabid;
//	@FXML private TableColumn<Timeline, String > jabcount;
	@FXML private Button B1;  //Follow
	@FXML private Label label;
	public ObservableList<User> userList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			prepare();
			refreshView();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void prepare() throws Exception {
		getUsers();
	}
	@Override
	public void refreshView(){
		username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		//add data to the table
		userTable.setItems(userList);
		//for editing a column, each column must be set up
		userTable.setEditable(true);
		username.setCellFactory(TextFieldTableCell.forTableColumn());//creates the textfield
	}


	public void getUsers() throws IOException, ClassNotFoundException 
	{
		String request = "users";
		ArrayList<ArrayList<String>> users = JabberMessageUtils.sendMessageForData(request);
		ArrayList<User> userArr = new ArrayList<>();
		for (int i = 0; i < users.size(); i++) {
			ArrayList<String> userList = users.get(i);
			User user = new User(userList.get(0));
			userArr.add(user);
		}
		userList=FXCollections.observableArrayList(userArr);
		userList.forEach(System.out::println);
	}

	public void follow(ActionEvent event) throws Exception 
	{
		User user = userTable.getSelectionModel().getSelectedItem();
		if (user==null){
			label.setText("please select one");
		}else {
			String request = "follow "+user.getUsername();
			String message = JabberMessageUtils.sendMessage(request);
			if (message!=null) {
				label.setText(message);
			}
			new Main().gotoIndexFromFollow();
		}
	}
	public void backIndex(ActionEvent event) throws Exception  
	{

		new Main().gotoIndexFromFollow();
	}
}

