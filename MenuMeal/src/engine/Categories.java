package engine;

import java.util.ArrayList;

/**
 * @author pluchtc
 */
public class Categories {
    private static ArrayList<Category> categoryList = new ArrayList<Category>();


    public void addCategory(Category newCategory) {
        this.getCategoryList().add(newCategory);
    }

    public void updateCategory(Category existingCategory, String newName) {
        getCategoryList().get(getCategoryList().indexOf(existingCategory)).setName(newName);
    }

    public Category getCategoryByName(String categoryName) {
        boolean flagForChanging = false;
        int iteratorStorage=0;
        for (int i = 0; i < Categories.categoryList.size(); i++) {
            if (Categories.categoryList.get(i).toString().toString().equals(categoryName)) {
                flagForChanging = true;
                iteratorStorage=i;
            }
        }
        if (flagForChanging) {
            return Categories.categoryList.get(iteratorStorage);
        } else {
            return null;
        }
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public String toString() {
        String output = "";
        for (Category currentCategory : categoryList) {
            output += "[" + currentCategory.toString() + "]" + "\n";
        }
        return output;
    }

    public Category getCategory(int index) {
        return this.categoryList.get(index);
    }

    public void initCategories() {
        Category first = new Category("First");
        Category second = new Category("Second");
        Category third = new Category("Third");
        this.addCategory(first);
        this.addCategory(second);
        this.addCategory(third);
    }
}
