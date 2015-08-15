package com.arek00.pacman.Factories;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.DrawableCharacter;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.Tile;
import com.arek00.pacman.Logics.Characters.ConcreteCharacters.Player;
import com.arek00.pacman.Logics.Characters.IPlayer;
import com.arek00.pacman.Utils.DataHelpers.AssetsHelper;
import com.arek00.pacman.Utils.DataHelpers.BitmapRetriever;

import java.io.IOException;
import java.io.InputStream;

/**
 * Factory that creates instances of player.
 */
public class PlayerFactory {

    public static IPlayer createPlayer(int lives) {
        PointF playerPosition = new PointF(0f, 0f);
        IPlayer player = new Player(playerPosition, lives, Player.PLAYER_MEDIUM_SPEED);
        return player;
    }

    /**
     * Get instance of drawable player.
     *
     * @param context
     * @return
     */
    public static DrawableCharacter createDrawablePlayer(IPlayer player, Context context) {
        Tile playerTile = createPlayerTile(context);
        DrawableCharacter playerDrawable = new DrawableCharacter(player, playerTile);
        return playerDrawable;
    }

    private static Tile createPlayerTile(Context context) {
        BitmapRetriever retriever = new BitmapRetriever(context);
        Bitmap bitmap = retriever.retrieveBitmapFromAssets("images/pacman_sprites.png");

        Tile playerTile = new Tile(bitmap, 64, 0, 64, 64);
        return playerTile;
    }

}
