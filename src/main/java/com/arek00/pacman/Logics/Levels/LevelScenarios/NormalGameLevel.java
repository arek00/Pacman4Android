package com.arek00.pacman.Logics.Levels.LevelScenarios;

import android.graphics.Point;
import android.graphics.PointF;
import com.arek00.pacman.Inputs.InputHandler;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.IPlayer;
import com.arek00.pacman.Logics.Fields.FieldsEnum;
import com.arek00.pacman.Logics.Levels.ILevel;
import com.arek00.pacman.Logics.Maps.IMap;
import com.arek00.pacman.Logics.Maps.Utils.FieldsRetriever;
import com.arek00.pacman.Utils.DataHelpers.MovementEstimator;
import com.arek00.pacman.Utils.DataHelpers.TimeHelper;

/**
 * Manage logic of a level
 */
public class NormalGameLevel implements ILevel {

    private IMap map;
    private IPlayer player;
    private ICharacter[] enemies;
    private int fields[][];
    private int remainingBalls;

    private InputHandler handler;

    public NormalGameLevel(IMap levelMap, IPlayer player, InputHandler input) {
        map = levelMap;
        this.player = player;
        this.enemies = enemies;
        this.handler = input;
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

    public int[][] getCurrentMapFields() {
        return fields;
    }

    public Point getMapSize() {
        return map.getSize();
    }

    public void setInputHandler(InputHandler input) {
        PointF playerInput = input.getInput();
    }

    public void update() {

        PointF destinationPoint = handler.getActualInput();
        PointF playerMove = MovementEstimator.calculatePlayerMove(player, destinationPoint, TimeHelper.getDeltaTime());

        player.move(playerMove);

        if (isCharacterCollides(player)) {
            undoCharacterMove(player, playerMove);
        }

        if (isPlayerCollectedBall(player)) {
            collectBall(player.getPosition());
        }


        //TODO
        //Get player input
        //update characters position
        //check collisions
        //update Map
        //go to step 1.

    }

    private void undoCharacterMove(ICharacter character, PointF playerMove) {
        playerMove.x = Math.round(playerMove.x);
        playerMove.y = Math.round(playerMove.y);
        character.move(playerMove);
    }

    private boolean isCharacterCollides(ICharacter character) {
        int positionX = (int) character.getPosition().x;
        int positionY = (int) character.getPosition().y;

        return FieldsEnum.checkFieldCollision(fields[positionX][positionY]);
    }

    private boolean isPlayerCollectedBall(ICharacter player) {
        int positionX = (int) player.getPosition().x;
        int positionY = (int) player.getPosition().y;

        if (fields[positionX][positionY] == FieldsEnum.BIGBALL.field.getValue() ||
                fields[positionX][positionY] == FieldsEnum.SMALLBALL.field.getValue()) {
            return true;
        }

        return false;
    }

    private void collectBall(PointF playerPosition) {

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
