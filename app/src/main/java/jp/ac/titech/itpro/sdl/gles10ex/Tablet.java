package jp.ac.titech.itpro.sdl.gles10ex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Tablet implements SimpleRenderer.Obj {

    private FloatBuffer vbuf, cbuf;
    private float x, y, z;

    public Tablet(float s, float x, float y, float z) {
        float[] vertices = {
                // left
                -s, -s*1.5f, -s/5,
                -s, -s*1.5f, s/5,
                -s, s*1.5f, -s/5,
                -s, s*1.5f, s/5,
                // right
                s, -s*1.5f, -s/5,
                s, -s*1.5f, s/5,
                s, s*1.5f, -s/5,
                s, s*1.5f, s/5,
                // bottom
                -s, -s*1.5f, -s/5,
                s, -s*1.5f, -s/5,
                -s, -s*1.5f, s/5,
                s, -s*1.5f, s/5,
                // top
                -s, s*1.5f, -s/5,
                s, s*1.5f, -s/5,
                -s, s*1.5f, s/5,
                s, s*1.5f, s/5,
                // back
                -s, -s*1.5f, -s/5,
                -s, s*1.5f, -s/5,
                s, -s*1.5f, -s/5,
                s, s*1.5f, -s/5,
                // front
                -s, -s*1.5f, s/5,
                -s, s*1.5f, s/5,
                s, -s*1.5f, s/5,
                s, s*1.5f, s/5,

//                -s, s*1.5f, s/4
                -s*0.8f, -s*1.3f, s/2,
                -s*0.8f, s*1.3f, s/2,
                s*0.8f, -s*1.3f, s/2,
                s*0.8f, s*1.3f, s/2

        };
        float[] colors = {
                0.8f, 0f, 0f,
                0.8f, 0f, 0f,
                0.8f, 0f, 0f,
                0.8f, 0f, 0f,

                0.8f, 0f, 0f,
                0.8f, 0f, 0f,
                0.8f, 0f, 0f,
                0.8f, 0f, 0f,

                0.8f, 0f, 0f,
                0.8f, 0f, 0f,
                0.8f, 0f, 0f,
                0.8f, 0f, 0f,

                0.8f, 0f, 0f,
                0.8f, 0f, 0f,
                0.8f, 0f, 0f,
                0.8f, 0f, 0f,

                0.8f, 0f, 0f,
                0.8f, 0f, 0f,
                0.8f, 0f, 0f,
                0.8f, 0f, 0f,

                0.8f, 0f, 0f,
                0.8f, 0f, 0f,
                0.8f, 0f, 0f,
                0.8f, 0f, 0f,

                0.8f, 0f, 0f,
                0.8f, 0f, 0f,
                0.8f, 0f, 0f,
                0.8f, 0f, 0f,
        };
        vbuf = ByteBuffer.allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vbuf.put(vertices);
        vbuf.position(0);
        cbuf = ByteBuffer.allocateDirect(colors.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        cbuf.put(colors);
        cbuf.position(0);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw(GL10 gl) {
       // gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbuf);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(3, GL10.GL_FLOAT, 0, cbuf);

        // left
        gl.glNormal3f(-1, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        // right
        gl.glNormal3f(1, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);

        // bottom
        gl.glNormal3f(0, -1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);

        // top
        gl.glNormal3f(0, 1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);

        // rear
        gl.glNormal3f(0, 0, -1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);

        // front
        gl.glNormal3f(0, 0, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);

        gl.glNormal3f(0, 0, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 24, 4);

        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

    }
    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getZ() {
        return z;
    }
}
