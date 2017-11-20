package com.k9sv.service;

import com.k9sv.domain.pojo.Profile;

public interface IGoldManager {
	/**
	 * 取要添加的金币数
	 * 
	 * @param commentGold
	 * 
	 * @param uid
	 *            用户id
	 * @param fid
	 *            动态id
	 * @param type
	 *            类型 0为评论
	 * @param typeGold
	 *            类型金币数
	 * @return
	 */
	int getGold(Integer uid, Integer fid, Integer type, Integer typeGold);

	/**
	 * 
	 * @param profile
	 * @param fid
	 * @param fid
	 * @param gold
	 */
	void addGold(Profile profile, Integer fid, Integer type, int gold);

	void addGold(Profile profile, Integer type, int gold);

	int getGold(Integer id, Integer type, Integer typeGold);

}
