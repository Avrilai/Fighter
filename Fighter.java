package com.mygdx.fighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Fighter extends ApplicationAdapter {
	SpriteBatch batch;
	private Texture rosyImg;
	private OrthographicCamera camera;
	private Rectangle rosy;
    boolean flipX;
    private Texture fistImg;
    private Rectangle fist;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		rosyImg = new Texture(Gdx.files.internal("rosy.png"));
        fistImg = new Texture(Gdx.files.internal("fist.png"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		rosy = new Rectangle();
		rosy.x = 800 / 2 - 64 /2;
		rosy.y = 20;
		rosy.width = 111;
		rosy.height = 149;
        flipX = false;

        fist = new Rectangle();
        fist.x = 0;
        fist.y = 0;
        fist.width = 0;
        fist.height = 0;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(rosyImg, flipX? rosy.x + rosy.width: rosy.x,
                rosy.y, flipX? -rosy.width : rosy.width, rosy.height);
        batch.draw(fistImg, flipX? fist.x - fist.width: fist.x,
                fist.y, flipX? -fist.width : fist.width, fist.height);
		batch.end();

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			rosy.x -= 200 * Gdx.graphics.getDeltaTime();
            flipX = true;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			rosy.x += 200 * Gdx.graphics.getDeltaTime();
            flipX = false;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			rosy.y += 400 * Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			rosy.y -= 200 * Gdx.graphics.getDeltaTime();
            rosy.height = 80;
		} else {
            rosy.height = 149;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            fist.x = rosy.x + 100;
            fist.y = rosy.y + 90;
            fist.width = 96;
            fist.height = 96;
        } else {
            fist.x = 0;
            fist.y = 0;
            fist.width = 0;
            fist.height = 0;
        }
        if (rosy.y > 20){
            rosy.y -=2;
        }
        if (rosy.y < 20){
            rosy.y = 20;
        }
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		rosyImg.dispose();
	}
}
