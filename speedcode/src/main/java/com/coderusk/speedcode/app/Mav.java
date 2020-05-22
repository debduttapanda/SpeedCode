package com.coderusk.speedcode.app;

import java.util.ArrayList;

public class Mav {
    public interface Validity
    {
        boolean valid();
    }

    class ValRun
    {
        Validity validity = null;
        Runnable runnable =  null;

        public ValRun(Validity validity, Runnable runnable) {
            this.validity = validity;
            this.runnable = runnable;
        }
    }

    ArrayList<ValRun> data = new ArrayList<>();

    private Mav() {
    }

    public static Mav create()
    {
        return new Mav();
    }

    public Mav add(Validity validity, Runnable runnable)
    {
        data.add(new ValRun(validity,runnable));
        return this;
    }

    public boolean execute()
    {
        boolean ret = true;
        int len = data.size();
        for(int i=0;i<len;++i)
        {
            ValRun valRun = data.get(i);
            if(valRun!=null)
            {
                Validity validity = valRun.validity;
                if(validity!=null)
                {
                    if(!validity.valid())
                    {
                        ret = false;
                        Runnable runnable = valRun.runnable;
                        if(runnable!=null)
                        {
                            runnable.run();
                        }
                        break;
                    }
                }
            }
        }
        return ret;
    }
}
