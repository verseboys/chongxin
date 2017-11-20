package com.k9sv.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.k9sv.domain.pojo.Message;
import com.k9sv.domain.pojo.Profile;
import com.k9sv.service.IMessageManager;
import com.k9sv.util.UploadUtil;

@Component
public class Kefu {
	
	@Autowired
	private IMessageManager messageManager;
	
	private static final Logger LOG = Logger.getLogger(Kefu.class);
	
	public static List<Message> Messages = new ArrayList<Message>();

	public static List<Profile> IMUsers = new ArrayList<Profile>();
	
	public static Map<Integer,List<Message>> IMMessages = new HashMap<Integer,List<Message>>();
	
	@Scheduled(fixedRate = 1000 * 60 * 60 * 1)
	public void scheduleRun() {
		List<Profile> _list =messageManager.query("select distinct(profile) from Message m where m.toUid=? order by m.id asc", new Object[]{1}, 0, 10000);
		for(Profile p : _list){
			LOG.info(p.getId());
			IMUsers.add(p);
			List<Message> _msgs = messageManager.getIM(1, p.getId(), 1, 1);
			if(_msgs != null && _msgs.size() > 0){
				Messages.add(_msgs.get(0));
			}
			IMMessages.put(p.getId(), _msgs);
		}
	}
	public static void addIMUsers(Profile profile){
		if(!IMUsers.contains(profile)){
			IMUsers.add(profile);
		}
	}
	
	//@Scheduled(fixedRate = 1000 * 60 * 60 * 1)
	public void sRun() {
		List<Profile> _list =messageManager.query("from Profile order by id desc", null, 0, 5000);
		for(Profile p : _list){
			if(p.getAvatar() != null && p.getAvatar().startsWith("avatar")){
				LOG.info(p.getId()+":"+p.getAvatar());
				//UploadUtil.uploadAvatar(p.getAvatar(), null);
				UploadUtil.deleted(p.getAvatar());
			}
		}
	}
}
