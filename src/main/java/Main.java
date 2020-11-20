
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
        while(power*2 < denary){
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
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setBackground(new Background(new BackgroundImage(new Image("https://pbs.twimg.com/media/EKM9pjTVAAIudzZ.jpg",500,500,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        Text outputInfo = new Text("Output: ");
        root.add(outputInfo,0,7);
        Text output = new Text();
        root.add(output,1,7);
        outputInfo.setFont(Font.font("Comic Sans MS",20));
        outputInfo.setFill(Color.WHITE);
        output.setFont(Font.font("Comic Sans MS",20));
        output.setFill(Color.WHITE);

        TextField bTOdINPUT = new TextField();
        root.add(bTOdINPUT,0,1);
        Button bTOd = new Button("Binary to Denary");
        bTOd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String number = Integer.toString(binaryToDenary(bTOdINPUT.getText()));
                output.setText(number);
            }
        });



        root.add(bTOd,1,1);

        TextField bTOhINPUT = new TextField();
        root.add(bTOhINPUT,0,2);
        Button bTOh = new Button("Binary to Hex");
        root.add(bTOh,1,2);
        bTOh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                output.setText(binaryToHex(bTOhINPUT.getText()));
            }
        });


        TextField dTObINPUT = new TextField();
        root.add(dTObINPUT,0,3);
        Button dTOb = new Button("Denary to Binary");
        root.add(dTOb,1,3);
        dTOb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                output.setText(denaryToBinary(Integer.parseInt(dTObINPUT.getText())));
            }
        });

        TextField dTOhINPUT = new TextField();
        root.add(dTOhINPUT,0,4);
        Button dTOh = new Button("Denary to Hex");
        root.add(dTOh,1,4);
        dTOh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                output.setText(denaryToHex(Integer.parseInt(dTOhINPUT.getText())));
            }
        });

        TextField hTObINPUT = new TextField();
        root.add(hTObINPUT,0,5);
        Button hTOb = new Button("Hex to Binary");
        root.add(hTOb,1,5);
        hTOb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                output.setText(hexToBinary(hTObINPUT.getText()));
            }
        });

        TextField hTOdINPUT = new TextField();
        root.add(hTOdINPUT,0,6);
        Button hTOd = new Button("Hex to Denary");
        root.add(hTOd,1,6);
        hTOd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                output.setText(Integer.toString(hexToDenary(hTOdINPUT.getText())));
            }
        });




        stage.setScene(new Scene(root,500,500));
        stage.show();
    }
}