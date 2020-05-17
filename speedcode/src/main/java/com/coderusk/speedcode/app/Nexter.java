package com.coderusk.speedcode.app;

import java.util.ArrayList;

public class Nexter
    {
        public interface Action
        {
            void act(Nexter nexter);
        }

        private int pos = -1;
        private ArrayList<Action> actions;

        private Nexter() {
            this.actions = new ArrayList<>();
        }

        public static Nexter create()
        {
            return new Nexter();
        }

        public Nexter push(Action action)
        {
            actions.add(action);
            return this;
        }

        public void execute()
        {
            Nexter.nexec(this);
        }

        public Action next()
        {
            ++pos;
            if(pos>actions.size()-1)
            {
                return null;
            }
            return actions.get(pos);
        }

        public static void nexec(Nexter nexter)
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
    }