package es.iesoretania;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Nave extends Actor {
    enum VerticalMovement {UP, NONE, DOWN};
    enum HorizontalMovement {LEFT, NONE, RIGHT};
    private static TextureRegion naveReposo= null;
    private static TextureRegion naveIzq= null;
    private static TextureRegion naveDcha= null;
    private static TextureRegion naveArriba= null;
    private static TextureRegion naveArribaIzq = null;
    private static TextureRegion naveArribaDcha= null;
    private static TextureRegion naveAbajo= null;
    private static TextureRegion naveAbajoIzq= null;
    private static TextureRegion naveAbajoDcha= null;
    HorizontalMovement horizontalMovement;
    VerticalMovement verticalMovement;
    TextureRegion regionActual;
    private float deathmoment;
    private float stateTime;
    Boolean muerto=false;
    private static TextureRegion[] explosiontexture = new TextureRegion[8];
    private static Animation<TextureRegion> explosion;

    public Nave(float x, float y) {
        Texture completo = new Texture(Gdx.files.internal("spacetheme.png"));

        explosiontexture[0] = new TextureRegion(completo,8,167,10,10);
        explosiontexture[1] = new TextureRegion(completo,23,164,20,20);
        explosiontexture[2] = new TextureRegion(completo,47,161,23,23);
        explosiontexture[3] = new TextureRegion(completo,75,152,45,40);
        explosiontexture[4] = new TextureRegion(completo,0,196,47,45);
        explosiontexture[5] = new TextureRegion(completo,53,194,55,51);
        explosiontexture[6] = new TextureRegion(completo,124,193,55,55);
        explosiontexture[7] = new TextureRegion(completo,190,193,55,55);
        explosion=new Animation<>(0.1f, explosiontexture);

        naveArribaIzq = new TextureRegion(completo, 0, 44, 39, 43);
        naveArriba = new TextureRegion(completo, 42, 44, 39, 43);
        naveArribaDcha = new TextureRegion(completo, 84, 44, 39, 43);
        naveIzq = new TextureRegion(completo, 0, 0, 39, 43);
        naveReposo = new TextureRegion(completo, 42, 0, 39, 43);
        naveDcha = new TextureRegion(completo, 84, 0, 39, 43);
        naveAbajoIzq = new TextureRegion(completo, 0, 88, 39, 43);
        naveAbajo = new TextureRegion(completo, 42, 88, 39, 43);
        naveAbajoDcha = new TextureRegion(completo, 84, 88, 39, 43);
        regionActual = naveReposo;
        horizontalMovement = HorizontalMovement.NONE;
        verticalMovement = VerticalMovement.NONE;
        setSize(regionActual.getRegionWidth(), regionActual.getRegionHeight());
        setPosition(x - getWidth() / 2, y - getHeight() / 2);
        addListener(new NaveInputListener());
    }

    public Nave() {
        this(400, 240);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(regionActual, getX(), getY());
    }
    @Override
    public void act(float delta) {

        if(muerto){
            stateTime+=delta;
            regionActual = explosion.getKeyFrame(stateTime,false);
            if(stateTime - deathmoment > (0.8)){
                Actions.addAction(Actions.sequence(Actions.removeActor()));
                this.setVisible(false);
            }
        }else{
            if (verticalMovement == VerticalMovement.UP) {
                this.moveBy(0, 100 * delta);
            }
            if (verticalMovement == VerticalMovement.DOWN) {
                this.moveBy(0, -100 * delta);
            }
            if (horizontalMovement == HorizontalMovement.LEFT) {
                this.moveBy(-100 * delta, 0);
            }
            if (horizontalMovement == HorizontalMovement.RIGHT) {
                this.moveBy(100 * delta, 0);
            }
            if (getX() < 0) setX(0);
            if (getY() < 0) setY(0);
            if (getX() >= 799 - getWidth()) setX(799 - getWidth());
            if (getY() >= 479 - getHeight()) setY(479 - getHeight());
            if (verticalMovement == VerticalMovement.UP && horizontalMovement == HorizontalMovement.LEFT)
                regionActual = naveArribaIzq;
            if (verticalMovement == VerticalMovement.UP && horizontalMovement == HorizontalMovement.NONE)
                regionActual = naveArriba;
            if (verticalMovement == VerticalMovement.UP && horizontalMovement == HorizontalMovement.RIGHT)
                regionActual = naveArribaDcha;
            if (verticalMovement == VerticalMovement.NONE && horizontalMovement == HorizontalMovement.LEFT)
                regionActual = naveIzq;
            if (verticalMovement == VerticalMovement.NONE && horizontalMovement == HorizontalMovement.NONE)
                regionActual = naveReposo;
            if (verticalMovement == VerticalMovement.NONE && horizontalMovement == HorizontalMovement.RIGHT)
                regionActual = naveDcha;
            if (verticalMovement == VerticalMovement.DOWN && horizontalMovement == HorizontalMovement.LEFT)
                regionActual = naveAbajoIzq;
            if (verticalMovement == VerticalMovement.DOWN && horizontalMovement == HorizontalMovement.NONE)
                regionActual = naveAbajo;
            if (verticalMovement == VerticalMovement.DOWN && horizontalMovement == HorizontalMovement.RIGHT)
                regionActual = naveAbajoDcha;
        }
    }

    class NaveInputListener extends InputListener {

        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.DOWN:
                    verticalMovement = VerticalMovement.DOWN;
                    break;
                case Input.Keys.UP:
                    verticalMovement = VerticalMovement.UP;
                    break;
                case Input.Keys.LEFT:
                    horizontalMovement = HorizontalMovement.LEFT;
                    break;
                case Input.Keys.RIGHT:
                    horizontalMovement = HorizontalMovement.RIGHT;
                    break;
            }
            return true;
        }
        @Override
        public boolean keyUp(InputEvent event, int keycode) {
            switch (keycode) {
                case Input.Keys.DOWN:
                    if (verticalMovement == VerticalMovement.DOWN) {
                        verticalMovement = VerticalMovement.NONE;
                    }
                    break;
                case Input.Keys.UP:
                    if (verticalMovement == VerticalMovement.UP) {
                        verticalMovement = VerticalMovement.NONE;
                    }
                    break;
                case Input.Keys.LEFT:
                    if (horizontalMovement == HorizontalMovement.LEFT) {
                        horizontalMovement = HorizontalMovement.NONE;
                    }
                    break;
                case Input.Keys.RIGHT:
                    if (horizontalMovement == HorizontalMovement.RIGHT) {
                        horizontalMovement = HorizontalMovement.NONE;
                    }
                    break;
            }
            return true;
        }
    }

    Rectangle getShape() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public void die(){
        muerto=true;
        deathmoment+=Gdx.graphics.getDeltaTime();
    }
}
