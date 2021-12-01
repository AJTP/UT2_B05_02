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

public class MenuPrincipal extends ScreenAdapter {
    private final ManejadorPantallas game;
    private Stage stage;

    public MenuPrincipal(ManejadorPantallas game){
        this.game=game;
        stage = new Stage(new ScreenViewport());

        //Botón Fácil
        TextButton playButton = new TextButton("Facil", game.gameSkin);
        playButton.setWidth(Gdx.graphics.getWidth() / 2);
        playButton.setPosition(
                Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2,
                Gdx.graphics.getHeight() / 1 - playButton.getHeight() - 10
        );
        playButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ManejadorPantallas.dificultad = 1;
                game.setScreen(new OreDefense(game));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(playButton);
        //Botón Medio
        TextButton playButtonMedio = new TextButton("Medio", game.gameSkin);
        playButtonMedio.setWidth(Gdx.graphics.getWidth() / 2);
        playButtonMedio.setPosition(
                Gdx.graphics.getWidth() / 2 - playButtonMedio.getWidth() / 2,
                Gdx.graphics.getHeight() / 1.5f - playButtonMedio.getHeight() / 1.5f
        );
        playButtonMedio.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ManejadorPantallas.dificultad = 2;
                game.setScreen(new OreDefense(game));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(playButtonMedio);

        //Botón Difícil
        TextButton playButtonDificil = new TextButton("Dificil", game.gameSkin);
        playButtonDificil.setWidth(Gdx.graphics.getWidth() / 2);
        playButtonDificil.setPosition(
                Gdx.graphics.getWidth() / 2 - playButtonDificil.getWidth() / 2,
                Gdx.graphics.getHeight() / 2.5f - playButtonDificil.getHeight() / 2
        );
        playButtonDificil.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ManejadorPantallas.dificultad = 3;
                game.setScreen(new OreDefense(game));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(playButtonDificil);

        //Botón salir
        TextButton exitButton = new TextButton("Salir", game.gameSkin);
        exitButton.setWidth(Gdx.graphics.getWidth() / 2);
        exitButton.setPosition(
                Gdx.graphics.getWidth() / 2 - exitButton.getWidth() / 2,
                Gdx.graphics.getHeight() / 6 - exitButton.getHeight() / 2
        );
        exitButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
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
