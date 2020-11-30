
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.apache.commons.lang3.ArrayUtils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    public static Integer binaryToDenary(String binary){
        int denary = 0;
        for(int i=binary.length(); i!=0;i--){
            int bit = Integer.parseInt(String.valueOf(binary.charAt(i-1)));
            denary += bit*(Math.pow(2,binary.length()-i));
        }
        return denary;
    }
    public static String denaryToBinary(int denary){
        String binary = "";
        int power = 2;
        System.out.println(denary);
        while(power*2 <= denary){
            System.out.println(power);
            power *= 2;
        }
        while(power > 0){
            if(power<= denary){
                binary += "1";
                denary -= power;
            }else{
                binary +="0";
            }
            power /=2;
        }
        while(binary.length()%4 != 0){
            binary = "0" + binary;
        }
        return binary;
    }

    public static String binaryToHex(String binary){
        String hex = "";
        while(binary.length()%4 != 0){
            binary = "0" + binary;
        }
        String[] nibbles = {"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};
        String[] hexValues = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        for(int i=0;i<binary.length();i+=4){
            String nibble = binary.substring(i,i+4);
            hex+= hexValues[binaryToDenary(nibble)];
        }

        return hex;
    }

    public static String hexToBinary(String hex){
        String binary = "";
        String[] nibbles = {"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};
        String[] hexValues = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        for(int i=0;i<hex.length();i++){
            binary += nibbles[ArrayUtils.indexOf(hexValues,hex.substring(i,i+1))];
        }
        return binary;
    }

    public static Integer hexToDenary(String hex){
        String binary = hexToBinary(hex);
        int denary = binaryToDenary(binary);
        return denary;
    }

    public static String denaryToHex(int denary){
        String binary = denaryToBinary(denary);
        String hex = binaryToHex(binary);
        return hex;
    }


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Number Conversion");
        stage.getIcons().add(new Image("https://pbs.twimg.com/media/EKM9pjTVAAIudzZ.jpg"));
        GridPane root = new GridPane();
        GridPane buttons = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.setHgap(10);
        buttons.setVgap(10);
        buttons.getStylesheets().add("stylesheet.css");
        root.setBackground(new Background(new BackgroundImage(new Image("https://pbs.twimg.com/media/EKM9pjTVAAIudzZ.jpg",500,500,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        Text inputInfo = new Text("Input: ");
            inputInfo.setFont(Font.font("Comic Sans MS",20));
            root.add(inputInfo,0,0);
        TextField input = new TextField();
            input.setFont(Font.font("Comic Sans MS",20));
            root.add(input,1,0);
        Text outputInfo = new Text("Output: ");
            outputInfo.setFont(Font.font("Comic Sans MS",20));
            root.add(outputInfo,0,1);
        TextField output = new TextField();
            output.setFont(Font.font("Comic Sans MS",20));
            root.add(output,1,1);


        Button bTOd = new Button("Binary to Denary");
        buttons.add(bTOd,0,0);
        bTOd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String number = Integer.toString(binaryToDenary(input.getText()));
                output.setText(number);
            }
        });

        Button bTOh = new Button("Binary to Hex");
        buttons.add(bTOh,1,0);
        bTOh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                output.setText(binaryToHex(input.getText()));
            }
        });



        Button dTOb = new Button("Denary to Binary");
        buttons.add(dTOb,0,1);
        dTOb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                output.setText(denaryToBinary(Integer.parseInt(input.getText())));
            }
        });


        Button dTOh = new Button("Denary to Hex");
        buttons.add(dTOh,1,1);
        dTOh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                output.setText(denaryToHex(Integer.parseInt(input.getText())));
            }
        });


        Button hTOb = new Button("Hex to Binary");
        buttons.add(hTOb,0,2);
        hTOb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                output.setText(hexToBinary(input.getText()));
            }
        });


        Button hTOd = new Button("Hex to Denary");
        buttons.add(hTOd,1,2);
        hTOd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                output.setText(Integer.toString(hexToDenary(input.getText())));
            }
        });

        root.add(buttons,1,3);


        stage.setScene(new Scene(root,500,500));
        stage.show();
    }
}