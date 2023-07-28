package sample.utils;


import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Stage Manage Class 
public class StageManager {
    
    //save all Stage object
    private Map<String, Stage> stageMap = new HashMap<>();

    public void addStage(String name, Stage stage){
        stageMap.put(name, stage);
    }

    public Stage getStage(String name){
        return stageMap.get(name);
    }

    public void closeStage(String name){
        stageMap.get(name).close();
    }

   //Realize the jump of Stage, jump from currentStage to targetStage
    public void jump(String currentStageName, String targetStageName){
        Stage stage = stageMap.get(currentStageName);
        if (stage!=null){
            stage.close();
        }
        stageMap.get(targetStageName).show();
    }

    public void jump2(String currentStageName, String targetStageName){
        Stage stage = stageMap.get(currentStageName);
        Stage stage1 = stageMap.get(currentStageName+"1");
        if (stage!=null){
            stage.close();
        }
        if (stage1!=null){
            stage1.close();
        }
        stageMap.get(targetStageName).show();
    }
    public void close( String... stageNames){
        Arrays.stream(stageNames).forEach(
                stageName->{
                    Stage stage = stageMap.get(stageName);
                    if (stage!=null){
                        stage.close();
                    }
                }
        );
    }

    public void show(String targetStageName){
        Stage stage = stageMap.get(targetStageName);
        if (stage!=null){
            stage.show();
        }
    }
    
    public void release(String name){
        stageMap.remove(name);
    }
}
