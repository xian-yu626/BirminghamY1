package sample.utils;

import javafx.fxml.Initializable;


public interface ModelView extends Initializable {
    void prepare() throws Exception;
    void refreshView() throws Exception;
}
