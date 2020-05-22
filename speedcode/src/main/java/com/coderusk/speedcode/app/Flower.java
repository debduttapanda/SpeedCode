package com.coderusk.speedcode.app;

import java.util.HashMap;

public class Flower {
    public interface Action
    {
        void act(Flower flowster,Object ...args);
    }
    /////////////////////////
    HashMap<String, HashMap<String,Action>> nodes;
    private String current = "";
    private String start = "";
    private HashMap<String,Action> currentActions;

    private Flower() {
        this.nodes = new HashMap<>();
    }

    public static Flower create()
    {
        return new Flower();
    }

    public Flower add(Action action)
    {
        if(!current.isEmpty())
        {
            if(currentActions!=null)
            {
                nodes.put(current,currentActions);
                current = "";
                currentActions = null;
            }
        }
        if(action==null)
        {
            return this;
        }
        String hash = action.hashCode()+"";
        current = hash;
        if(start.isEmpty())
        {
            start = current;
        }
        currentActions = new HashMap<>();
        currentActions.put("self",action);

        return this;
    }

    public Flower onNext(Action action)
    {
        return addCase("next",action);
    }

    public Flower onYes(Action action)
    {
        return addCase("yes",action);
    }

    public Flower onNo(Action action)
    {
        return addCase("no",action);
    }

    public Flower addCase(String caseName, Action action)
    {
        if(current!=null)
        {
            if(!current.isEmpty())
            {
                if(currentActions!=null)
                {
                    currentActions.put(caseName,action);
                }
            }
        }
        return this;
    }

    public void execute(Object ...args)
    {
        if(!current.isEmpty())
        {
            if(currentActions!=null)
            {
                nodes.put(current,currentActions);
                current = "";
                currentActions = null;
            }
        }
        if(!start.isEmpty())
        {
            if(nodes!=null)
            {
                if(nodes.containsKey(start))
                {
                    HashMap<String,Action> actions = nodes.get(start);
                    if(actions!=null)
                    {
                        if(actions.size()>0)
                        {
                            if(actions.containsKey("self"))
                            {
                                Action action = actions.get("self");
                                if(action!=null)
                                {
                                    current = start;
                                    action.act(this,args);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void next(Flower flowster,Object ...args)
    {
        Flower.flowCase("next",flowster,args);
    }

    public static void yes(Flower flowster,Object ...args)
    {
        Flower.flowCase("yes",flowster,args);
    }

    public static void no(Flower flowster,Object ...args)
    {
        Flower.flowCase("no",flowster,args);
    }

    public static void flowCase(String caseName, Flower flowster,Object ...args)
    {
        if(flowster!=null)
        {
            if(flowster.current!=null)
            {
                if(!flowster.current.isEmpty())
                {
                    if(flowster.nodes!=null)
                    {
                        if(flowster.nodes.containsKey(flowster.current))
                        {
                            HashMap<String,Action> actions = flowster.nodes.get(flowster.current);
                            if(actions!=null)
                            {
                                if(actions.containsKey(caseName))
                                {
                                    Action action = actions.get(caseName);
                                    if(action!=null)
                                    {
                                        String hash = action.hashCode()+"";
                                        flowster.current = hash;
                                        action.act(flowster,args);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
