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
import sample.Bridges.Bridge;
import sample.Items.FireHeroes;
import sample.Items.Heroes;
import sample.Items.Knights;
import sample.Items.WaterHeroes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TableController {
    private ArrayList<Heroes> heroesList = new ArrayList<Heroes>();
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
    private Button refBtn;

    @FXML
    private Button sortBtn;

    @FXML
    private void initialize() {
        heroesData = Bridge.getHeroesDataList();
    //    heroesList = Bridge.getHeroesArrayList();

        ShowInfo();
        name.setCellValueFactory(new PropertyValueFactory<Heroes, Heroes.Names>("name"));
        rarity.setCellValueFactory(new PropertyValueFactory<Heroes, Integer>("rarity"));
        dam.setCellValueFactory(new PropertyValueFactory<Heroes, Heroes.damageAmplification>("dam"));
        heal.setCellValueFactory(new PropertyValueFactory<Heroes, Heroes.healing>("heal"));
        element.setCellValueFactory(new PropertyValueFactory<Heroes, String>("element"));
        skill.setCellValueFactory(new PropertyValueFactory<Heroes, String>("skill"));
        table.setItems(heroesData);



        refBtn.setOnAction((event -> {
            Ref();
        }));

        sortBtn.setOnAction((event -> {
            heroesData = Bridge.sort();
            table.setItems(heroesData);
        }));

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


    private void Ref(){
        //this.heroesData.clear();
        //this.heroesList.clear();
        Bridge.cleaner();
        for(var i=0;i<15;++i)
        {
            switch(0 + (int)(Math.random() * ((2 - 0) + 1)))
            {
                case 0:
                    WaterHeroes whero = WaterHeroes.Generate();
                //    this.heroesList.add(whero);
                //    heroesData.add(whero);

                    //Bridge.setHeroesArrayList(whero);
                    Bridge.setHeroesDataList(whero);
                    break;
                case 1:
                    FireHeroes fhero = FireHeroes.Generate();
                //    this.heroesList.add(fhero);
                //    heroesData.add(new FireHeroes(fhero.getRarity(),
                //            fhero.getDam(), fhero.getHeal(), fhero.getName(), fhero.getElement(), fhero.getSkill()));
                    //Bridge.setHeroesArrayList(fhero);
                    Bridge.setHeroesDataList(fhero);
                    break;
                case 2:
                    Knights khero = Knights.Generate();
                //    this.heroesList.add(khero);
                //    heroesData.add(khero);
                    //Bridge.setHeroesArrayList(khero);
                    Bridge.setHeroesDataList(khero);

                    break;
            }
        }

        heroesData = Bridge.getHeroesDataList();
    //    heroesList = Bridge.getHeroesArrayList();
        table.setItems(heroesData);
        ShowInfo();
    }


    private void ShowInfo() {
        int FireHeroesCount = 0;
        int WaterHeroesCount = 0;
        int KnightsCount = 0;

        for (var heroes : this.heroesData)
        {
            if (heroes instanceof WaterHeroes)
            {
                WaterHeroesCount += 1;
            }
            else if (heroes instanceof FireHeroes)
            {
                FireHeroesCount += 1;
            }
            else if (heroes instanceof Knights)
            {
                KnightsCount += 1;
            }
        }


        infoLabel.setText("Герои огня\t\tГерои воды\t\tВоины\n"+
                "\t" + FireHeroesCount + "\t\t\t\t" + WaterHeroesCount + "\t\t\t" + KnightsCount);
    }
}