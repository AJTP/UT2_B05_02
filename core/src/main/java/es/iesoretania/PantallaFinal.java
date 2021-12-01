package es.iesoretania;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PantallaFinal extends ScreenAdapter {
    private final ManejadorPantallas game;
    private Stage stage;

    public PantallaFinal(ManejadorPantallas game){
        this.game=game;
        stage = new Stage(new ScreenViewport());
        //Título
        Label title = new Label("Puntuacion: "+Manager.score,game.gameSkin,"dark");
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight() * 1 / 3);
        title.setWidth(Gdx.graphics.getWidth());
        stage.addActor(title);
        //Botón salir
        TextButton exitButton = new TextButton("Menu Principal", game.gameSkin);
        exitButton.setWidth(Gdx.graphics.getWidth() / 2);
        exitButton.setPosition(
                Gdx.graphics.getWidth() / 2 - exitButton.getWidth() / 2,
                Gdx.graphics.getHeight() / 6 - exitButton.getHeight() / 2
        );
        exitButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MenuPrincipal(game));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(exitButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
