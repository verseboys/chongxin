package com.k9sv.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.k9sv.cache.UserCache;
import com.k9sv.domain.dto.FeedDto;
import com.k9sv.domain.dto.FeedPicDto;
import com.k9sv.domain.dto.UserDto;
import com.k9sv.domain.pojo.Feed;
import com.k9sv.domain.pojo.FeedComment;
import com.k9sv.service.IBaseManager;
import com.k9sv.service.IFeedManager;
import com.k9sv.service.IFriendManager;
import com.k9sv.service.ITouTiaoManager;
import com.k9sv.service.IUserManager;
import com.k9sv.util.DateUtil;

/**
 * 动态
 * 
 * @author Administrator
 * 
 */
@Controller("feedController1")
@Scope("prototype")
@RequestMapping("/feed")
public class FeedController {

	@Autowired
	IBaseManager baseManager;
	@Autowired
	ITouTiaoManager touTiaoManager;
	@Autowired
	IFeedManager feedManager;
	@Autowired
	IUserManager userManager;
	@Autowired
	IFriendManager friendManager;

	/**
	 * 分享动态
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/share")
	public String share(HttpServletRequest request, Model model, Integer uid,
			Integer feedid) {
		Feed _feed = feedManager.getByClassId(Feed.class, feedid);
		List<Feed> feeds = UserCache.UserZanFeeds.get(uid);
		int friendship = friendManager.getFriendShip(uid, _feed.getUid());// 好友关系
		int isZan = isZan(feeds, _feed);
		FeedDto _dto = new FeedDto(_feed, friendship, isZan);

		List<UserDto> zanUsers = this.getFeedZans(_feed.getId(), 1, 10);
		List<FeedPicDto> picDtos = _dto.getPhotos();
		FeedPicDto picDto = picDtos.get(0);
		String timeString = DateUtil.getSubTimeString(_feed.getCreated());
		model.addAttribute("zanUsers", zanUsers);
		model.addAttribute("picDtos", picDtos);
		model.addAttribute("picDto", picDto);
		model.addAttribute("feed", _dto);
		model.addAttribute("timeString", timeString);

		return "h5/feedshare";
	}

	private int isZan(List<Feed> feeds, Feed _feed) {
		int isZan = 0;
		if (feeds != null && feeds.contains(_feed)) {
			isZan = 1;// 已赞
		}
		return isZan;
	}

	private List<UserDto> getFeedZans(int fid, int page, int size) {
		List<UserDto> _temp = new ArrayList<UserDto>();
		try {
			List<FeedComment> _list = feedManager.getFeedZans(fid, page, size);
			for (FeedComment fc : _list) {
				_temp.add(new UserDto(fc.getProfile()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _temp;
	}
}
