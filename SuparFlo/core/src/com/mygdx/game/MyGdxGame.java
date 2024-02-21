package com.mygdx.game;

import com.mygdx.game.screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
	public static final int WORLD_WIDTH = 200;
	public static final int WORLD_HEIGHT = 104;

	public SpriteBatch batch;


	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
