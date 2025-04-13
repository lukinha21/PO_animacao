package com.example.poanimacao;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Quick extends Application {
    AnchorPane pane;
    Button botao1, botao2;
    Label labelaux, label = new Label("Vetor Inicial"), descricao;
    Button[] vet;
    Label[] labels = new Label[23];

    final String verde = "-fx-background-color: #46c846;";
    final String azul = "-fx-background-color: #4596dc;";
    final String vermelho = "-fx-background-color: #d62626;";

    ImageView arrowView = new ImageView(new Image(getClass().getResourceAsStream("/arrow.png")));
    ImageView arrowView2 = new ImageView(new Image(getClass().getResourceAsStream("/arrow.png")));

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        pane = new AnchorPane();
        pane.setStyle("-fx-background-color: white;");
        Scene scene = new Scene(pane, 1400, 700);
        stage.setTitle("Metodo de ordenação");

        Button botao_inicio = new Button("Quick Sort Sem Pivô");
        botao_inicio.setLayoutX(50);
        botao_inicio.setLayoutY(100);
        botao_inicio.setOnAction(e -> quickSort());
        pane.getChildren().add(botao_inicio);

        descricao = criarLabel("", 50, 550, 20);
        label.setLayoutX(5);
        label.setLayoutY(210);
        pane.getChildren().addAll(descricao, label);

        configurarFlechas();
        configurarCodigo();
        configurarVetor();
        configurarAuxiliares();

        stage.setScene(scene);
        stage.show();
    }

    private void configurarFlechas() {
        for (ImageView arrow : new ImageView[]{arrowView, arrowView2}) {
            arrow.setFitWidth(20);
            arrow.setFitHeight(20);
            arrow.setLayoutX(10000);
            arrow.setLayoutY(10000);
            pane.getChildren().add(arrow);
        }
    }

    private void configurarCodigo() {
        String[] linhas = {
                "public void quickSort(){", "    quickSP(0, TL-);", "}",
                "quickSP(int ini, int fim){", "    int i=ini, j=fim,aux;", "    boolean flag=true;",
                "    while(i<j){", "         if(flag)", "              while(i<j && vet[i]<=vet[j])",
                "                   i++;", "         else", "              while(i<j && vet[i]<=vet[j])",
                "                   j--;", "         aux=vet[i];", "         vet[i]=vet[j];", "         vet[j]=aux;",
                "         flag=!flag;", "     }", "     if(ini<i-1)", "          quickSP(ini,i-1);",
                "     if(j+1<fim)", "          quickSP(j+1,fim);", "}"
        };
        for (int i = 0; i < linhas.length; i++) {
            labels[i] = criarLabel(linhas[i], 1000, 15 + i * 30, 14);
            pane.getChildren().add(labels[i]);
        }
    }

    private void configurarVetor() {
        vet = new Button[10];
        Set<Integer> numeros = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < vet.length; i++) {
            int numero;
            do {
                numero = random.nextInt(10);
            } while (numeros.contains(numero));
            numeros.add(numero);

            vet[i] = new Button(String.valueOf(numero));
            vet[i].setLayoutX(110 + i * 80);
            vet[i].setLayoutY(200);
            vet[i].setMinSize(40, 40);
            vet[i].setFont(new Font(14));
            pane.getChildren().add(vet[i]);
        }
    }

    private void configurarAuxiliares() {
        botao1 = criarBotao("", 400, 50);
        botao2 = criarBotao("", 500, 50);
        labelaux = criarLabel("", 465, 50, 14);
        pane.getChildren().addAll(botao1, botao2, labelaux);
    }

    private Button criarBotao(String texto, double x, double y) {
        Button btn = new Button(texto);
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setMinSize(40, 40);
        btn.setFont(new Font(14));
        return btn;
    }

    private Label criarLabel(String texto, double x, double y, int tamanhoFonte) {
        Label lbl = new Label(texto);
        lbl.setLayoutX(x);
        lbl.setLayoutY(y);
        lbl.setFont(new Font(tamanhoFonte));
        return lbl;
    }
    private void delay(Runnable r, int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.runLater(r);
    }

    public void quickSort() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                delay(() -> labels[1].setStyle(azul), 200);
                quickSP(0, vet.length - 1, 0);
                delay(() -> {
                    label.setText("Vetor Ordenado");
                    descricao.setText("O vetor está ordenado.");
                    botao1.setText("");
                    botao2.setText("");
                    pane.getChildren().removeAll(arrowView, arrowView2);
                    for (Button btn : vet) {
                        btn.setLayoutY(200);
                        btn.setStyle(verde);
                    }
                }, 500);
                return null;
            }
        };
        new Thread(task).start();
    }

    private void quickSP(int ini, int fim, int desloc) {
        int i = ini, j = fim;
        boolean flag = true;

        for (int k = i; k <= j; k++) {
            int finalK = k;
            delay(() -> vet[finalK].setLayoutY(vet[finalK].getLayoutY() + desloc), 200);
        }

        while (i < j) {
            if (flag) {
                while (i < j && Integer.parseInt(vet[i].getText()) <= Integer.parseInt(vet[j].getText())) {
                    int idxI = i++, idxJ = j;
                    delay(() -> {
                        setTextFx(botao1, vet[idxI].getText());
                        setTextFx(botao2, vet[idxJ].getText());
                        moveFx(arrowView, vet[idxI].getLayoutX() + 10, vet[idxI].getLayoutY() + 50);
                        moveFx(arrowView2, vet[idxJ].getLayoutX() + 10, vet[idxJ].getLayoutY() + 50);
                    }, 200);
                }
            } else {
                while (i < j && Integer.parseInt(vet[i].getText()) <= Integer.parseInt(vet[j].getText())) {
                    int idxI = i, idxJ = j--;
                    delay(() -> {
                        setTextFx(botao1, vet[idxJ].getText());
                        setTextFx(botao2, vet[idxI].getText());
                        moveFx(arrowView, vet[idxI].getLayoutX() + 10, vet[idxI].getLayoutY() + 50);
                        moveFx(arrowView2, vet[idxJ].getLayoutX() + 10, vet[idxJ].getLayoutY() + 50);
                    }, 200);
                }
            }

            if (i != j) animarTroca(i, j);
            Button aux = vet[i];
            vet[i] = vet[j];
            vet[j] = aux;

            flag = !flag;
        }

        if (ini < i - 1) quickSP(ini, i - 1, 50);
        if (j + 1 < fim) quickSP(j + 1, fim, 50);
    }


    private int avancar(int i, int j, boolean ladoEsquerdo) {
        highlightLabel(ladoEsquerdo ? 8 : 11);
        while (i < j && comparar(i, j)) {
            int idx = ladoEsquerdo ? i++ : j--;
            int finalI = i, finalJ = j;
            delay(() -> {
                moverSeta(arrowView, vet[finalI]);
                moverSeta(arrowView2, vet[finalJ]);
                atualizarBotoes(finalI, finalJ);
            }, 200);
        }
        return ladoEsquerdo ? i : j;
    }

    private void animarTroca(int i, int j) {
        setTextFx(descricao, "Realiza a permutação entre os valores não ordenados entre sí.");
        highlightLabel(13, 14, 15);

        // Anima subida/descida
        for (int l = 0; l < 10; l++) {
            int ii = i, jj = j;
            delay(() -> {
                vet[ii].setLayoutY(vet[ii].getLayoutY() - 5);
                vet[jj].setLayoutY(vet[jj].getLayoutY() + 5);
            }, 10);
        }

        // Anima troca lateral
        for (int l = 0; l < Math.abs(j - i) * 80 / 5; l++) {
            int ii = i, jj = j;
            delay(() -> {
                vet[ii].setLayoutX(vet[ii].getLayoutX() + 5);
                vet[jj].setLayoutX(vet[jj].getLayoutX() - 5);
            }, 10);
        }

        // Volta pra posição original vertical
        for (int l = 0; l < 10; l++) {
            int ii = i, jj = j;
            delay(() -> {
                vet[ii].setLayoutY(vet[ii].getLayoutY() + 5);
                vet[jj].setLayoutY(vet[jj].getLayoutY() - 5);
            }, 10);
        }

        delay(() -> {
            setStyleFx(botao1, "");
            setStyleFx(botao2, "");
        }, 100);
    }


    private void moverParaBaixo(int i, int j, int desloc) {
        for (int k = i; k <= j; k++) {
            int finalK = k;
            delay(() -> vet[finalK].setLayoutY(vet[finalK].getLayoutY() + desloc), 200);
        }
    }

    private boolean comparar(int i, int j) {
        return Integer.parseInt(vet[i].getText()) <= Integer.parseInt(vet[j].getText());
    }

    private void highlightLabel(int... indices) {
        resetAllLabels();
        for (int index : indices) labels[index].setStyle(azul);
    }

    private void resetAllLabels() {
        for (Label lbl : labels) lbl.setStyle("");
    }

    private void resetLabels(int... indices) {
        for (int index : indices) labels[index].setStyle("");
    }

    private void moverSeta(ImageView seta, Button btn) {
        seta.setLayoutX(btn.getLayoutX() + 10);
        seta.setLayoutY(btn.getLayoutY() + 50);
    }

    private void atualizarBotoes(int i, int j) {
        botao1.setText(vet[i].getText());
        botao2.setText(vet[j].getText());
    }
    private void setTextFx(Labeled node, String text) {
        Platform.runLater(() -> node.setText(text));
    }

    private void setStyleFx(Labeled node, String style) {
        Platform.runLater(() -> node.setStyle(style));
    }

    private void moveFx(javafx.scene.Node node, double x, double y) {
        Platform.runLater(() -> {
            node.setLayoutX(x);
            node.setLayoutY(y);
        });
    }
}