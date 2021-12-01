package es.iesoretania;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;


public class Meteorito extends Actor {
    private static TextureRegion meteorito[]= null;
    private TextureRegion regionActual;
    public int descenso,rotacion;
    static Random rnd = new Random();
    int dificultad;


    public Meteorito(){
        dificultad=ManejadorPantallas.dificultad;
        Texture completo = new Texture(Gdx.files.internal("spacetheme.png"));
        if(meteorito==null){
            meteorito = new TextureRegion[6];
            meteorito[0] = new TextureRegion(completo,0,250,55,55);
            meteorito[1] = new TextureRegion(completo,0,300,55,60);
            meteorito[2] = new TextureRegion(completo,65,250,35,55);
            meteorito[3] = new TextureRegion(completo,65,300,25,45);
            meteorito[4] = new TextureRegion(completo,100,270,20,20);
            meteorito[5] = new TextureRegion(completo,90,300,25,30);
        }

        descenso = -(rnd.nextInt(300)+100 * (dificultad/2));
        rotacion = rnd.nextInt(180)+1;
        regionActual = meteorito[rnd.nextInt(5)];
        setSize(regionActual.getRegionWidth(), regionActual.getRegionHeight());
        setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        setPosition(rnd.nextInt(640), rnd.nextInt(750)+500);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(regionActual, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void act(float delta) {
       this.moveBy(0,descenso*delta);
       this.rotateBy(rotacion*delta);
       if(getY()<0-getHeight()){
           //Enviar el meteorito de vuelta arriba
           this.setY(rnd.nextInt(750)+500);
           descenso = -(rnd.nextInt(300)+100);
           rotacion = rnd.nextInt(180)+1;
       }
    }
}
