package com.arek00.pacman.Logics.Maps.Utils;

import android.graphics.Point;
import android.graphics.PointF;
import com.arek00.pacman.Logics.Fields.FieldsEnum;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Admin on 2015-07-10.
 */
public class FieldsRetriever {


    /**
     * Get array with positions of all fields on map with given field value
     *
     * @param fields
     * @param fieldValue
     * @param size
     * @return
     */
    public static PointF[] getPositionsOfConcreteFields(int[][] fields, int fieldValue, Point size) {
        ArrayList<PointF> points = new ArrayList<PointF>();

        for (int line = 0; line < size.y; line++) {
            for (int column = 0; column < size.x; column++) {
                if (fields[column][line] == fieldValue) {
                    points.add(points.size(), new PointF(column, line));
                }
            }
        }
        PointF[] pointsArray = points.toArray(new PointF[points.size()]);

        return pointsArray;
    }

    /**
     * Get amount of fields with given field value
     *
     * @param fields
     * @param fieldValue
     * @param size
     * @return
     */
    public static int getCountOfConcreteField(int[][] fields, int fieldValue, Point size) {
        int amount = 0;

        for (int line = 0; line < size.y; line++) {
            for (int column = 0; column < size.x; column++) {
                if (fields[column][line] == fieldValue) {
                    amount++;
                }
            }
        }

        return amount;
    }

    public static PointF getRandomField(PointF[] array) {
        int maxRandom = array.length;
        Random random = new Random();
        int index = random.nextInt(maxRandom);
        return array[index];
    }

}
