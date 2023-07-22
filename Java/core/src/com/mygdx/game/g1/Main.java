package com.mygdx.game.g1;

import com.mygdx.game.g1.units.BasicHero;
import com.mygdx.game.g1.units.Crossbowman;
import com.mygdx.game.g1.units.Mag;
import com.mygdx.game.g1.units.Monk;
import com.mygdx.game.g1.units.NamesHeros;
import com.mygdx.game.g1.units.Pikeman;
import com.mygdx.game.g1.units.Robber;
import com.mygdx.game.g1.units.Rustic;
import com.mygdx.game.g1.units.Sniper;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public ArrayList<BasicHero> aliance = new ArrayList<>();
    public ArrayList<BasicHero> empire = new ArrayList<>();
    public ArrayList<BasicHero> unitedTeam = new ArrayList<>();

    public Main() {
        for (int i = 1; i < 11; i++) {
            int j = new Random().nextInt(4);
            int k = new Random().nextInt(4);
            switch (j) {
                case 0:
                    aliance.add(new Mag(NamesHeros.getName(), 1, i));
                    break;
                case 1:
                    aliance.add(new Robber(NamesHeros.getName(), 1, i));
                    break;
                case 2:
                    aliance.add(new Sniper(NamesHeros.getName(), 1, i));
                    break;
                default:
                    aliance.add(new Rustic(NamesHeros.getName(), 1, i));

            }
            switch (k) {
                case 0:
                    empire.add(new Monk(NamesHeros.getName(), 10, i));
                    break;
                case 1:
                    empire.add(new Pikeman(NamesHeros.getName(), 10, i));
                    break;
                case 2:
                    empire.add(new Crossbowman(NamesHeros.getName(), 10, i));
                    break;
                default:
                    empire.add(new Rustic(NamesHeros.getName(), 10, i));

            }
        }


        // Scanner scan = new Scanner(System.in);

        unitedTeam.addAll(aliance);
        unitedTeam.addAll(empire);
        unitedTeam.sort(new Comparator<BasicHero>() {
            @Override
            public int compare(BasicHero t0, BasicHero t1) {
                return t1.getInitiativa() - t0.getInitiativa();
            }
        });

    }

    public boolean step() {
    int deadCountAlliance = 0;
    int deadCountEmpire = 0;
    boolean endGame = false;

        // View.view();
            for (BasicHero item : unitedTeam) {
                if (empire.contains(item)) {
                    item.step(aliance, empire);
                } else {
                    item.step(empire, aliance);
                }
            }

            for (BasicHero item : aliance){
                if(item.getHealthLevel()<=0)
                    deadCountAlliance +=1;
                if (deadCountAlliance == aliance.size()){
                    endGame = true;
                }
            }
            for (BasicHero item : empire){
                if(item.getHealthLevel()<=0)
                    deadCountEmpire +=1;
                if (deadCountEmpire == empire.size()){
                    endGame = true;
                }

            //  View.view();
        }

    return endGame;
    }
}