package com.k9sv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.k9sv.domain.pojo.Profile;
import com.k9sv.domain.pojo.Visitor;
import com.k9sv.service.IVisitorManager;

@Service("visitorManager")
public class VisitorManager extends BaseManager implements IVisitorManager {

	@Override
	public int saveVisitor(int type, int vid, int uid) {
		List<Visitor> _list = queryStart("from Visitor where type=? and vid=? and uid=? order by created desc",new Object[]{type,vid,uid},0,1);
		if(_list != null  && _list.size()==1){
			Visitor _visitor = _list.get(0);
			_visitor.setCreated(new Date());
			update(_visitor);
		}else{
			Visitor _visitor = new Visitor();
			_visitor.setCreated(new Date());
			_visitor.setVid(vid);
			_visitor.setType(type);
			_visitor.setUid(uid);
			save(_visitor);
		}
		return 1;
	}

	@Override
	public List<Profile> getVisitors(int type, int vid,int start,int size) {
		return super.queryStart("select profile from Visitor where vid=? and type=?", new Object[]{vid,type}, start, size);
	}

}
