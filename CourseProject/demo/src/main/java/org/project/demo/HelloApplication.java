package org.project.demo;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.input.*;
import javafx.scene.image.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.File;

public class HelloApplication extends Application {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    @Override
    public void start(Stage primaryStage) {
        Box box = new Box(50, 50, 50);

        Group group = getGroup(box);
        box.setMaterial(getPhongMaterial());
        Camera camera = getCamera();
        Scene scene = getScene(group, camera);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case Q:
                    box.translateZProperty().set(box.getTranslateZ() + 10);
                    break;
                case E:
                    box.translateZProperty().set(box.getTranslateZ() - 10);
                    break;
                case W:
                    box.translateYProperty().set(box.getTranslateY() - 10);
                    break;
                case S:
                    box.translateYProperty().set(box.getTranslateY() + 10);
                    break;
                case A:
                    box.translateXProperty().set(box.getTranslateX() - 10);
                    break;
                case D:
                    box.translateXProperty().set(box.getTranslateX() + 10);
                    break;
                case Z:
                    box.setRotationAxis(Rotate.Z_AXIS);
                    box.setRotate(box.getRotate() + 10);
                    break;
                case X:
                    box.setRotationAxis(Rotate.X_AXIS);
                    box.setRotate(box.getRotate() + 10);
                    break;
                case C:
                    box.setRotationAxis(Rotate.Y_AXIS);
                    box.setRotate(box.getRotate() + 10);
                    break;
            }
        });

        primaryStage.setTitle("3D Sphere");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private PhongMaterial getPhongMaterial() {
        // Создаем градиент для текстуры
        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop[]{
                new Stop(0, Color.TRANSPARENT),
                new Stop(0.5, Color.BLACK),
                new Stop(1, Color.TRANSPARENT)
        });

        // Создаем изображение из градиента
        int textureWidth = 100;
        int textureHeight = 100;
        WritableImage image = new WritableImage(textureWidth, textureHeight);
        PixelWriter pixelWriter = image.getPixelWriter();
        for (int y = 0; y < textureHeight; y++) {
            for (int x = 0; x < textureWidth; x++) {
                double fraction = (double) x / textureWidth;
                Color color = gradient.getStops().get(0).getColor().interpolate(gradient.getStops().get(2).getColor(), fraction);
                pixelWriter.setColor(x, y, color);
            }
        }

        // Создаем материал
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.GRAY);
        material.setSpecularColor(Color.BLACK);
        material.setSpecularPower(10);
        material.setSelfIlluminationMap(image); // Применяем изображение в качестве самоосвещенной текстуры
        return material;
    }

    private Camera getCamera() {
        Camera camera = new PerspectiveCamera(true);
        camera.setFarClip(50000.0);
        camera.setTranslateZ(-1000);
        return camera;
    }

    private Scene getScene(Group group, Camera camera) {
        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);
        return scene;
    }

    private Group getGroup(Shape3D shape3D) {
        Group group = new Group();
        group.getChildren().add(shape3D);
        return group;
    }

    private int[] getSize() {
        File file = new File()
    }

    public static void main(String[] args) {
        launch(args);
    }
}