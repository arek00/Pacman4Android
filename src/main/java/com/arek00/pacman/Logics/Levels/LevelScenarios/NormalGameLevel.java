package com.arek00.pacman.Logics.Levels.LevelScenarios;

import android.graphics.Point;
import android.graphics.PointF;
import com.arek00.pacman.Activities.Listeners.BallsRemainingListener;
import com.arek00.pacman.Activities.Listeners.BallsRemainingObservable;
import com.arek00.pacman.Activities.Listeners.LifeListener;
import com.arek00.pacman.Activities.Listeners.LifeObservable;
import com.arek00.pacman.Activities.Listeners.PointsListener;
import com.arek00.pacman.Activities.Listeners.PointsObservable;
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
import com.arek00.pacman.Utils.DataHelpers.CollisionResolver;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;
import com.arek00.pacman.Utils.Validators.NumberValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage logic of a level
 */
public class NormalGameLevel implements ILevel, LifeObservable, PointsObservable, BallsRemainingObservable {

    private IMap map;
    private IPlayer player;
    private IEnemy[] enemies;
    private int fields[][];
    private int remainingBalls;
    private InputInterpreter interpreter;
    private PointF inputInformation;
    private LifeObservable lifeObservable;
    private PointsObservable pointsObservable;
    private BallsRemainingObservable ballsObservable;

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
        this.ballsObservable = new BallsRemainingObserverHandler();
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

        ballsObservable.informBallsRemainingListener();
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
     * Check if game should be finished and return current game state
     */
    public boolean isFinished() {
        if (player.getLife() < 0 || remainingBalls < 1) {
            return true;
        }
        return false;
    }

    public IPlayer getPlayer() {
        return player;
    }

    public IEnemy[] getEnemies() {
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

        this.inputInformation = input;
    }

    public void update() {
        movePlayer();
        moveEnemies();
        isEnemyCaughtPlayer();
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

    private void isEnemyCaughtPlayer() {
        if (CollisionResolver.isCollide(this.player, this.enemies)) {
            onEnemyCaughtPlayer();
        }
    }

    private void onEnemyCaughtPlayer() {
        player.loseLife();
        setEnemiesPosition();
        setPlayerPosition();
        lifeObservable.informLifeListeners();
    }

    private void onCharacterCollides(ICharacter character, MovementDirection direction) {
        if (isCharacterCollides(character)) {
            correctPosition(character, MovementDirection.getDirectionByValue(direction.reverse));
        }
    }

    /**
     * @param character Character that should correct its position due to collide with wall
     * @param direction Direction which character moved.
     */
    private void correctPosition(ICharacter character, MovementDirection direction) {
        NullPointerValidator.validate(character);
        NullPointerValidator.validate(direction);

        CollisionResolver.resolveCollision(character, direction);
    }

    /**
     * Return side where found collision
     *
     * @param character
     * @return
     */

    private boolean isCharacterCollides(ICharacter character) {
        NullPointerValidator.validate(character);
        return CollisionResolver.isCollide(character, this.fields);
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
        informBallsRemainingListener();
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

    public void addBallsRemainingListener(BallsRemainingListener listener) {
        ballsObservable.addBallsRemainingListener(listener);
    }

    public void removeBallsRemainingListener(BallsRemainingListener listener) {
        ballsObservable.removeBallsRemainingListener(listener);
    }

    public void informBallsRemainingListener() {
        ballsObservable.informBallsRemainingListener();
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

    class BallsRemainingObserverHandler implements BallsRemainingObservable {
        private List<BallsRemainingListener> listeners = new ArrayList<BallsRemainingListener>();

        public void addBallsRemainingListener(BallsRemainingListener listener) {
            NullPointerValidator.validate(listener);
            listeners.add(listener);
        }

        public void removeBallsRemainingListener(BallsRemainingListener listener) {
            NullPointerValidator.validate(listener);
            listeners.remove(listener);
        }

        public void informBallsRemainingListener() {
            for (BallsRemainingListener listener : listeners) {
                listener.onBallsRemainingChanged(remainingBalls);
            }
        }
    }
}
