package es.iesoretania;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class OreDefense extends ScreenAdapter {
    enum VerticalMovement {UP, NONE, DOWN};
    enum HorizontalMovement {LEFT, NONE, RIGHT};
    Nave.HorizontalMovement horizontalMovement;
    Nave.VerticalMovement verticalMovement;
    private final ManejadorPantallas game;
    private int dificultad;
    static Stage stage;
    OrthographicCamera camera;
    Nave nave;
    public static Meteorito meteoritos[];


    public OreDefense(ManejadorPantallas game) {
        this.dificultad=ManejadorPantallas.dificultad;
        this.game=game;
        stage = new Stage();
        nave = new Nave();
        stage.addActor(nave);

        //Gdx.input.setInputProcessor(stage);
        stage.setKeyboardFocus(nave);
        // Preparar la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        // Crear viewport para el stage asociado a la cámara
        Viewport viewport = new ScreenViewport(camera);
        stage.setViewport(viewport);
        meteoritos = new Meteorito[10*dificultad];
        for(int i=0;i<meteoritos.length;i++){
            meteoritos[i] = new Meteorito();
            stage.addActor(meteoritos[i]);
        }

        Actor score = new Manager(nave,game);
        stage.addActor(score);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // Borrar pantalla
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Permitir que los actores hagan sus tareas
        stage.act(Gdx.graphics.getDeltaTime());
        // Dibujar el stage
        stage.draw();
        nave.toFront();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

    public static Stage getStage() {
        return stage;
    }
}