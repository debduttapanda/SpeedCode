package com.coderusk.speedcode.app;

import java.util.HashMap;

public class DataBank {
    HashMap<String,Object> datas;

    public DataBank() {
        this.datas = new HashMap<>();
    }

    public void set(String key, Object value)
    {
        datas.put(key,value);
    }

    public Object get(String key)
    {
        if(datas.containsKey(key))
        {
            return datas.get(key);
        }
        else
        {
            return null;
        }
    }

    public void remove(String key)
    {
        if(datas.containsKey(key))
        {
            datas.remove(key);
        }
    }

    public Object poll(String key)
    {
        Object object = get(key);
        remove(key);
        return object;
    }
}
