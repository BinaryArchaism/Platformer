package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MenuScreen implements Screen {
    final MainClass game;

    OrthographicCamera cam;

    private Texture background;
    private Texture start;
    private Music music;

    private boolean size = false;
    private int sizeInt = 1;

    public MenuScreen(MainClass game) {
        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, MainClass.WIDTH, MainClass.HEIGHT);
        background = new Texture("background.png");
        start = new Texture("start.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("invitation.mp3"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
            game.batch.draw(background, 0,0);
            game.batch.draw(start, 0,0);
            cam.setToOrtho(false, MainClass.WIDTH-changeSize(), MainClass.HEIGHT-changeSize());
            music.setLooping(true);
            music.setVolume(0.05f);
            music.play();
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    private int changeSize() {
        if (!size) {
            sizeInt++;
            if (sizeInt > 30) size = true;
        } else {
            sizeInt--;
            if (sizeInt < 1) size = false;
        }
        return sizeInt;
    }

    @Override
    public void resize(int width, int height) {

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
