package com.arek00.pacman.Inputs;

import android.graphics.PointF;
import com.arek00.pacman.Logics.Characters.ICharacter;
import com.arek00.pacman.Logics.Characters.IPlayer;

/**
 * Abstraction to converts player input to reasonable player interaction data.
 * Interface should be implemented by InputHandlers
 */
public interface InputConverter {

    /**
     *
     * @return
     */
    public PointF convertToPlayerMove(ICharacter player);

}
