package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.actors.Spieler;
import com.mygdx.game.helper.ImageHelper;

public class MyGdxGame implements ApplicationListener {

	static final int WORLD_WIDTH = 100;
	static final int WORLD_HEIGHT = 100;

	private OrthographicCamera cam;
	private SpriteBatch batch;

	private Sprite mapSprite;

	private Spieler spieler;
	ImageHelper ih;
	Texture Background;

	@Override
	public void create() {
		ih = new ImageHelper();
		Background = new Texture("Images/landscape.png");
		spieler = new Spieler(2,2,ih.changeImgSize(10,10,"Images/Paul.png"));
		mapSprite = new Sprite(Background);
		mapSprite.setPosition(0, 0);
		mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		// Constructs a new OrthographicCamera, using the given viewport width and height
		// Height is multiplied by aspect ratio.
		cam = new OrthographicCamera(30, 30 * (h / w));

		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		cam.update();

		batch = new SpriteBatch();
	}

	@Override
	public void render() {


		// Clear the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Move the camera
		cam.update();
		batch.setProjectionMatrix(cam.combined);

		// Begin rendering
		batch.begin();
		handleInput();
		mapSprite.draw(batch);
		spieler.draw(batch, 1);
		batch.end();
	}

	private void handleInput() {

		float deltaTime = Gdx.graphics.getDeltaTime();

		// Bewegung des Spielers
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && spieler.getX() > 0) {
			spieler.move(-1, deltaTime);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && spieler.getX() < WORLD_WIDTH - spieler.getWidth()) {
			spieler.move(1, deltaTime);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			cam.translate(-0.5f, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			cam.translate(0.5f, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			cam.translate(0, -0.5f, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			cam.translate(0, 0.5f, 0);
		}



		float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
		float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

		cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
		cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
	}

	@Override
	public void resize(int width, int height) {
		cam.viewportWidth = 30f;
		cam.viewportHeight = 30f * height/width;
		cam.update();
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		mapSprite.getTexture().dispose();
		batch.dispose();
	}

	@Override
	public void pause() {
	}

}
