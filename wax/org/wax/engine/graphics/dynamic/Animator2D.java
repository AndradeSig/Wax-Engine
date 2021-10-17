package org.wax.engine.graphics.dynamic;

import org.wax.engine.Wax;
import org.wax.engine.graphics.Mesh;

import java.util.ArrayList;
import java.util.HashMap;

public class Animator2D {

    private Mesh mesh;
    private ArrayList<String[]> t_paths;

    int cAnim = 0;
    int fAnim = 0;

    public Animator2D()
    {
        t_paths = new ArrayList<>();
    }

    public void getMesh(Mesh mesh)
    {
        this.mesh = mesh;
    }

    public void setAnimation(String[] paths)
    {
        t_paths.add(paths);
    }

    public void playAnimation(int index_path, int speed)
    {
        fAnim++;
        if(fAnim > speed){
            fAnim = 0;
            cAnim++;
            if(cAnim > t_paths.get(index_path).length - 1)
                cAnim = 0;
        }
        mesh.setTexture(t_paths.get(index_path)[cAnim], true);
    }

    public void stopAnimation(int index_path, int index)
    {
        cAnim = index;
    }
}
