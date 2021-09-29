package org.wax.engine.IO;

import org.wax.engine.staticConfigs.WaxModel;

public class WaxListener{

    private WaxModel target;

    public void toListener(WaxModel target)
    {
         this.target = target;
    }

    public void run(WaxWindow window)
    {
        target.start();
        while(window.isOpen()){
            target.update();
            target.draw();
        }
        window.destroy();
    }
}
