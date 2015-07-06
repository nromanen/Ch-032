package engine;

import java.util.ArrayList;

public class Menus {
    private static ArrayList<Menu> menuList = new java.util.ArrayList<Menu>();

    public ArrayList<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(ArrayList<Menu> menuList) {
        this.menuList = menuList;
    }

    public String toString() {
        String output = "";
        for (Menu currentMenu : menuList) {
            output += "[" + currentMenu.toString() + "]" + "\n";
        }
        return output;
    }

    public void addMenu (Menu menu)
    {
        this.menuList.add(menu);
    }
}
