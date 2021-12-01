package es.iesoretania;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ManejadorPantallas extends Game {
    BitmapFont font;
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    static public Skin gameSkin;
    public static int dificultad;
    @Override
    public void create() {
        font = new BitmapFont();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        gameSkin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));

        setScreen(new MenuPrincipal(this));
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}
