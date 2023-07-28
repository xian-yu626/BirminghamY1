package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.Main;
import sample.utils.ModelView;
import sample.utils.JabberMessageUtils;
import sample.utils.SocketUtils;
import server.Timeline;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Index implements ModelView {


	@FXML private TableView<Timeline> timeTable;
	@FXML private TableColumn<Timeline, String> username;
	@FXML private TableColumn<Timeline, String > jabtext;
	@FXML private TableColumn<Timeline, String > jabid;
	@FXML private TableColumn<Timeline, String > jabcount;
	@FXML private Button B1;  //Like
	@FXML private Button B2;  //Sign out
	@FXML private Button B3;  //Follow
	@FXML private Button B4;  //Post
	@FXML private Label label;
	public ObservableList<Timeline> timelineList;

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
		getTimeline();
	}
	@Override
	public void refreshView(){
		username.setCellValueFactory(new PropertyValueFactory<Timeline, String>("username"));
		jabtext.setCellValueFactory(new PropertyValueFactory<Timeline, String>("jabtext"));
		//jabid.setCellValueFactory(new PropertyValueFactory<Timeline, String>("jabid"));
		jabcount.setCellValueFactory(new PropertyValueFactory<Timeline, String>("jabcount"));
		//add data to the table
		timeTable.setItems(timelineList);
		//for editing a column, each column must be set up
		timeTable.setEditable(true);
		username.setCellFactory(TextFieldTableCell.forTableColumn());//creates the textfield
	}

	public void getTimeline() throws IOException, ClassNotFoundException 
	{
		String request = "timeline";
		ArrayList<ArrayList<String>> timelineOfUserEx = JabberMessageUtils.sendMessageForData(request);
		ArrayList<Timeline> timeArr = new ArrayList<>();
		for (int i = 0; i < timelineOfUserEx.size(); i++) {
			ArrayList<String> timeLines = timelineOfUserEx.get(i);
			Timeline timeData = new Timeline(timeLines.get(0), timeLines.get(1),timeLines.get(2),timeLines.get(3) );
			timeArr.add(timeData);
		}
		timelineList=FXCollections.observableArrayList(timeArr);
		timelineList.forEach(System.out::println);
	}
	public void signOut(ActionEvent event) throws IOException  
	{
		String request = "signout";
		JabberMessageUtils.sendMessageForVoid(request);
		SocketUtils.ois.close();
		SocketUtils.oos.close();
		SocketUtils.socketConnection.close();
		System.exit(0);
	}
	public void like(ActionEvent event) throws Exception  
	{
		Timeline selectedItem = timeTable.getSelectionModel().getSelectedItem();
		if (selectedItem == null){
			label.setText("please select one to like");
		}else {
			String request = "like "+selectedItem.getJabid();
			String message = JabberMessageUtils.sendMessage(request);
			if (message!=null) {
				label.setText(message);
			}
			new Main().refreshIndex();
		}

	}
	public void followView(ActionEvent event) throws Exception  
	{
		new Main().gotoFollow();
	}
	public void postView(ActionEvent event) throws Exception  
	{
		new Main().gotoPost();
	}
}

