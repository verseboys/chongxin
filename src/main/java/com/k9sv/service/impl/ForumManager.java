package com.k9sv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Forum;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.domain.pojo.Topic;
import com.k9sv.service.IForumManager;

@Service("forumManager")
public class ForumManager extends BaseManager implements IForumManager {

	public void saveOrUpdateTopic(Topic topic){
		Date _now = new Date();
		if(topic.getId() ==0){

			Forum _f = super.getByClassId(Forum.class, topic.getFid());
			_f.setTopics(_f.getTopics()+1);
			_f.setTodayTopics(_f.getTodayTopics()+1);
			update(_f);
			topic.setCreated(_now);
			topic.setDeleted(0);
			topic.setPid(0);
			topic.setLastReplied(_now);
			super.save(topic);
			
			
			Profile _profile = super.getByClassId(Profile.class,topic.getUid());
			_profile.setTopicsCount(_profile.getTopicsCount()+1);
			_profile.setScore(_profile.getScore()+1);
			_profile.setPrestige(_profile.getPrestige()+1);
			_profile.setFeedsCount(_profile.getFeedsCount()+1);
			update(_profile);
		}else{
			Topic _topic = super.getByClassId(Topic.class, topic.getId());
			_topic.setContent(topic.getContent());
			_topic.setTitle(topic.getTitle());
			_topic.setUpdated(_now);
			_topic.setFid(topic.getFid());
			_topic.setType(topic.getType());
			update(_topic);
		}
		
	}
	public void saveOrUpdateReply(Topic topic){
		Date _now = new Date();
		if(topic.getId()==0){
			Topic _t = super.getByClassId(Topic.class, topic.getPid());
			_t.setReplied(_t.getReplied()+1);
			_t.setLastReplied(_now);
			_t.setLastUid(topic.getUid());
			update(_t);
			topic.setCreated(_now);
			save(topic);
		}else{
			Topic _topic = super.getByClassId(Topic.class, topic.getId());
			_topic.setContent(topic.getContent());
			_topic.setUpdated(_now);
			update(_topic);
		}
	}
	public int deleteTopic(int id){
		Topic _topic = super.getByClassId(Topic.class, id);
		if(_topic != null){
			if(_topic.getPid()==0){
				Forum f = getByClassId(Forum.class,_topic.getFid());
				f.setTopics(f.getTopics()-1);
				update(f);
				_topic.setDeleted(1);
				update(_topic);
				
				Profile p = _topic.getProfile();
				p.setTopicsCount(p.getTopicsCount()-1);
				update(p);
			}else{
				Topic _pTopic = getByClassId(Topic.class, _topic.getPid());
				_pTopic.setReplied(_pTopic.getReplied()-1);
				update(_pTopic);
				_topic.setDeleted(1);
				update(_topic);
			}
			return 1;
		}
		return 0;
	}
	public Topic getTopic(int id){
		Topic _topic = super.getByClassId(Topic.class, id);
		if(_topic != null){
			_topic.setViewed(_topic.getViewed()+1);
			update(_topic);
			return _topic;
		}
		return null;
	}
	@Override
	public List<Forum> getForums(int pid) {
		List<Forum> _list = query("from Forum where pid=? and enable=true order by orders desc", new Object[]{pid} , 1, 100);
		for(Forum f : _list){
			List<Profile> _profiles = query("select p from Profile p, ForumAdmin fa where fa.uid =p.id and fa.fid = ?",new Object[]{f.getId()},1,10);
			f.setProfiles(_profiles);
		}
		return _list;
	}
	public List<Forum> getForums() {
		List<Forum> _list = query("from Forum where enable=true order by orders desc", null , 0, 100);
		for(Forum f : _list){
			List<Profile> _profiles = query("select p from Profile p, ForumAdmin fa where fa.uid =p.id and fa.fid = ?",new Object[]{f.getId()},1,10);
			f.setProfiles(_profiles);
		}
		return _list;
	}
	@Override
	public List<Topic> getTopics(int fid, int deleted,int pageIndex, int pageSie) {
		return query("from Topic where fid=? and deleted=? and pid=0 order by topest desc,lastReplied desc", new Object[]{fid,deleted} , pageIndex, pageSie);
	}

	@Override
	public List<Topic> getReplies(int pid, int deleted,int pageIndex, int pageSie) {

		return query("from Topic where pid=? and deleted=? order by id asc", new Object[]{pid,deleted} , pageIndex, pageSie);
	}
	@Override
	public List<Topic> getMyTopics(int uid, int deleted, int pageIndex, int pageSie) {
		return query("from Topic where uid=? and deleted=? and pid=0 order by id desc", new Object[]{uid,deleted} , pageIndex, pageSie);
	}
	@Override
	public Forum getForum(int id) {
		Forum forum = super.getByClassId(Forum.class, id);
		List<Profile> _profiles = query("select p from Profile p, ForumAdmin fa where fa.uid =p.id and fa.fid = ?",new Object[]{id},1,10);
		forum.setProfiles(_profiles);
		return forum;
	}
	@Override
	public boolean isManager(int fid, int uid) {
		if(uid ==1){
			return true;
		}
		Long i = (Long)super.getCount("select count(id) from ForumAdmin where fid=? and uid=?", new Object[]{fid,uid});
		if(i > 0){
			return true;
		}else{
			return false;
		}
		
	}
	@Override
	public int topestTopic(int id,int value) {
		Topic _topic = super.getByClassId(Topic.class, id);
		if(_topic.getPid() > 0){
			return 0;
		}
		_topic.setTopest(value);
		update(_topic);
		return 1;
	}
	@Override
	public int bestTopic(int id,int value) {
		Topic _topic = super.getByClassId(Topic.class, id);
		if(_topic.getPid() > 0){
			return 0;
		}
		_topic.setBest(value);
		update(_topic);
		return 1;
	}
	@Override
	public List<Topic> getNewTopics(int fid, int pageIndex, int pageSie) {
		StringBuffer _hql = new StringBuffer("from Topic where pid = 0 ");
		if(fid !=0){
			_hql.append("and fid = ");
			_hql.append(fid);
		}
		_hql.append(" order by id desc");
		return query(_hql.toString(), null , pageIndex, pageSie);
	}
	@Override
	public List<Topic> getHotTopics(int fid, int pageIndex, int pageSie) {
		StringBuffer _hql = new StringBuffer("from Topic where pid = 0 ");
		if(fid !=0){
			_hql.append("and fid = ");
			_hql.append(fid);
		}
		_hql.append(" order by replied desc");
		return query(_hql.toString(), null , pageIndex, pageSie);
	}

}
