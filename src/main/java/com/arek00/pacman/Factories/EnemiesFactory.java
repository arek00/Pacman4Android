package com.arek00.pacman.Factories;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.DrawableCharacter;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.MoveAnimatedCharacter;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.MoveAnimatedEnemy;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.Tile;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Logics.Characters.ConcreteCharacters.Enemy;
import com.arek00.pacman.Logics.Characters.ConcreteCharacters.Player;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.IEnemy;
import com.arek00.pacman.Logics.Characters.MovementStrategies.ConcreteStrategies.RandomMovementStrategy;
import com.arek00.pacman.Logics.Characters.MovementStrategies.IMovementStrategy;
import com.arek00.pacman.Utils.DataHelpers.AssetsHelper;
import com.arek00.pacman.Utils.DataHelpers.BitmapRetriever;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;
import com.arek00.pacman.Utils.Validators.NumberValidator;

import java.util.Random;

/**
 * Create enemies array.
 */
public class EnemiesFactory {

    public static IEnemy createEnemy(ICharacter player, IMovementStrategy strategy, float speed) {
        PointF startingPosition = new PointF(0, 0);

        IEnemy enemy = new Enemy(startingPosition, speed, strategy, player);
        return enemy;
    }

    public static MoveAnimatedEnemy[] createAnimatedEnemies(IEnemy[] enemies, Context context) {
        NullPointerValidator.validate(enemies);

        int enemiesAmount = enemies.length;
        MoveAnimatedEnemy[] animatedCharacters = new MoveAnimatedEnemy[enemiesAmount];

        for (int index = 0; index < enemiesAmount; index++) {
            IEnemy character = enemies[index];
            Tile[] tiles = getRandomAnimatedEnemy(context);
            animatedCharacters[index] = new MoveAnimatedEnemy(character, tiles);
        }
        return animatedCharacters;
    }


    public static IEnemy[] createEnemies(int enemiesNumber, ICharacter player) {
        NumberValidator.checkNegativeNumber(enemiesNumber);

        IEnemy[] enemies = new Enemy[enemiesNumber];

        for (int index = 0; index < enemiesNumber; index++) {
            IMovementStrategy strategy = new RandomMovementStrategy(50, 100);
            enemies[index] = createEnemy(player, strategy, Player.PLAYER_MEDIUM_SPEED);
        }

        return enemies;
    }

    private static Tile[] getRandomAnimatedEnemy(Context context) {
        Random random = new Random();
        int nextInt = random.nextInt(4);

        switch (nextInt) {
            case 0:
                return getAnimatedBlueEnemy(context);
            case 1:
                return getAnimatedOrangeEnemy(context);
            case 2:
                return getAnimatedPinkEnemy(context);
            case 3:
                return getAnimatedRedEnemy(context);
            default:
                return getAnimatedRedEnemy(context);
        }
    }


    private static Tile[] getAnimatedEnemy(int startingY, Context context) {
        BitmapRetriever retriever = new BitmapRetriever(context);
        Bitmap tileSheet = retriever.retrieveBitmapFromAssets("images/ghosts_sprites.png");

        Tile[] tiles = new Tile[]{
                new Tile(tileSheet, 0, startingY, 64, 64), //NONE
                new Tile(tileSheet, 128, startingY, 64, 64), //UP
                new Tile(tileSheet, 0, startingY, 64, 64), //DOWN
                new Tile(tileSheet, 128, startingY, 64, 64), //LEFT
                new Tile(tileSheet, 256, startingY, 64, 64), //RIGHT
        };
        return tiles;
    }

    private static Tile[] getAnimatedRedEnemy(Context context) {
        return getAnimatedEnemy(0, context);
    }

    private static Tile[] getAnimatedPinkEnemy(Context context) {
        return getAnimatedEnemy(64, context);
    }

    private static Tile[] getAnimatedBlueEnemy(Context context) {
        return getAnimatedEnemy(128, context);
    }

    private static Tile[] getAnimatedOrangeEnemy(Context context) {
        return getAnimatedEnemy(192, context);
    }

}
