package com.arek00.pacman.Logics.Levels.LevelScenarios;

import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import com.arek00.pacman.Inputs.Interpreters.InputInterpreter;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.IPlayer;
import com.arek00.pacman.Logics.Characters.MovementDirection;
import com.arek00.pacman.Logics.Fields.FieldsEnum;
import com.arek00.pacman.Logics.Levels.ILevel;
import com.arek00.pacman.Logics.Maps.IMap;
import com.arek00.pacman.Logics.Maps.Utils.FieldsRetriever;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

/**
 * Manage logic of a level
 */
public class NormalGameLevel implements ILevel {

    private IMap map;
    private IPlayer player;
    private ICharacter[] enemies;
    private int fields[][];
    private int remainingBalls;
    private InputInterpreter interpreter;
    private PointF inputInformation;


    public NormalGameLevel(IMap levelMap, IPlayer player, InputInterpreter interpreter) {

        NullPointerValidator.validate(levelMap);
        NullPointerValidator.validate(player);

        map = levelMap;
        this.player = player;
        this.enemies = enemies;
        this.interpreter = interpreter;
        this.inputInformation = new PointF(0, 0);

        //fields = map.getMatrix();
        //setPlayerPosition();
    }

    public void startLevel() {
        fields = map.getMatrix();
        setPlayerPosition();
        //setEnemiesPosition();

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
        MovementDirection direction = interpreter.interpretInputData(inputInformation);
        player.move(direction);
        onCharacterCollides(player, direction);

        // Log.i("Player position: ", player.getPosition().x + " " + player.getPosition().y);


        //PointF movement = player.getPosition();

//        PointF playerMove = MovementEstimator.calculatePlayerMove(player, destinationPoint, TimeHelper.getDeltaTime());

//        player.move(movement);
//
//        if (isCharacterCollides(player)) {
//            undoCharacterMove(player, movement);
//        }
//
//        if (isPlayerCollectedBall(player)) {
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


    private void onCharacterCollides(ICharacter character, MovementDirection direction) {
        if (isCharacterCollides(character, direction)) {
            correctPlayerPosition(character, MovementDirection.getDirectionByValue(direction.reverse));
        }
    }


    private void correctPlayerPosition(ICharacter character, MovementDirection correctionDirection) {
        float characterX = character.getPosition().x;
        float characterY = character.getPosition().y;

        if (correctionDirection == MovementDirection.DOWN) {
            characterY = (float) Math.ceil(characterY);
        } else if (correctionDirection == MovementDirection.UP) {
            characterY = (float) Math.floor(characterY);
        } else if (correctionDirection == MovementDirection.LEFT) {
            characterX = (float) Math.floor(characterX);
        } else if (correctionDirection == MovementDirection.RIGHT) {
            characterX = (float) Math.ceil(characterX);
        }

        character.setPosition(characterX, characterY);
    }

    /**
     * Return side where found collision
     *
     * @param character
     * @return
     */

    private boolean isCharacterCollides(ICharacter character, MovementDirection movement) {
        NullPointerValidator.validate(character);

        PointF characterPosition = character.getPosition();

        int minX = (int) Math.floor(characterPosition.x);
        int maxX = (int) Math.ceil(characterPosition.x);
        int minY = (int) Math.floor(characterPosition.y);
        int maxY = (int) Math.ceil(characterPosition.y);


        if (FieldsEnum.checkFieldCollision(fields[minX][minY]) ||
                FieldsEnum.checkFieldCollision(fields[minX][maxY]) ||
                FieldsEnum.checkFieldCollision(fields[maxX][minY]) ||
                FieldsEnum.checkFieldCollision(fields[maxX][maxY])) {
            return true;
        } else {
            return false;
        }
    }


    private boolean isPlayerCollectedBall(ICharacter player) {
        NullPointerValidator.validate(player);

        int positionX = (int) player.getPosition().x;
        int positionY = (int) player.getPosition().y;

        if (fields[positionX][positionY] == FieldsEnum.BIGBALL.field.getValue() ||
                fields[positionX][positionY] == FieldsEnum.SMALLBALL.field.getValue()) {
            return true;
        }

        return false;
    }

    private void collectBall(PointF playerPosition) {
        NullPointerValidator.validate(playerPosition);

        int ballX = Math.round(playerPosition.x);
        int ballY = Math.round(playerPosition.y);

        addPlayersPoint(1);
        fields[ballX][ballY] = FieldsEnum.COLLECTED.field.getValue();
        --remainingBalls;
    }

    private void addPlayersPoint(int pointsAmount) {
        player.addPoints(pointsAmount);
    }


}
