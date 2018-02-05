package com.morhop.utils;


import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * <p>Bitmap Util use android build in Bitmap and Matrix</p>
 * <strong>need android sdk to use</strong>
 * @author BingKeh
 * 2018/2/2 17:48
 */

public class BitmapUtil {

    /**
     * @param origin original bitmap
     * @param angle the angle to rotate
     * @return the rotated bitmap
     */
    public static Bitmap rotate(Bitmap origin, float angle) {
        if (origin == null)
            return null;
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(angle);
        Bitmap rotatedBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (rotatedBM.equals(origin))
            return rotatedBM;
        origin.recycle();
        return rotatedBM;
    }

    /**
     * @param origin the original bitmap
     * @param rect the rect area to clip
     * @return the clipped bitmap
     */
    public static Bitmap clipRect(Bitmap origin, Rect rect)
    {
        if (origin == null)
            return null;
        Bitmap rotatedBM = Bitmap.createBitmap(origin, rect.left, rect.top, rect.width(), rect.height(), null, false);
        if (rotatedBM.equals(origin))
            return rotatedBM;
        origin.recycle();
        return rotatedBM;
    }

    /**
     * @param width container width
     * @param height container height
     * @return the RectF area of the container center aligned
     */
    public static RectF getA4RectF(int width, int height) {
        float rect_width = (float) (width * 0.8);
        float rect_height = (float) (rect_width * 1.41);
        float left = (float) (width * 0.2);
        float top = ((height - rect_height));
        return new RectF(left, top, rect_width, rect_height);
    }

    /**
     * @param width container width
     * @param height container height
     * @return the Rect area of the container center aligned
     */
    public static Rect getA4Rect(int width, int height) {
        int rect_width = (int) (width * 0.8);
        int rect_height = (int) (rect_width * 1.41);
        int left = (int) (width * 0.2);
        int top = ((height - rect_height));
        return new Rect(left, top, rect_width, rect_height);
    }
}
