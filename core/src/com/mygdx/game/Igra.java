package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


import static com.badlogic.gdx.Application.ApplicationType.Android;
import static com.badlogic.gdx.Files.FileType.Local;

public class Igra extends ApplicationAdapter {

	OrthographicCamera camera;
	SpriteBatch batch;

	Texture lico;
	Music mainTheme;

	Rectangle licoM;
	
	@Override
	public void create () {

		camera = new OrthographicCamera();
		camera.setToOrtho(false,1920,1080);

		batch = new SpriteBatch();
		lico = new Texture("lico.png");
		mainTheme = Gdx.audio.newMusic(Gdx.files.internal("MainTheme.mp3"));

		mainTheme.setLooping(true);
		mainTheme.play();

		licoM = new Rectangle();
		licoM.x = 1920/2 -276/2;
		licoM.y = 1080/2-276/2;
		licoM.width = 276;
		licoM.height = 276;


	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,0,0.2f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(lico, licoM.x, licoM.y);
		batch.end();

		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
			camera.unproject(touchPos);
			licoM.x = (int) (touchPos.x - 276/2);
			licoM.y = (int) (touchPos.y - 276/2);
		}


	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose () {
		batch.dispose();
		lico.dispose();
		mainTheme.dispose();

		}
}
