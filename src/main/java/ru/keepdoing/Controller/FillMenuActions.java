package ru.keepdoing.Controller;

import ru.keepdoing.Model.AbstractMenuItem;
import ru.keepdoing.Model.MenuBuilder;
import ru.keepdoing.Model.MenuWrapper;
import ru.keepdoing.View.GUIHelper;

import java.time.LocalDate;

public class FillMenuActions {

    private static final String REMOVE_ITEM = "Remove task";
    private static final String ROOT_MENU = "Main menu";
    private static final String SHOW_TASKS = "Show tasks";
    private static final String ALL_TASKS_MSG = "Here is all your tasks";
    private static final String SELECT_TASK = "Select and work";
    private static final String CHOOSE_TASK_TO_WORK = "Select from the table task id to work. Enter 0 to go back";
    private static final String ADD_ITEM = "Add new item";
    private static final String ENTER_TASK_NAME = "Enter new task";
    private static final String ENTER_TASK_TYPE = "Select new task type";
    private static final String ENTER_TASK_STATUS = "Select new task status";
    private static final String ENTER_DEADLINE = "Enter deadline date. Separately day, month and year ";
    private static final String QUIT = "Quit";
    private static final String EDIT_TASK_SUBMENU = "What you want to do?";
    private static final String CHANGE_STATUS = "Change task status";
    private static final String ENTER_NEW_STATUS = "Enter status id that you want";
    private static final String GO_BACK = "Go back";
    private static final String SYSTEM_SUBMENU = "System menu";
    private static final String DROP_DATABASE = "Clear database";

    private final DBQueries dbQueries;
    private final MenuWrapper menu;
    private MenuBuilder rootMenu, subMenu, sysMenu;
    private int choice;


    public FillMenuActions(final MenuWrapper menu, final DBQueries dbQueries) {
        this.dbQueries = dbQueries;
        this.menu = menu;

        fillRootMenu();
        fillSubmenu();
        fillSysMenu();
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
                if (choice == 0) menu.callSubMenu(rootMenu);
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

        menu.addActionToMenu(rootMenu, new AbstractMenuItem(SYSTEM_SUBMENU) {
            @Override
            public void run() {
                menu.callSubMenu(sysMenu);
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
        subMenu = menu.addSubMenu(EDIT_TASK_SUBMENU, GO_BACK, rootMenu);

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

    public void fillSysMenu() {
        sysMenu = menu.addSubMenu(SYSTEM_SUBMENU, GO_BACK, rootMenu);

        menu.addActionToMenu(sysMenu, new AbstractMenuItem(DROP_DATABASE) {
            @Override
            public void run() {
                dbQueries.dropTables();
                dbQueries.createTables();
                dbQueries.fillTables();
                menu.callSubMenu(rootMenu);
            }
        });
    }

}
