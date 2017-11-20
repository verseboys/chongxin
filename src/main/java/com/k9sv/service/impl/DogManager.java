package com.k9sv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.k9sv.Config;
import com.k9sv.domain.dto.PushDto;
import com.k9sv.domain.dto.PushTransDto;
import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Dog;
import com.k9sv.domain.pojo.DogComment;
import com.k9sv.domain.pojo.DogFavorite;
import com.k9sv.domain.pojo.DogPhoto;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.domain.pojo.TransRecord;
import com.k9sv.service.IDogManager;
import com.k9sv.util.GetuiPush;
import com.k9sv.util.StringUtil;

@SuppressWarnings("unchecked")
@Service("dogManager")
public class DogManager extends BaseManager implements IDogManager {

	Logger LOG = Logger.getLogger(DogManager.class);

	@Override
	public List<Dog> getDogs(int deleted, int start, int size) {
		try {
			return baseDao.queryStart("from Dog where deleted = ? order by created desc", new Object[] { deleted },
					start, size);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Dog> getCatDog(int catid, int start, int size) {
		try {
			return baseDao.queryStart("from Dog where category_id=? and deleted = 0 order by created desc",
					new Object[] { catid }, start, size);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Dog> getUserDogs(int uid, int pageIndex, int pageSize) {
		try {
			return query("from Dog where ownerId=? and deleted = 0 order by created desc", new Object[] { uid },
					pageIndex, pageSize);

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Dog> getUserDogs(int uid, int sex, int start, int size) {
		try {
			return baseDao.queryStart(
					"from Dog where deleted = 0 and ownerId=? and sex = ? and status = 0 order by created desc",
					new Object[] { uid, sex }, start, size);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<DogPhoto> getDogPhotos(int dogId, int start, int size) {
		try {
			return baseDao.queryStart("from DogPhoto where dogId=? and dog.deleted = 0  order by id desc",
					new Object[] { dogId }, start, size);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int getDogPhotoCount(int dogId) {
		try {
			return (Integer) baseDao.getCount("select count(id) from DogPhoto where dogId=? and dog.deleted = 0 ",
					new Object[] { dogId });
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public List<DogPhoto> getUserPhoto(int uid, int start, int size) {
		try {
			return baseDao.queryStart("from DogPhoto where uid=? and dog.deleted = 0 order by id desc",
					new Object[] { uid }, start, size);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int getUserPhotoCount(int uid) {
		try {
			return (Integer) baseDao.getCount("select count(id) from Photo where uid=? and dog.deleted = 0 ",
					new Object[] { uid });
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public void saveDogPhoto(DogPhoto photo) {
		Dog _dog = super.getByClassId(Dog.class, photo.getDogId());
		if (_dog == null) {
			return;
		}
		Date _now = new Date();
		_dog.setUpdated(_now);
		super.update(_dog);
		photo.setCreated(_now);
		super.save(photo);
	}

	//
	// @Override
	// public void saveDogVideo(DogVideo video) {
	// Dog _dog = super.getByClassId(Dog.class, video.getDogId());
	// if(_dog ==null){
	// return;
	// }
	// Date _now = new Date();
	// _dog.setUpdated(_now);
	// super.update(_dog);
	// video.setCreated(_now);
	// super.save(video);
	// }

	@Override
	public int getDogCount(int deleted) {
		try {
			Long l = (Long) baseDao.getCount("select count(id) from Dog where deleted=?", new Object[] { deleted });
			return l.intValue();
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public int getPetCount(Integer ownerId, Integer deleted) {
		try {
			Long l = (Long) baseDao.getCount("select count(id) from Dog where ownerId = ? and deleted = ?",
					new Object[] { ownerId, deleted });
			return l.intValue();
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public Dog getDog(int id) {
		Dog _dog = super.getByClassId(Dog.class, id);
		_dog.setViewed(_dog.getViewed() + 1);
		update(_dog);
		return _dog;
	}

	@Override
	public int saveDogComment(DogComment dogComment) {
		if (dogComment.getZan() == 1) {
			if (getDogZan(dogComment.getDogId(), dogComment.getUid()) == 1) {
				return 0;
			}
		}
		Dog _dog = super.getByClassId(Dog.class, dogComment.getDogId());
		if (dogComment.getZan() == 1) {
			_dog.setZan(_dog.getZan() + 1);
		} else {
			_dog.setCommentCount(_dog.getCommentCount() + 1);
		}
		update(_dog);
		dogComment.setCreated(new Date());
		save(dogComment);
		return dogComment.getId();
	}

	@Override
	public List<DogComment> getDogComments(int dogId, int zan, int start, int size) {
		return super.queryStart("from DogCommnet where dogId=? and zan=?", new Object[] { dogId, zan }, start, size);
	}

	@Override
	public int getDogCount(int dogId, int zan) {
		Long total = (Long) super.getCount("select count(id) from DogComment where dogId=? and zan = ?",
				new Object[] { dogId, zan });
		return total.intValue();
	}

	@Override
	public int getDogZan(int uid, int dogId) {
		Long total = (Long) super.getCount("select count(id) from DogComment where dogId=? and uid = ?",
				new Object[] { dogId, uid });
		return total.intValue();
	}

	@Override
	public int removeDogZan(int uid, int dogId) {
		List<DogComment> _list = queryStart("from DogComment where dogId=? and zan=1 and uid=?",
				new Object[] { dogId, uid }, 0, 1);
		if (_list != null && _list.size() == 1) {
			DogComment _dm = _list.get(0);
			Dog _dog = _dm.getDog();
			_dog.setZan(_dog.getZan() - 1);
			update(_dog);
			super.delete(_dm);
			return 1;
		}
		return 0;
	}

	@Override
	public int saveDogFavorite(int uid, int dogId) {
		Long l = (Long) super.getCount("select count(id) from DogFavorite where uid=? and dogId=?",
				new Object[] { uid, dogId });
		if (l == 0) {
			DogFavorite _df = new DogFavorite();
			_df.setUid(uid);
			_df.setDogId(dogId);
			_df.setCreated(new Date());
			super.save(_df);

			Dog _dog = super.getByClassId(Dog.class, dogId);
			_dog.setFavoriteCount(_dog.getFavoriteCount() + 1);
			update(_dog);
			return 1;
		}
		return 0;
	}

	@Override
	public List<Dog> getFavoriteDogs(int uid, int start, int size) {
		List<Dog> _list = super.queryStart("from Dog dog,DogFavorite df where where dog.id = df.dogId and df.uid=?",
				new Object[] { uid }, start, size);
		return _list;
	}

	@Override
	public int getFavoriteDogCount(int uid) {
		Long l = (Long) super.getCount("select count(id) from DogFavorite where uid=? ", new Object[] { uid });
		return l.intValue();
	}

	@Override
	public int deleteFavorited(int uid, int dogId) {
		List<DogFavorite> _list = super.queryStart("from DogFavorite where uid =? and dogId=?",
				new Object[] { uid, dogId }, 0, 1);
		if (_list != null && _list.size() == 1) {
			DogFavorite df = _list.get(0);
			super.delete(df);
			Dog _dog = super.getByClassId(Dog.class, dogId);
			_dog.setFavoriteCount(_dog.getFavoriteCount() - 1);
			update(_dog);
			return 1;
		}
		return 0;
	}

	@Override
	public int getDogFavorite(int uid, int dogId) {
		Long l = (Long) super.getCount("select count(id) from DogFavorite where uid=? and dogId=?",
				new Object[] { uid, dogId });
		return l.intValue();
	}

	@Override
	public List<Dog> getUserTradeDogs(int uid, int start, int size) {
		try {
			return baseDao.queryStart("from Dog where deleted = 0 and ownerId=? and status = 1 order by created desc",
					new Object[] { uid }, start, size);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Dog> getChildrenDogs(int dogId, int start, int size) {
		Dog _dog = super.getByClassId(Dog.class, dogId);
		if (_dog == null || "".equals(_dog.getBlood())) {
			return null;
		}
		if (_dog.getSex() == 1) {
			return queryStart("from Dog where fatherBlood = ?", new Object[] { _dog.getBlood() }, start, size);
		} else {
			return queryStart("from Dog where matherBlood = ?", new Object[] { _dog.getBlood() }, start, size);
		}
	}

	@Override
	public int getChildrenDogCount(int dogId) {
		Dog _dog = super.getByClassId(Dog.class, dogId);
		if (_dog == null || "".equals(_dog.getBlood())) {
			return 0;
		}
		Long l;
		if (_dog.getSex() == 1) {
			l = (Long) getCount("select count(id) from Dog where fatherBlood = ?", new Object[] { _dog.getBlood() });
		} else {
			l = (Long) getCount("select count(id) from Dog where matherBlood = ?", new Object[] { _dog.getBlood() });
		}

		return l.intValue();
	}

	@Override
	public List<Dog> getSameFatherDogs(int dogId, int start, int size) {
		Dog _dog = super.getByClassId(Dog.class, dogId);
		if (_dog == null || "".equals(_dog.getFatherBlood())) {
			return null;
		}
		return queryStart("from Dog where fatherBlood = ? and id <> " + dogId, new Object[] { _dog.getFatherBlood() },
				start, size);
	}

	@Override
	public int getSameFatherDogCount(int dogId) {
		Dog _dog = super.getByClassId(Dog.class, dogId);
		if (_dog == null || "".equals(_dog.getFatherBlood())) {
			return 0;
		}
		Long l = (Long) getCount("select count(id) from Dog where fatherBlood = ? and id <>" + dogId,
				new Object[] { _dog.getFatherBlood() });

		return l.intValue();
	}

	@Override
	public List<Dog> getSameMatherDogs(int dogId, int start, int size) {
		Dog _dog = super.getByClassId(Dog.class, dogId);
		if (_dog == null || "".equals(_dog.getMatherBlood())) {
			return null;
		}
		return queryStart("from Dog where matherBlood = ? and id <> " + dogId, new Object[] { _dog.getMatherBlood() },
				start, size);
	}

	@Override
	public int getSameMatherDogCount(int dogId) {
		Dog _dog = super.getByClassId(Dog.class, dogId);
		if (_dog == null || "".equals(_dog.getMatherBlood())) {
			return 0;
		}
		Long l = (Long) getCount("select count(id) from Dog where matherBlood = ? and id <> " + dogId,
				new Object[] { _dog.getMatherBlood() });

		return l.intValue();
	}

	@Override
	public List<Dog> getCommandDogs(int command, int start, int size) {
		try {
			return baseDao.queryStart("from Dog where deleted = 0 and command=? order by created desc",
					new Object[] { command }, start, size);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<DogPhoto> getDogPhotos(int start, int size) {
		try {
			return baseDao.queryStart("from DogPhoto order by id desc", null, start, size);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Integer delete(Integer id, Account _account) {
		Dog dog = super.getByClassId(Dog.class, id);
		if (dog != null && (_account.getId() == dog.getOwnerId())) {
			dog.setDeleted(1);
			update(dog);
			return 1;
		}
		return 0;
	}

	@Override
	public Integer getCount(String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount("select count(id) from Dog where deleted = 0 and name like ?",
					new Object[] { "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Dog where deleted = 0", null);
			return total.intValue();
		}
	}

	@Override
	public List<Dog> getDogs(String title, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query("from Dog where deleted = 0 and name like ? order by created desc",
					new Object[] { "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from Dog where deleted = 0 order by created desc", null, pageNum, numPerPage);
		}
	}

	@Override
	public Dog getdog(Integer did) {
		List<Dog> dogs = super.find("from Dog where id = ? order by created desc", new Object[] { did });
		Dog dog = null;
		if (dogs != null && dogs.size() > 0) {
			dog = dogs.get(0);
		}
		return dog;
	}

	@Override
	public void saveOrUpdate(Dog dog) {
		int id = dog.getId();
		if (id == 0) {
			this.save(dog);
		} else {
			this.update(dog);
		}
	}

	public void save(Dog dog) {
		dog.setViewed(0);
		dog.setZan(0);
		dog.setCommentCount(0);
		dog.setFavoriteCount(0);
		dog.setDeleted(0);
		dog.setCreated(new Date());
		dog.setHeight(0);
		dog.setWeight(0);
		super.save(dog);
		/*
		 * TransRecord record = new TransRecord(); record.setDid(dog.getId());
		 * record.setOriginalowner(dog.getOwnerId());
		 * record.setNowowner(dog.getOwnerId()); record.setTranstime(new
		 * Date()); super.save(record);
		 */
	}

	public void update(Dog dog) {
		Dog dog2 = super.getByClassId(Dog.class, dog.getId());
		if (dog.getOwnerId() != dog2.getOwnerId()) {
			TransRecord record = new TransRecord();
			record.setDogid(dog.getId());
			record.setFromUid(dog2.getOwnerId());
			record.setToUid(dog.getOwnerId());
			record.setTranstime(new Date());
			super.save(record);
		}
		super.update(dog);
	}

	@Override
	public Integer getCount(String title, Account account) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount(
					"select count(id) from Dog where deleted = 0 and ownerId = ? and name like ?",
					new Object[] { account.getId(), "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Dog where deleted = 0 and ownerId = ?",
					new Object[] { account.getId() });
			return total.intValue();
		}
	}

	@Override
	public List<Dog> getDogs(String title, Account account, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query("from Dog where deleted = 0  and ownerId = ? and name like ? order by id desc",
					new Object[] { account.getId(), "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from Dog where deleted = 0  and ownerId = ? order by id desc",
					new Object[] { account.getId() }, pageNum, numPerPage);
		}
	}

	@Override
	public Dog checkDogName(Account _account, String name, int petid) {
		List<Dog> _list = queryStart("from Dog where ownerId = ? and name=? and id != ? and deleted = 0",
				new Object[] { _account.getId(), name.trim(), petid }, 0, 1);
		if (_list != null && _list.size() > 0) {
			return _list.get(0);
		}
		return null;
	}

	@Override
	public Dog checkDog(Integer petid, Account _account, Integer touid) {
		Dog dog = super.getByClassId(Dog.class, petid);
		if (dog != null && (_account.getId() == dog.getOwnerId())) {
			dog.setOwnerId(touid);
			super.update(dog);
			TransRecord record = new TransRecord();
			record.setDogid(dog.getId());
			record.setFromUid(_account.getId());
			Profile original = super.getByClassId(Profile.class, _account.getId());
			record.setOld(original);
			record.setToUid(touid);
			Profile now = super.getByClassId(Profile.class, touid);
			record.setNow(now);
			record.setOld(original);
			record.setTranstime(new Date());
			super.save(record);
			this.updateRecord(petid, touid);
			this.pushTrans(record);
			return dog;
		}
		return null;
	}

	private void updateRecord(Integer petid, Integer touid) {
		super.executeSQL("update Record set uid = ? where dogid = ?", new Object[] { touid, petid });
	}

	private void pushTrans(final TransRecord record) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					PushTransDto transDto = new PushTransDto(record);
					PushDto pushDto = new PushDto();
					Profile fromProfile = record.getOld();
					pushDto.setData(transDto);
					if (record.getFromUid() != record.getToUid()) {// 转让自己不推
						String alter = fromProfile.getNickName() + "给你转让了宠物";
						pushDto.setType(Config.PushTrans);
						GetuiPush.push(alter, pushDto, record.getNow());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	@Override
	public Integer getCount(String title, Integer dogid) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount(
					"select count(id) from Dog where deleted = 0 and id != ? and name like ?",
					new Object[] { dogid, "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Dog where deleted = 0 and id != ?",
					new Object[] { dogid });
			return total.intValue();
		}
	}

	@Override
	public List<Dog> getDogs(String title, Integer dogid, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query("from Dog where deleted = 0 and id != ? and name like ? order by created desc",
					new Object[] { dogid, "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from Dog where deleted = 0 and id != ? order by created desc", new Object[] { dogid },
					pageNum, numPerPage);
		}
	}

	@Override
	public Integer getCount2(String title, Account account, int havablood) {
		if (account == null) {
			if (havablood == 0) {
				return this.getCount(title);
			}
			return this.getCount(havablood, title);
		}
		if (havablood == 0) {
			return this.getCount(title, account);
		}
		return this.getCount(havablood, title, account);
	}

	private Integer getCount(int havablood, String title, Account account) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount(
					"select count(id) from Dog where deleted = 0 and blood is not null and ownerId = ? and name like ?",
					new Object[] { account.getId(), "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount(
					"select count(id) from Dog where deleted = 0 and ownerId = ? and blood is not null",
					new Object[] { account.getId() });
			return total.intValue();
		}
	}

	private Integer getCount(int havablood, String title) {
		if (StringUtil.isNotEmpty(title)) {
			Long total = (Long) super.getCount(
					"select count(id) from Dog where deleted = 0 and blood is not null and name like ?",
					new Object[] { "%" + title + "%" });
			return total.intValue();
		} else {
			Long total = (Long) super.getCount("select count(id) from Dog where deleted = 0 and blood is not null",
					null);
			return total.intValue();
		}
	}

	@Override
	public List<Dog> getDogs2(String title, Account account, int havablood, Integer pageNum, Integer numPerPage) {
		if (account == null) {
			if (havablood == 0) {
				return this.getDogs(title, pageNum, numPerPage);
			}
			return this.getDogs(havablood, title, pageNum, numPerPage);
		}
		if (havablood == 0) {
			return this.getDogs(title, account, pageNum, numPerPage);
		}
		return this.getDogs(havablood, title, account, pageNum, numPerPage);
	}

	private List<Dog> getDogs(int havablood, String title, Account account, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query(
					"from Dog where deleted = 0 and blood is not null and ownerId = ? and name like ? order by id desc",
					new Object[] { account.getId(), "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from Dog where deleted = 0 and blood is not null and ownerId = ? order by id desc",
					new Object[] { account.getId() }, pageNum, numPerPage);
		}
	}

	private List<Dog> getDogs(int havablood, String title, Integer pageNum, Integer numPerPage) {
		if (StringUtil.isNotEmpty(title)) {
			return super.query("from Dog where deleted = 0 and blood is not null and name like ? order by created desc",
					new Object[] { "%" + title + "%" }, pageNum, numPerPage);
		} else {
			return super.query("from Dog where deleted = 0 and blood is not null order by created desc", null, pageNum,
					numPerPage);
		}
	}

	@Override
	public int getDogByBlood(String blood) {
		List<Dog> dogs = super.find("from Dog where blood = ?", new Object[] { blood });
		if (dogs == null) {
			return 0;
		}
		return dogs.size();
	}

	@Override
	public Dog getDog(String blood) {
		List<Dog> dogs = super.find("from Dog where blood = ?", new Object[] { blood });
		if (dogs != null && dogs.size() > 0) {
			return dogs.get(0);
		}
		return null;
	}

	@Override
	public Dog getDogBuyBlood(String blood) {
		try {
			List<Dog> dogs = query("from Dog where deleted = 0 and blood = ? and isok = 1 order by created desc",
					new Object[] { blood }, 1, 1);
			if (dogs != null && dogs.size() > 0) {
				return dogs.get(0);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}
}
