package com.arek00.pacman.Logics.Fields;

/**
 * Stores IDs of all fields.
 */
public enum FieldsEnum {

    UNKNOWN_FIELD(new Field(-1, false)),
    WALL(new Field(0, true)),
    COLLECTED(new Field(1, false)),
    SMALLBALL(new Field(2, false)),
    BIGBALL(new Field(3, false)),
    PLAYER_SPAWN(new Field(4, false)),
    ENEMY_SPAWN(new Field(5, false));

    public final IField field;

    FieldsEnum(IField field) {
        this.field = field;
    }

}
