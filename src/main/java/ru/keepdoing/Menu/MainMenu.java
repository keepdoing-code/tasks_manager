package ru.keepdoing.Menu;

import java.util.HashMap;

public class MainMenu {
    private HashMap<Integer, AbstractMenuItem> items = new HashMap<>();

    private final String menuName;

    public MainMenu(String menuName) {
        this.menuName = menuName;
    }

    public void run(int menuNum) {
        //TODO check error when wrong number entered
        //TODO may be use user exception
        items.get(menuNum).run();
    }

    public void add(int itemNumber, AbstractMenuItem item) {
        items.put(itemNumber, item);
    }

    public String getText() {
        StringBuilder sb = new StringBuilder(this.menuName);
        sb.append('\n');

        for (Integer i : items.keySet()) {
            sb
                    .append('\t')
                    .append(i)
                    .append(":\t")
                    .append(items.get(i).getName())
                    .append('\n');
        }
        return sb.toString();
    }

}
