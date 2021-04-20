package sample.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Logic.Bridge;
import sample.Items.FireHeroes;
import sample.Items.Heroes;
import sample.Items.Knights;
import sample.Items.WaterHeroes;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class TableController {

    private ObservableList<Heroes> heroesData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button retBtn;

    @FXML
    private Label infoLabel;

    @FXML
    private TableView<Heroes> table;

    @FXML
    private TableColumn<Heroes, Heroes.Names> name;

    @FXML
    private TableColumn<Heroes, Integer> rarity;

    @FXML
    private TableColumn<?, ?> res;

    @FXML
    private TableColumn<Heroes, Heroes.damageAmplification> dam;

    @FXML
    private TableColumn<Heroes, Heroes.healing> heal;

    @FXML
    private TableColumn<Heroes, String> element;

    @FXML
    private TableColumn<Heroes, String> skill;


    @FXML
    private Button tableWindow;

    @FXML
    private Button workWindow;



    @FXML
    private void initialize() {
        heroesData = Bridge.getItems();
        System.out.println(heroesData);


        name.setCellValueFactory(new PropertyValueFactory<Heroes, Heroes.Names>("name"));
        rarity.setCellValueFactory(new PropertyValueFactory<Heroes, Integer>("rarity"));
        dam.setCellValueFactory(new PropertyValueFactory<Heroes, Heroes.damageAmplification>("dam"));
        heal.setCellValueFactory(new PropertyValueFactory<Heroes, Heroes.healing>("heal"));
        element.setCellValueFactory(new PropertyValueFactory<Heroes, String>("element"));
        skill.setCellValueFactory(new PropertyValueFactory<Heroes, String>("skill"));
        table.setItems(heroesData);




        workWindow.setOnAction(event -> {
            Stage stage = (Stage) workWindow.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxms/clicker.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("ClickerMenu");
            stage.setResizable(false);
            stage.show();
        });

        retBtn.setOnAction((event -> {
            Stage stage = (Stage) retBtn.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxms/sample.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            stage = new Stage();
            stage.setTitle("Lototron");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        }));
    }



}
