package jp.ac.titech.itpro.sdl.gles10ex;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private final static String TAG = "MainActivity";

    private GLSurfaceView glView;
    private SimpleRenderer renderer;
    private SeekBar rotationBarX, rotationBarY, rotationBarZ;

    private int px = 0, py = 0, pz = 0;
    private float baseX, baseY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        glView = (GLSurfaceView) findViewById(R.id.glview);

        rotationBarX = (SeekBar) findViewById(R.id.rotation_bar_x);
        rotationBarY = (SeekBar) findViewById(R.id.rotation_bar_y);
        rotationBarZ = (SeekBar) findViewById(R.id.rotation_bar_z);
        rotationBarX.setOnSeekBarChangeListener(this);
        rotationBarY.setOnSeekBarChangeListener(this);
        rotationBarZ.setOnSeekBarChangeListener(this);

        renderer = new SimpleRenderer();
        renderer.addObj(new Cube(0.5f, 0, 0.2f, -3));
        renderer.addObj(new Pyramid(0.5f, 0, 0, 0));
        renderer.addObj(new Tablet(0.8f, 1.0f, 0.8f, -2));
        glView.setRenderer(renderer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        glView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        glView.onPause();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == rotationBarX){
            px = progress;
            renderer.setRotationX(progress);
        }
        else if (seekBar == rotationBarY){
            py = progress;
            renderer.setRotationY(progress);
        }
        else if (seekBar == rotationBarZ){
            pz = progress;
            renderer.setRotationZ(progress);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX() / 10, ey = event.getY() / 10;
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                baseX = ex;
                baseY = ey;
                break;
            case MotionEvent.ACTION_MOVE:
                float diffX = ex - baseX, diffY = ey - baseY;
                baseX = ex;
                baseY = ey;
                if(0 <= px + diffY && px + diffY <= 360){
                    rotationBarX.setProgress((int)(px + diffY));
                    renderer.setRotationX(px + diffY);
                }else if(px + diffY > 360){
                    rotationBarX.setProgress(0);
                    renderer.setRotationX(0);
                }else{
                    rotationBarX.setProgress(360);
                    renderer.setRotationX(360);
                }

                if(0 <= py - diffX && py - diffX <= 360){
                    rotationBarY.setProgress((int)(py - diffX));
                    renderer.setRotationY(py - diffX);
                }else if(py - diffX > 360){
                    rotationBarY.setProgress(0);
                    renderer.setRotationY(0);
                }else{
                    rotationBarY.setProgress(360);
                    renderer.setRotationY(360);
                }
//                Log.d(TAG, "x :" + (int)(px + diffY));
            break;
            case MotionEvent.ACTION_UP:
                break;
        }
//        renderer.setRotationX(px + 10);
        return super.onTouchEvent(event);
    }



    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

}
