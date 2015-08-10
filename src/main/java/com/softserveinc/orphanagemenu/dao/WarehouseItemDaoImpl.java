﻿package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.WarehouseItem;

@Repository("WarehouseItemDao")
@Transactional
public class WarehouseItemDaoImpl implements WarehouseItemDao {

	@PersistenceContext
	private EntityManager em;

	public WarehouseItem getItem(Long id) {
		return em.find(WarehouseItem.class, id);
	}
	
	public List<WarehouseItem> getAll() {
		String sql = " SELECT wi FROM WarehouseItem wi WHERE wi.quantity != 0 order by wi.product.name ASC ";
		return em.createQuery(sql, WarehouseItem.class).getResultList();
	}

	public Long getCount() {
		String sql = "SELECT Count(wi) FROM WarehouseItem wi WHERE wi.quantity != 0";
		return em.createQuery(sql, Long.class).getSingleResult();
	}

	public Long getCount(String name) {
		String sql = "SELECT Count(wi) FROM WarehouseItem wi WHERE LOWER(wi.product.name) LIKE :searchKeyword and wi.quantity != 0";
		return em.createQuery(sql, Long.class).setParameter("searchKeyword", "%" + name.trim().toLowerCase() + "%").getSingleResult();
	}

	public List<WarehouseItem> getPage(Integer offset, Integer count) {
		String sql = " SELECT wi FROM WarehouseItem wi WHERE wi.quantity != 0 order by wi.product.name ASC ";
		return em.createQuery(sql, WarehouseItem.class).setFirstResult(offset).setMaxResults(count).getResultList();
	}

	@Override
	public List<WarehouseItem> getPage(String name, Integer offset, Integer count) {
		String sql = " SELECT wi FROM WarehouseItem wi WHERE LOWER(wi.product.name) LIKE  :searchKeyword  and wi.quantity != 0 "
				+ "order by wi.product.name ASC";
		return em.createQuery(sql, WarehouseItem.class).setParameter("searchKeyword", "%" + name.toLowerCase().trim() + "%")
				.setFirstResult(offset).setMaxResults(count).getResultList();
	}
	
}
