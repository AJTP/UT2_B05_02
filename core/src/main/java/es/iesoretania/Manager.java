package es.iesoretania;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Manager extends Actor {
    private final ManejadorPantallas game;
    private static BitmapFont font;
    private final Nave nave;
    public static int score;
    public float inicio;


    public Manager(Nave nave,ManejadorPantallas game) {
        this.nave = nave;
        this.game=game;
        if (font == null) {
            // Cargar fuente solamente si es la primera vez
            font = new BitmapFont();
        }
        score = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, "Puntos: " + score, 20, 460);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        inicio+=delta;
        if(inicio>=1f){
            score+=100;
            inicio=0;
        }
        for(int i=0;i<OreDefense.meteoritos.length;i++){
            if( OreDefense.meteoritos[i].killer==true && OreDefense.meteoritos[i].isVisible() && Intersector.overlaps(nave.getShape(),OreDefense.meteoritos[i].getShape())){
                OreDefense.meteoritos[i].setVisible(false);
                nave.die();
            }
        }
        if(nave.isVisible()==false){
            game.setScreen(new PantallaFinal(game));
        }
    }
}
