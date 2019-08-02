package dev.ayohandgod.bludborne;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.ayohandgod.bludborne.screens.MainGameScreen;

/** The type Blud borne game. */
public class BludBorneGame extends Game {

  /** The constant _mainGameScreen. */
  public static final MainGameScreen _mainGameScreen = new MainGameScreen();

  /** The Batch. */
  SpriteBatch batch;

  /** The Img. */
  Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		setScreen(_mainGameScreen);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		_mainGameScreen.dispose();
	}
}
