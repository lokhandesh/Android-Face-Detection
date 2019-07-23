
package com.example.santoshlokhande.facedetectionandroid.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.santoshlokhande.facedetectionandroid.R;
import com.example.santoshlokhande.facedetectionandroid.activity.FaceTrackerActivity;
import com.google.android.gms.vision.face.Face;

/**
 * This class is for rendering face position, orientation, and landmarks within an associated
 * graphic overlay view.
 */
public class FaceGraphic extends GraphicOverlay.Graphic {
    private static final float FACE_POSITION_RADIUS = 10.0f;
    private static final float ID_TEXT_SIZE = 40.0f;
    private static final float ID_Y_OFFSET = 50.0f;
    private static final float ID_X_OFFSET = -50.0f;
    private static final float BOX_STROKE_WIDTH = 5.0f;

    public static float leftFrom,topFrom;

    private static final int COLOR_CHOICES[] = {
        Color.BLUE
    };
    private static int mCurrentColorIndex = 0;

    private Paint mFacePositionPaint;
    private Paint mIdPaint;
    private Paint mBoxPaint;

    private volatile Face mFace;
    private int mFaceId;
    private float mFaceHappiness;
    private Context context;

    public FaceGraphic(GraphicOverlay overlay, FaceTrackerActivity faceTrackerActivity) {
        super(overlay);

        mCurrentColorIndex = (mCurrentColorIndex + 1) % COLOR_CHOICES.length;
        final int selectedColor = COLOR_CHOICES[mCurrentColorIndex];

        mFacePositionPaint = new Paint();
        mFacePositionPaint.setColor(selectedColor);

        mIdPaint = new Paint();
        mIdPaint.setColor(selectedColor);
        mIdPaint.setTextSize(ID_TEXT_SIZE);

        mBoxPaint = new Paint();
        mBoxPaint.setColor(selectedColor);
        mBoxPaint.setStyle(Paint.Style.STROKE);
       // mBoxPaint.
        mBoxPaint.setStrokeWidth(BOX_STROKE_WIDTH);

        this.context=faceTrackerActivity;

    }

    public void setId(int id) {
        mFaceId = id;
    }


    /**
     * Updates the face instance from the detection of the most recent frame.  Invalidates the
     * relevant portions of the overlay to trigger a redraw.
     */
    public void updateFace(Face face) {
        mFace = face;
        postInvalidate();
    }

    /**
     * Draws the face annotations for position on the supplied canvas.
     */

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void draw(Canvas canvas) {
        Face face = mFace;
        /*if (face == null) {
            return;
        }*/

        mBoxPaint.setStyle(Paint.Style.STROKE);
        mBoxPaint.setColor(Color.GREEN);
        mBoxPaint.setStrokeWidth(10);

        //center
        int x0 = canvas.getWidth()/2;
        int y0 = canvas.getHeight()/2;
        int dx = canvas.getHeight()/4;
        int lx = canvas.getWidth()/4;

       // canvas.drawRect(x0-lx, y0-dx, x0+lx, y0+dx, mBoxPaint);

        float x = translateX(face.getPosition().x + face.getWidth() / 2);
        float y = translateY(face.getPosition().y + face.getHeight() / 2);

        float xOffset = scaleX(face.getWidth() / 2.0f);
        float yOffset = scaleY(face.getHeight() / 2.0f);

        float left = x - xOffset;
        float top = y - yOffset;
        float right = x + xOffset;
        float bottom = y + yOffset;

        float dxOffset = scaleX(face.getWidth() / 4);
        float dyOffset = scaleY(face.getHeight() / 4);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap source = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.face, options);


        Utility.CLETFT= x0-lx;
        Utility.CRIGHT=x0+lx;
        Utility.CTOP=y0-dx;
        Utility.CBOTTOM=y0+dx;
        Utility.FLETFT= (int)(x - xOffset);
        Utility.FRIGHT=(int)(x + xOffset);
        Utility.FTOP=(int)(y - yOffset);
        Utility.FBOTTOM=(int)(y + yOffset);

    }

}
