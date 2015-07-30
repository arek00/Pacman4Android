package com.arek00.pacman.Logics.Levels.LevelScenarios;

import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import com.arek00.pacman.Graphics.Listeners.Lifes.LifeListener;
import com.arek00.pacman.Graphics.Listeners.Lifes.LifeObservable;
import com.arek00.pacman.Graphics.Listeners.Points.PointsListener;
import com.arek00.pacman.Graphics.Listeners.Points.PointsObservable;
import com.arek00.pacman.Inputs.Interpreters.InputInterpreter;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.IEnemy;
import com.arek00.pacman.Logics.Characters.IPlayer;
import com.arek00.pacman.Logics.Characters.MovementDirection;
import com.arek00.pacman.Logics.Fields.FieldsEnum;
import com.arek00.pacman.Logics.Levels.ILevel;
import com.arek00.pacman.Logics.Levels.Utils.CharacterArea;
import com.arek00.pacman.Logics.Maps.IMap;
import com.arek00.pacman.Logics.Maps.Utils.FieldsRetriever;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;
import com.arek00.pacman.Utils.Validators.NumberValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage logic of a level
 */
public class NormalGameLevel implements ILevel, LifeObservable, PointsObservable {

    private IMap map;
    private IPlayer player;
    private IEnemy[] enemies;
    private int fields[][];
    private int remainingBalls;
    private InputInterpreter interpreter;
    private PointF inputInformation;
    private LifeObservable lifeObservable;
    private PointsObservable pointsObservable;


    public NormalGameLevel(IMap levelMap, IPlayer player, IEnemy[] enemies, InputInterpreter interpreter) {

        NullPointerValidator.validate(levelMap);
        NullPointerValidator.validate(player);

        map = levelMap;
        this.player = player;
        this.enemies = enemies;
        this.interpreter = interpreter;
        this.inputInformation = new PointF(0, 0);
        this.lifeObservable = new LifeObservableHandler();
        this.pointsObservable = new PointsObservableHandler();
    }

    public void startLevel() {
        fields = map.getMatrix();
        setPlayerPosition();
        setEnemiesPosition();

        remainingBalls =
                FieldsRetriever.getCountOfConcreteField(
                        fields,
                        FieldsEnum.SMALLBALL.field.getValue(),
                        map.getSize());

        remainingBalls +=
                FieldsRetriever.getCountOfConcreteField(
                        fields,
                        FieldsEnum.BIGBALL.field.getValue(),
                        map.getSize());

        lifeObservable.informLifeListeners();
        pointsObservable.informPointsListeners();
    }

    private void setPlayerPosition() {
        PointF[] playerSpawns = map.getPlayerStartPosition();
        this.player.setPosition(FieldsRetriever.getRandomField(playerSpawns));
    }

    private void setEnemiesPosition() {
        PointF[] enemiesSpawns = map.getEnemiesStartPosition();

        for (ICharacter enemy : enemies) {
            enemy.setPosition(FieldsRetriever.getRandomField(enemiesSpawns));
        }
    }

    /**
     * Do it to finish game
     */
    public void finishLevel() {
        //TODO do when level finished
        if (player.getLife() < 0) {
            //TODO losing scenario
        } else if (remainingBalls < 1) {
            //TODO winning scenario
        }
    }

    /**
     * Check if game should be finished and return current game state
     */
    public boolean isFinished() {
        if (player.getLife() < 0 || remainingBalls < 1) {
            return true;
        }
        return false;
    }

    public ICharacter getPlayer() {
        return player;
    }

    public ICharacter[] getEnemies() {
        return enemies;
    }

    public int getFieldValue(int x, int y) {
        return fields[x][y];
    }

    public Point getMapSize() {
        return map.getSize();
    }

