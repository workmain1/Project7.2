package com.example.project7_2;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part3 extends Application {

    private static final int NUM_BARS = 50;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private static final int BAR_WIDTH = WIDTH / NUM_BARS;

    private List<Integer> data = new ArrayList<>();
    private List<Rectangle> bars = new ArrayList<>();
    private Pane pane = new Pane();

    @Override
    public void start(Stage stage) {
        generateData();
        drawBars();

        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Bubble Sort Visualization");
        stage.show();

        runBubbleSort();
    }

    private void generateData() {
        for (int i = 1; i <= NUM_BARS; i++) {
            data.add(i);
        }
        Collections.shuffle(data);
    }

    private void drawBars() {
        pane.getChildren().clear();
        bars.clear();

        for (int i = 0; i < NUM_BARS; i++) {
            int value = data.get(i);
            double height = (HEIGHT - 20) * ((double) value / NUM_BARS);
            Rectangle rect = new Rectangle(i * BAR_WIDTH, HEIGHT - height, BAR_WIDTH - 2, height);
            rect.setFill(Color.BLUE);
            bars.add(rect);
            pane.getChildren().add(rect);
        }
    }

    private void runBubbleSort() {
        Timeline timeline = new Timeline();
        List<KeyFrame> frames = new ArrayList<>();
        Duration frameDuration = Duration.millis(50);

        int n = data.size();
        int frameCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                int finalJ = j;
                int finalI = i;

                KeyFrame frame = new KeyFrame(frameDuration.multiply(frameCount), e -> {
                    highlightBars(finalJ, finalJ + 1, Color.RED);

                    if (data.get(finalJ) > data.get(finalJ + 1)) {
                        Collections.swap(data, finalJ, finalJ + 1);
                        drawBars();
                        highlightBars(finalJ, finalJ + 1, Color.GREEN);
                    }
                });
                frames.add(frame);
                frameCount++;
            }
        }

        timeline.getKeyFrames().addAll(frames);
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void highlightBars(int i, int j, Color color) {
        for (int k = 0; k < NUM_BARS; k++) {
            bars.get(k).setFill(Color.BLUE);
        }
        bars.get(i).setFill(color);
        bars.get(j).setFill(color);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
