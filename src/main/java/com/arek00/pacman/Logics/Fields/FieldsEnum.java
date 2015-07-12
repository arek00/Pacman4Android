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

    private static FieldsEnum[] values;
    public final IField field;

    FieldsEnum(IField field) {
        this.field = field;
    }


    /**
     * Get information about field collision only by its value
     *
     * @param fieldValue
     * @return true if field should collide false otherwise
     */
    public static boolean checkFieldCollision(int fieldValue) {
        return getFieldByIndex(fieldValue).isCollide();
    }

    public static IField getFieldByIndex(int index) {
        int fieldsNumber = FieldsEnum.values().length;

        for (int i = 0; i < fieldsNumber; i++) {
            IField currentField = FieldsEnum.values()[i].field;

            if (index == currentField.getValue())
                return currentField;
        }

        return UNKNOWN_FIELD.field;
    }

}