    public void setInputInterpreter(InputInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    public void setInput(PointF input) {
        NullPointerValidator.validate(input);

        // Log.i("SET INPUT", "GET INPUT FROM GAME: X: " + input.x + " Y: " + input.y);

        this.inputInformation = input;
    }

    public void update() {
        movePlayer();
        moveEnemies();


        // Log.i("Player position: ", player.getPosition().x + " " + player.getPosition().y);


        //PointF movement = player.getPosition();

//        PointF playerMove = MovementEstimator.calculateMove(player, destinationPoint, TimeHelper.getDeltaTime());

//        player.move(movement);
//
//        if (isCharacterCollides(player)) {
//            undoCharacterMove(player, movement);
//        }
//
//        if (checkCollectedBalls(player)) {
//            collectBall(player.getPosition());
//        }


        //TODO class should has method move thats use before player takes a move
        //in this method a collisions, collected balls and enemies will be checked

        //TODO
        //Get player input
        //update characters position
        //check collisions
        //update Map
        //go to step 1.

        //Log.i("TICK", "TICK");

    }

    private void movePlayer() {
        MovementDirection direction = interpreter.interpretInputData(inputInformation);
        this.player.move(direction);
        onCharacterCollides(this.player, direction);
        checkCollectedBalls(this.player);
    }

    private void moveEnemies() {

        for (IEnemy enemy : enemies) {
            MovementDirection direction = enemy.executeMove();
            enemy.move(direction);
            onCharacterCollides(enemy, direction);
        }
    }


    private void onCharacterCollides(ICharacter character, MovementDirection direction) {
        if (isCharacterCollides(character, direction)) {
            correctPosition(character, MovementDirection.getDirectionByValue(direction.reverse));
        }
    }

    /**
     * @param character           Character that should correct its position due to collide with wall
     * @param correctionDirection Direction which character should move to correct its position. Very mostly its reversed position of its step.
     */
    private void correctPosition(ICharacter character, MovementDirection correctionDirection) {
        float characterX = character.getPosition().x;
        float characterY = character.getPosition().y;

//        String info = String.format("X: %.2f, Y: %.2f", character.getPosition().x, character.getPosition().y);
//        Log.i("Collision Before", info);

        character.move(correctionDirection);
//
//        info = String.format("X: %.2f, Y: %.2f", character.getPosition().x, character.getPosition().y);
//        Log.i("Collision After", info);

        if (correctionDirection == MovementDirection.DOWN) {
            characterY = (float) Math.floor(character.getPosition().y);
        } else if (correctionDirection == MovementDirection.UP) {
            characterY = (float) Math.ceil(character.getPosition().y);
        } else if (correctionDirection == MovementDirection.LEFT) {
            characterX = (float) Math.ceil(character.getPosition().x);
        } else if (correctionDirection == MovementDirection.RIGHT) {
            characterX = (float) Math.floor(character.getPosition().x);
        }

        character.setPosition(characterX, characterY);
//        info = String.format("X: %.2f, Y: %.2f", character.getPosition().x, character.getPosition().y);
//        Log.i("New Position", info);
    }

    /**
     * Return side where found collision
     *
     * @param character
     * @return
     */

    private boolean isCharacterCollides(ICharacter character, MovementDirection movement) {
        NullPointerValidator.validate(character);
        CharacterArea characterArea = new CharacterArea(character);

        Log.i("Character" + character.toString(), characterArea.toString() + " " + movement.toString());


        if (FieldsEnum.isFieldCollide(
                fields[characterArea.getMinX()][characterArea.getMinY()]) ||
                FieldsEnum.isFieldCollide(
                        fields[characterArea.getMinX()][characterArea.getMaxY()]) ||
                FieldsEnum.isFieldCollide(
                        fields[characterArea.getMaxX()][characterArea.getMinY()]) ||
                FieldsEnum.isFieldCollide(
                        fields[characterArea.getMaxX()][characterArea.getMaxY()])) {
            return true;
        } else {
            return false;
        }
    }


    private void checkCollectedBalls(ICharacter player) {
        NullPointerValidator.validate(player);
        CharacterArea area = new CharacterArea(player);

        if (FieldsEnum.isFieldCollectible(fields[area.getMinX()][area.getMinY()])) {
            collectBall(area.getMinX(), area.getMinY());
        }
        if (FieldsEnum.isFieldCollectible(fields[area.getMinX()][area.getMaxY()])) {
            collectBall(area.getMinX(), area.getMaxY());
        }
        if (FieldsEnum.isFieldCollectible(fields[area.getMaxX()][area.getMinY()])) {
            collectBall(area.getMaxX(), area.getMinY());
        }
        if (FieldsEnum.isFieldCollectible(fields[area.getMaxX()][area.getMaxY()])) {
            collectBall(area.getMaxX(), area.getMaxY());
        }
    }

    private void collectBall(int ballX, int ballY) {
        NumberValidator.checkNegativeNumber(ballX);
        NumberValidator.checkNegativeNumber(ballY);

        fields[ballX][ballY] = FieldsEnum.COLLECTED.field.getValue();
        addPlayersPoint(10);
        --remainingBalls;
    }

    private void addPlayersPoint(int pointsAmount) {
        player.addPoints(pointsAmount);
        informPointsListeners();
    }


    /*
        Methods to handle listeners of player's points and lives below.
    */

    public void addLifeListener(LifeListener listener) {
        lifeObservable.addLifeListener(listener);
    }

    public void removeLifeListener(LifeListener listener) {
        lifeObservable.removeLifeListener(listener);
    }

    public void informLifeListeners() {
        lifeObservable.informLifeListeners();
    }

    public void addPointsListener(PointsListener listener) {
        pointsObservable.addPointsListener(listener);
    }

    public void removePointsListener(PointsListener listener) {
        pointsObservable.removePointsListener(listener);
    }

    public void informPointsListeners() {
        pointsObservable.informPointsListeners();
    }

    class PointsObservableHandler implements PointsObservable {
        private List<PointsListener> listeners = new ArrayList<PointsListener>();

        public void addPointsListener(PointsListener listener) {
            NullPointerValidator.validate(listener);
            listeners.add(listener);
        }

        public void removePointsListener(PointsListener listener) {
            NullPointerValidator.validate(listener);
            listeners.remove(listener);
        }

        public void informPointsListeners() {
            for (PointsListener listener : listeners) {
                listener.onChangePoints(player.getPoints());
            }
        }
    }


    class LifeObservableHandler implements LifeObservable {
        private List<LifeListener> listeners = new ArrayList<LifeListener>();

        public void addLifeListener(LifeListener listener) {
            NullPointerValidator.validate(listener);
            listeners.add(listener);
        }

        public void removeLifeListener(LifeListener listener) {
            NullPointerValidator.validate(listener);
            listeners.remove(listener);
        }

        public void informLifeListeners() {
            for (LifeListener listener : listeners) {
                listener.onChangeLife(player.getLife());
            }
        }
    }
}
