package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actors.Spieler;
import com.mygdx.game.scenes.Hud;

public class PlayScreen implements Screen {

    public SpriteBatch batch;

    private MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    public Spieler spieler;
    private Texture paul;

    public PlayScreen(MyGdxGame game){
        this.game = game;
        batch = new SpriteBatch();
        //erstellt Kamera zum Folgen von Mario
        camera = new OrthographicCamera();
        viewport = new FitViewport(MyGdxGame.WORLD_WIDTH,MyGdxGame.WORLD_HEIGHT,camera);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Images/landscape.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        paul = new Texture("Images/paul.png");
        spieler = new Spieler(0,0,paul );
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            camera.position.x += 100 * dt;

        if(Gdx.input.isKeyPressed(Input.Keys.A) && camera.position.x > MyGdxGame.WORLD_WIDTH / 2 +1)
            camera.position.x += 100 * -dt;

        float delta = Gdx.graphics.getDeltaTime();
        //update
        if(spieler.getX() > 0) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) spieler.move(1);
        }
        if(spieler.getX() < 824) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) spieler.move(0);
        }

    }

    public void update(float dt){
        handleInput(dt);
        camera.update();
        renderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //game.batch.begin(); // SpriteBatch initialisieren

        renderer.render();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        //spieler.draw(batch, 1); // Game-Objekt-Batch verwenden
        //spieler.update(delta);

        //game.batch.end(); // SpriteBatch beenden
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
