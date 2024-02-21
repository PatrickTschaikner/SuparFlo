package com.mygdx.game.actors;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Spieler extends SpielObjekt{
    Sound sound;
    private Rectangle boundary;
    private float accelleration= 0.5f;
    private float speed = 2.0f;
    private float direction = 0f;
    public Spieler(int x, int y, Texture image) {
        super(x, y, image);
        boundary = new Rectangle();
        this.setBoundary();
    }

    public void setBoundary(){
        this.boundary.set(this.getX(),this.getY(),this.getWidth(),this.getHeight());
    }
    @Override
    public void draw(Batch b, float parentAlpha) {
        this.getImage().draw(b);

    }

    public void update(float delta){

    }

    public void move (int direction){
        if (direction != this.direction){
            speed = 2;
        }
        speed += accelleration;
        if(direction == 1){
            this.setX((getX()-speed));
        }else{
            this.setX(this.getX()+speed);
        }
        this.setBoundary();
        this.direction = direction;
    }

    public void act(float delta){
        super.act(delta);
    }

    public boolean collideRectangle(Rectangle shape){
        if (Intersector.overlaps(this.boundary,shape)){
            return true;
        }else {
            return false;
        }
    }

}
