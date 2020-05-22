package com.coderusk.speedcode.app;

public class Vacast {
    private Object args[] = null;
    private int index = -1;

    private Vacast() {
    }
    public static Vacast from(Object args[])
    {
        Vacast vacast = new Vacast();
        vacast.args = args;
        return vacast;
    }
    public Vacast at(int index)
    {
        this.index = index;
        return this;
    }
    public <T> T get(Class<T> classOfT, T defaultValue)
    {
        if(args!=null)
        {
            if(args.length>0)
            {
                if(index>-1)
                {
                    if(index<args.length)
                    {
                        return classOfT.cast(args[index]);
                    }
                }
            }
        }
        return defaultValue;
    }
}
