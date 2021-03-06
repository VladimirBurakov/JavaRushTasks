package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void update(Observable o, Object arg) {
        ConsoleHelper.writeMessage("Start cooking - " + arg);
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(arg.toString(), name, ((Order)arg).getTotalCookingTime() * 60, ((Order) arg).getDishes()));
        //StatisticManager.getInstance().registerMockData();  // for fake data, remove this
        setChanged();
        notifyObservers(arg);
    }
}
