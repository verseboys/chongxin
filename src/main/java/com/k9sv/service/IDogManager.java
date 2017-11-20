package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Dog;
import com.k9sv.domain.pojo.DogComment;
import com.k9sv.domain.pojo.DogPhoto;

public interface IDogManager extends IBaseManager {

	void saveDogPhoto(DogPhoto photo);

	Dog getDog(int id);

	List<Dog> getDogs(int deleted, int start, int size);

	List<Dog> getCommandDogs(int command, int start, int size);

	int getDogCount(int deleted);

	int getPetCount(Integer ownerId, Integer deleted);

	/**
	 * 取后代犬
	 * 
	 * @param DogId
	 * @param start
	 * @param size
	 * @return
	 */
	List<Dog> getChildrenDogs(int dogId, int start, int size);

	int getChildrenDogCount(int dogId);

	/**
	 * 同父犬
	 * 
	 * @param DogId
	 * @param start
	 * @param size
	 * @return
	 */
	List<Dog> getSameFatherDogs(int dogId, int start, int size);

	int getSameFatherDogCount(int dogId);

	/**
	 * 同母犬
	 * 
	 * @param DogId
	 * @param start
	 * @param size
	 * @return
	 */
	List<Dog> getSameMatherDogs(int dogId, int start, int size);

	int getSameMatherDogCount(int dogId);

	List<Dog> getCatDog(int catid, int start, int size);

	List<Dog> getUserDogs(int uid, int start, int size);

	List<Dog> getUserDogs(int uid, int sex, int start, int size);

	List<Dog> getUserTradeDogs(int uid, int start, int size);

	List<DogPhoto> getDogPhotos(int dogid, int start, int size);

	int getDogPhotoCount(int dogId);

	List<DogPhoto> getUserPhoto(int uid, int start, int size);

	int getUserPhotoCount(int uid);

	int saveDogComment(DogComment dogComment);

	List<DogComment> getDogComments(int dogId, int zan, int start, int size);

	int getDogCount(int dogId, int zan);

	int getDogZan(int uid, int dogId);

	int removeDogZan(int uid, int dogId);

	int saveDogFavorite(int uid, int dogId);

	List<Dog> getFavoriteDogs(int uid, int start, int size);

	int getFavoriteDogCount(int uid);

	int deleteFavorited(int uid, int dogId);

	int getDogFavorite(int uid, int dogId);

	List<DogPhoto> getDogPhotos(int start, int size);

	Integer delete(Integer id, Account _account);

	Integer getCount(String title);

	List<Dog> getDogs(String title, Integer pageNum, Integer numPerPage);

	void saveOrUpdate(Dog dog);

	Dog getdog(Integer did);

	Integer getCount(String title, Account account);

	List<Dog> getDogs(String title, Account account, Integer pageNum, Integer numPerPage);

	/**
	 * 用户名下宠物名是否已经存在
	 * 
	 * @param _account
	 * @param name
	 * @param petid
	 * @return
	 */
	Dog checkDogName(Account _account, String name, int petid);

	/**
	 * 是不是某人的宠物
	 * 
	 * @param petid
	 * @param _account
	 * @return
	 */
	Dog checkDog(Integer petid, Account _account, Integer touid);

	/**
	 * 除自己外的宠物
	 * 
	 * @param title
	 * @param dogid
	 * @return
	 */
	Integer getCount(String title, Integer dogid);

	/**
	 * 除自己外的宠物
	 * 
	 * @param title
	 * @param dogid
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<Dog> getDogs(String title, Integer dogid, Integer pageNum, Integer numPerPage);

	Integer getCount2(String title, Account account, int havablood);

	List<Dog> getDogs2(String title, Account account, int havablood, Integer pageNum, Integer numPerPage);

	int getDogByBlood(String blood);

	Dog getDog(String blood);

	/**
	 * 芯片号查询
	 * 
	 * @param blood
	 * @return
	 */
	Dog getDogBuyBlood(String blood);

}
