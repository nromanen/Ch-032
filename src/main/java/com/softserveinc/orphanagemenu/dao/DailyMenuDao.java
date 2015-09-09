package com.softserveinc.orphanagemenu.dao;

import java.util.Date;
import java.util.List;

import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Product;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
public interface DailyMenuDao {
    DailyMenu save(DailyMenu dailyMenu);

    void delete(DailyMenu dailyMenu);

    DailyMenu getById(Long id);

    DailyMenu getByDate(Date date);

    List<DailyMenu> getAll();

    List<DailyMenu> getFromCurrentDateToFutureDate(Date futureDate);

    void updateDailyMenu(DailyMenu dailyMenu);

    Date getDateById(Long id);

    Boolean getDailyMenuAccepted(Long id);

    List<Product> getProductsForDailyMenu(Date date);

    Long createByTemplate(Long id, Date date);

    List<ConsumptionType> getConsumptionTypesForDailyMenu(Date date);

 

}
