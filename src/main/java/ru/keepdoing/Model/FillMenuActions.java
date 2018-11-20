package ru.keepdoing.Model;

import ru.keepdoing.Controller.DBQueries;
import ru.keepdoing.Model.Menu.AbstractMenuItem;
import ru.keepdoing.Model.Menu.MenuBuilder;
import ru.keepdoing.Model.Menu.MenuWrapper;
import ru.keepdoing.View.GUIHelper;

import java.time.LocalDate;

public class FillMenuActions {

    private static final String REMOVE_ITEM = "Remove";
    private static final String ROOT_MENU = "Root menu";
    private static final String SHOW_TASKS = "Show tasks";
    private static final String ALL_TASKS_MSG = "Here is all your tasks";
    private static final String SELECT_TASK = "Select and work";
    private static final String CHOOSE_TASK_TO_WORK = "Select the task to work with";
    private static final String ADD_ITEM = "Add new item";
    private static final String ENTER_TASK_NAME = "Enter new task";
    private static final String ENTER_TASK_TYPE = "Select new task type";
    private static final String ENTER_TASK_STATUS = "Select task status";
    private static final String ENTER_DEADLINE = "Enter deadline date";
    private static final String QUIT = "Quit";
    private static final String EDIT_TASK_SUBMENU = "What you want to do?";
    private static final String CHANGE_STATUS = "Change status";
    private static final String ENTER_NEW_STATUS = "Enter status id that you want";

    private final DBQueries dbQueries;
    private final MenuWrapper menu;
    private MenuBuilder rootMenu, subMenu;
    private int choice;


    public FillMenuActions(final MenuWrapper menu, final DBQueries dbQueries) {
        this.dbQueries = dbQueries;
        this.menu = menu;

        fillRootMenu();
        fillSubmenu();
    }


    public void fillRootMenu() {
        rootMenu = menu.addMenu(ROOT_MENU);
        menu.setRootMenu(rootMenu);

        menu.addActionToMenu(rootMenu, new AbstractMenuItem(SHOW_TASKS) {
            @Override
            public void run() {
                GUIHelper.printData(dbQueries.getTasksView(), ALL_TASKS_MSG);
            }
        });

        menu.addActionToMenu(rootMenu, new AbstractMenuItem(SELECT_TASK) {
            @Override
            public void run() {
                GUIHelper.printData(dbQueries.getTasksView());
                choice = GUIHelper.askInteger(CHOOSE_TASK_TO_WORK);
                menu.callSubMenu(subMenu);
            }
        });

        menu.addActionToMenu(rootMenu, new AbstractMenuItem(ADD_ITEM) {
            @Override
            public void run() {
                String newTask = GUIHelper.askString(ENTER_TASK_NAME);
                GUIHelper.printData(dbQueries.getTypes());
                int newTaskType = GUIHelper.askInteger(ENTER_TASK_TYPE);
                GUIHelper.printData(dbQueries.getStatuses());
                int newTaskStatus = GUIHelper.askInteger(ENTER_TASK_STATUS);
                LocalDate date = GUIHelper.askDate(ENTER_DEADLINE);
                dbQueries.addTask(newTask, newTaskType, newTaskStatus, LocalDate.now().toString(), date.toString());
            }
        });

        menu.addActionToMenu(rootMenu, new AbstractMenuItem(QUIT) {
            @Override
            public void run() {
                System.exit(0);
            }
        });

    }

    public void fillSubmenu() {
        subMenu = menu.addSubMenu(EDIT_TASK_SUBMENU, rootMenu);

        menu.addActionToMenu(subMenu, new AbstractMenuItem(CHANGE_STATUS) {
            @Override
            public void run() {
                GUIHelper.printData(dbQueries.getStatuses());
                dbQueries.changeTaskStatus(choice, GUIHelper.askInteger(ENTER_NEW_STATUS));
                menu.callSubMenu(rootMenu);
            }
        });

        menu.addActionToMenu(subMenu, new AbstractMenuItem(REMOVE_ITEM) {
            @Override
            public void run() {
                dbQueries.removeTask(choice);
                menu.callSubMenu(rootMenu);
            }
        });
    }

}
