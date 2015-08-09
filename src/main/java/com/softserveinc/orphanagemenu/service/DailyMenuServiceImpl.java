package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.TransactionRequiredException;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.dao.RoleDao;
import com.softserveinc.orphanagemenu.dao.UserAccountDao;
import com.softserveinc.orphanagemenu.exception.NotSuccessDBException;
import com.softserveinc.orphanagemenu.forms.UserAccountForm;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.UserAccount;

@Service("dailyMenuService")
@Transactional
public class DailyMenuServiceImpl implements DailyMenuService {

	@Autowired
	@Qualifier("dailyMenuDao")
	private DailyMenuDao dailyMenuDao;
	
	@Override
	public DailyMenu save(DailyMenu dailyMenu) {
		dailyMenuDao.save(dailyMenu);
		return dailyMenu;
	}

	@Override
	public DailyMenu getById(Long id) {
		return dailyMenuDao.getById(id);
	}

	@Override
	public void deleteByID(Long id) {
		dailyMenuDao.delete(dailyMenuDao.getById(id));
	}

	@Override
	public List<DailyMenu> getAllDto(){
		List<DailyMenu> dailyMenus = dailyMenuDao.getAll();
		List<DailyMenu> dailyMenusDto = new ArrayList<>();
		Mapper mapper = new DozerBeanMapper();
		for (DailyMenu dailyMenu : dailyMenus){
			dailyMenusDto.add(mapper.map(dailyMenu, DailyMenu.class));
		}
		return dailyMenusDto;
	}

}
