package com.arek00.pacman.Factories;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.DrawableCharacter;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.Tile;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Logics.Characters.ConcreteCharacters.Enemy;
import com.arek00.pacman.Logics.Characters.ConcreteCharacters.Player;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.IEnemy;
import com.arek00.pacman.Logics.Characters.MovementStrategies.ConcreteStrategies.RandomMovementStrategy;
import com.arek00.pacman.Logics.Characters.MovementStrategies.IMovementStrategy;
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

    public static DrawableCharacter createDrawableEnemy(IEnemy enemy, Drawable enemyTile) {
        return new DrawableCharacter(enemy, enemyTile);
    }

    public static DrawableCharacter[] createEnemies(IEnemy[] enemies, Context context) {
        NullPointerValidator.validate(enemies);

        int enemiesAmount = enemies.length;
        DrawableCharacter[] enemiesDrawable = new DrawableCharacter[enemiesAmount];

        for (int index = 0; index < enemiesAmount; index++) {
            Tile enemyTile = getRandomEnemyTile(context);
            IEnemy enemy = enemies[index];
            enemiesDrawable[index] = createDrawableEnemy(enemy, enemyTile);
        }

        return enemiesDrawable;
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

    private static Tile getRandomEnemyTile(Context context) {
        Random random = new Random();
        int nextInt = random.nextInt(4);

        switch (nextInt) {
            case 0:
                return getBlueGhostTile(context);
            case 1:
                return getRedGhostTile(context);
            case 2:
                return getPinkGhostTile(context);
            case 3:
                return getOrangeGhostTile(context);
            default:
                return getOrangeGhostTile(context);
        }
    }

    private static Tile getGhostTile(int left, int top, Context context) {
        BitmapRetriever retriever = new BitmapRetriever(context);
        Bitmap bitmap = retriever.retrieveBitmapFromAssets("images/ghosts_sprites.png");
        Tile ghost = new Tile(bitmap, left, top, 64, 64);
        return ghost;
    }

    private static Tile getRedGhostTile(Context context) {
        return getGhostTile(1000, 100, context);
    }

    private static Tile getPinkGhostTile(Context context) {
        return getGhostTile(1000, 164, context);
    }

    private static Tile getBlueGhostTile(Context context) {
        return getGhostTile(1000, 228, context);
    }

    private static Tile getOrangeGhostTile(Context context) {
        return getGhostTile(1000, 292, context);
    }


}
