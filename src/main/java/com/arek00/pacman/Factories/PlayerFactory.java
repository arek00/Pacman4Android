package com.arek00.pacman.Factories;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.DrawableCharacter;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.MoveAnimatedCharacter;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.MoveAnimatedPlayer;
import com.arek00.pacman.Graphics.Drawables.ConcreteDrawables.Tile;
import com.arek00.pacman.Graphics.Drawables.Interfaces.Drawable;
import com.arek00.pacman.Logics.Characters.ConcreteCharacters.Player;
import com.arek00.pacman.Logics.Characters.IPlayer;
import com.arek00.pacman.Utils.DataHelpers.BitmapRetriever;

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
    public static MoveAnimatedPlayer createDrawablePlayer(IPlayer player, Context context) {
        Drawable[] drawables = createPlayerDrawables(context);

        MoveAnimatedPlayer playerDrawable = new MoveAnimatedPlayer(player, drawables);
        return playerDrawable;
    }

    private static Drawable[] createPlayerDrawables(Context context) {
        BitmapRetriever retriever = new BitmapRetriever(context);
        Bitmap bitmap = retriever.retrieveBitmapFromAssets("images/pacman_sprites.png");

        Drawable[] playerDrawables = new Drawable[]{
                new Tile(bitmap, 0, 0, 64, 64),
                new Tile(bitmap, 64, 0, 64, 64),
                new Tile(bitmap, 128, 0, 64, 64),
                new Tile(bitmap, 192, 0, 64, 64),
                new Tile(bitmap, 256, 0, 64, 64),
        };


        return playerDrawables;
    }

}
