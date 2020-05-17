package com.coderusk.speedcode.app;

import java.util.HashMap;

public class Fexter
    {
        class ActionSet
        {
            Action action = null;
            String normal = "";
            String yes = "";
            String no = "";

            public ActionSet(Action action,String normal, String yes, String no) {
                this.action = action;
                this.normal = normal;
                this.yes = yes;
                this.no = no;
            }

            public ActionSet() {
            }
        }
        String pos = "";
        ///////////////////////
        public interface Action
        {
            void act(Fexter nexter);
        }
        private HashMap<String, ActionSet> actions;

        private Fexter() {
            this.actions = new HashMap<>();
        }

        public static Fexter create()
        {
            return new Fexter();
        }

        public Fexter add(String key,Action action)
        {
            if(pos.isEmpty())
            {
                pos = key;
            }
            actions.put(key,new ActionSet(action,"","",""));
            return this;
        }
        public Fexter add(String key,Action action,String normal)
        {
            if(pos.isEmpty())
            {
                pos = key;
            }
            actions.put(key,new ActionSet(action,normal,"",""));
            return this;
        }
        public Fexter add(String key,Action action,String yes,String no)
        {
            if(pos.isEmpty())
            {
                pos = key;
            }
            actions.put(key,new ActionSet(action,"",yes,no));
            return this;
        }

        public void execute()
        {
            Fexter.furrent(this);
        }

        private Action current()
        {
            if(pos!=null) {
                if (!pos.isEmpty()) {
                    if (actions.containsKey(pos)) {
                        ActionSet actionSet = actions.get(pos);
                        if (actionSet != null) {
                            return actionSet.action;
                        }
                    }
                }
            }
            return null;
        }

        public Action next()
        {
            if(pos!=null)
            {
                if(!pos.isEmpty())
                {
                    if(actions.containsKey(pos))
                    {
                        ActionSet actionSet = actions.get(pos);
                        if(actionSet!=null)
                        {
                            String nextActionKey = actionSet.normal;
                            if(nextActionKey!=null)
                            {
                                if(!nextActionKey.isEmpty())
                                {
                                    if(actions.containsKey(nextActionKey))
                                    {
                                        ActionSet actionSetNext = actions.get(nextActionKey);
                                        if(actionSetNext!=null)
                                        {
                                            pos = nextActionKey;
                                            return actionSetNext.action;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            pos = "";
            return null;
        }

        public Action yes()
        {
            if(pos!=null)
            {
                if(!pos.isEmpty())
                {
                    if(actions.containsKey(pos))
                    {
                        ActionSet actionSet = actions.get(pos);
                        if(actionSet!=null)
                        {
                            String nextActionKey = actionSet.yes;
                            if(nextActionKey!=null)
                            {
                                if(!nextActionKey.isEmpty())
                                {
                                    if(actions.containsKey(nextActionKey))
                                    {
                                        ActionSet actionSetNext = actions.get(nextActionKey);
                                        if(actionSetNext!=null)
                                        {
                                            pos = nextActionKey;
                                            return actionSetNext.action;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            pos = "";
            return null;
        }

        public Action no()
        {
            if(pos!=null)
            {
                if(!pos.isEmpty())
                {
                    if(actions.containsKey(pos))
                    {
                        ActionSet actionSet = actions.get(pos);
                        if(actionSet!=null)
                        {
                            String nextActionKey = actionSet.no;
                            if(nextActionKey!=null)
                            {
                                if(!nextActionKey.isEmpty())
                                {
                                    if(actions.containsKey(nextActionKey))
                                    {
                                        ActionSet actionSetNext = actions.get(nextActionKey);
                                        if(actionSetNext!=null)
                                        {
                                            pos = nextActionKey;
                                            return actionSetNext.action;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            pos = "";
            return null;
        }

        private static void furrent(Fexter nexter)
        {
            if(nexter!=null)
            {
                Action listener = nexter.current();
                if(listener!=null)
                {
                    listener.act(nexter);
                }
            }
        }

        public static void fexec(Fexter nexter)
        {
            if(nexter!=null)
            {
                Action listener = nexter.next();
                if(listener!=null)
                {
                    listener.act(nexter);
                }
            }
        }

        public static void fexec_y(Fexter nexter)
        {
            if(nexter!=null)
            {
                Action listener = nexter.yes();
                if(listener!=null)
                {
                    listener.act(nexter);
                }
            }
        }

        public static void fexec_n(Fexter nexter)
        {
            if(nexter!=null)
            {
                Action listener = nexter.no();
                if(listener!=null)
                {
                    listener.act(nexter);
                }
            }
        }
    }