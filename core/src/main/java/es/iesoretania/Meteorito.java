package es.iesoretania;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.Random;


public class Meteorito extends Actor {
    private static TextureRegion meteorito[]= null;
    private TextureRegion regionActual;
    public int descenso,rotacion;
    static Random rnd = new Random();
    int dificultad;
    private float deathmoment;
    private float stateTime;
    boolean killer=true;
    boolean muerto=false;
    private static TextureRegion[] explosiontexture = new TextureRegion[8];
    private static Animation<TextureRegion> explosion;
    private Stage stage;

    public Meteorito(){
        stage = OreDefense.getStage();
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
            explosiontexture[0] = new TextureRegion(completo,8,167,10,10);
            explosiontexture[1] = new TextureRegion(completo,23,164,20,20);
            explosiontexture[2] = new TextureRegion(completo,47,161,23,23);
            explosiontexture[3] = new TextureRegion(completo,75,152,45,40);
            explosiontexture[4] = new TextureRegion(completo,0,196,47,45);
            explosiontexture[5] = new TextureRegion(completo,53,194,55,51);
            explosiontexture[6] = new TextureRegion(completo,124,193,55,55);
            explosiontexture[7] = new TextureRegion(completo,190,193,55,55);
            explosion=new Animation<>(0.1f, explosiontexture);
        }

        descenso = -(rnd.nextInt(300)+100 * (dificultad/2));
        rotacion = rnd.nextInt(180)+1;
        regionActual = meteorito[rnd.nextInt(5)];
        setSize(regionActual.getRegionWidth(), regionActual.getRegionHeight());
        setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        setPosition(rnd.nextInt(640), rnd.nextInt(750)+500);
        setTouchable(Touchable.enabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(regionActual, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void act(float delta) {
        if(Gdx.input.isTouched()){
            Vector3 posicion = new Vector3();
            posicion.set(Gdx.input.getX(),Gdx.input.getY(),0);
            stage.getCamera().unproject(posicion);
            if(getShape().contains(posicion.x,posicion.y)){
                die();
            }
        }
        if(!muerto){
            stateTime=0;
            this.moveBy(0,descenso*delta);
            this.rotateBy(rotacion*delta);
            if(getY()<0-getHeight()){
                //Enviar el meteorito de vuelta arriba
                this.setY(rnd.nextInt(750)+500);
                descenso = -(rnd.nextInt(300)+100);
                rotacion = rnd.nextInt(180)+1;
                regionActual = meteorito[rnd.nextInt(5)];
                this.setVisible(true);
                killer=true;
            }
        }else{
            stateTime+=delta;
            regionActual = explosion.getKeyFrame(stateTime,false);
            if(stateTime - deathmoment > (0.8)){
                this.setVisible(false);
                muerto=false;
            }
        }
    }

    Rectangle getShape(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    private void die() {
        deathmoment=Gdx.graphics.getDeltaTime();
        muerto=true;
        killer=false;
    }
}
