package com.k9sv.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.k9sv.domain.pojo.Product;

public class ProductCache {

	/**
	 * 产品
	 */
	public static Map<Integer, Product> Products = new ConcurrentHashMap<Integer, Product>();

}
