package com.coderusk.speedcode.app;

import java.util.HashMap;

public class Flowster {
    public interface Action
    {
        void act(Flowster flowster);
    }
    /////////////////////////
    HashMap<String, HashMap<String,Action>> nodes;
    private String current = "";
    private String start = "";
    private HashMap<String,Action> currentActions;

    private Flowster() {
        this.nodes = new HashMap<>();
    }

    public static Flowster create()
    {
        return new Flowster();
    }

    public Flowster add(Action action)
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

    public Flowster onNext(Action action)
    {
        return addCase("next",action);
    }

    public Flowster onYes(Action action)
    {
        return addCase("yes",action);
    }

    public Flowster onNo(Action action)
    {
        return addCase("no",action);
    }

    public Flowster addCase(String caseName, Action action)
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

    public void execute()
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
                                    action.act(this);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void next(Flowster flowster)
    {
        Flowster.flowCase("next",flowster);
    }

    public static void yes(Flowster flowster)
    {
        Flowster.flowCase("yes",flowster);
    }

    public static void no(Flowster flowster)
    {
        Flowster.flowCase("no",flowster);
    }

    public static void flowCase(String caseName,Flowster flowster)
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
                                        action.act(flowster);
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
