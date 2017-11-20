package com.k9sv.domain.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.k9sv.Config;
import com.k9sv.Config2;
import com.k9sv.domain.pojo.Feed;
import com.k9sv.domain.pojo.FeedPic;
import com.k9sv.util.DateUtil;
import com.k9sv.util.JsonUtil;

public class FeedDto {

	private Integer fid;

	private Integer type;

	private CardDto card;

	private String content;

	private String shareurl;

	private String address;

	private String created;

	private Integer zancount;

	private Integer commentcount;

	private Integer iszan;

	private LocationDto location;

	private List<FeedPicDto> photos;

	private String photo;

	private List<FeedCommentDto> comments;

	private List<UserDto> zanusers;

	private UserDto user;

	public FeedDto(Feed feed, int friendship, int iszan) {
		this.fid = feed.getId();
		this.type = feed.getType();
		this.content = feed.getContent();
		this.address = feed.getAddress();
		this.created = DateUtil.getFormatDateTime(feed.getCreated());
		this.zancount = feed.getGoodCount();
		this.commentcount = feed.getReplyCount();
		this.iszan = iszan;
		if (feed.getLatitude() != 0 && feed.getLongtitude() != 0
				&& feed.getLatitude() > 0) {
			this.location = new LocationDto(feed.getLatitude(),
					feed.getLongtitude());
		}
		// this.shareurl = Config.FeedShareUrl + feed.getId();
		this.shareurl = Config.ShareUrl;
		this.user = new UserDto(feed.getProfile(), friendship);
		this.photos = this
				.getFeedPicDtos(Config2.ImgServer, feed.getFeedPics());
		this.card = this.getFeedCardDto(feed);
	}

	// 保存动态时使用，其他勿调用
	public FeedDto(Feed feed) {
		this.fid = feed.getId();
		this.type = feed.getType();
		this.content = feed.getContent();
		this.address = feed.getAddress();
		this.created = DateUtil.getFormatDateTime(feed.getCreated());
		this.zancount = feed.getGoodCount();
		this.commentcount = feed.getReplyCount();
		this.iszan = 0;
		if (feed.getLatitude() != 0 && feed.getLongtitude() != 0
				&& feed.getLatitude() > 0) {
			this.location = new LocationDto(feed.getLatitude(),
					feed.getLongtitude());
		}
		this.user = new UserDto(feed.getProfile(), 0);
		this.photos = this.getFeedPicDtos(Config2.LocalImgServer,
				feed.getFeedPics());
		this.card = this.getFeedCardDto(feed);
		// this.shareurl = Config.FeedShareUrl + feed.getId();
		this.shareurl = Config.ShareUrl;
	}

	public FeedDto(Feed feed, Integer isTui) {
		this.fid = feed.getId();
		this.content = feed.getContent();
		if (feed.getType() == 1 && feed.getCard() != null) {
			String card = feed.getCard();
			JSONObject myJsonObject = new JSONObject(card);
			this.photo = JsonUtil.getString(myJsonObject, "photo");
		} else {
			this.photo = this.getFeedPic(Config2.ImgServer, feed.getFeedPics());
		}
	}

	public List<FeedPicDto> getFeedPicDtos(String server, Set<FeedPic> set) {
		List<FeedPicDto> _photos = new ArrayList<FeedPicDto>();
		if (set != null && set.size() > 0) {
			for (FeedPic pic : set) {
				_photos.add(new FeedPicDto(server, pic));
			}
		}
		return _photos;
	}

	public String getFeedPic(String server, Set<FeedPic> set) {
		String photo = null;
		if (set != null && set.size() > 0) {
			Iterator<FeedPic> it = set.iterator();
			FeedPic pic = it.next();
			photo = server + pic.getUrl();
		}
		return photo;
	}

	public CardDto getFeedCardDto(Feed feed) {
		if (feed.getType() == 1 && feed.getCard() != null) {
			Gson gson = new Gson();
			CardDto card = gson.fromJson(feed.getCard(), CardDto.class);
			return card;
		}
		return null;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public CardDto getCard() {
		return card;
	}

	public void setCard(CardDto card) {
		this.card = card;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Integer getZancount() {
		return zancount;
	}

	public void setZancount(Integer zancount) {
		this.zancount = zancount;
	}

	public Integer getCommentcount() {
		return commentcount;
	}

	public void setCommentcount(Integer commentcount) {
		this.commentcount = commentcount;
	}

	public Integer getIszan() {
		return iszan;
	}

	public void setIszan(Integer iszan) {
		this.iszan = iszan;
	}

	public LocationDto getLocation() {
		return location;
	}

	public void setLocation(LocationDto location) {
		this.location = location;
	}

	public List<FeedPicDto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<FeedPicDto> photos) {
		this.photos = photos;
	}

	public List<FeedCommentDto> getComments() {
		return comments;
	}

	public void setComments(List<FeedCommentDto> comments) {
		this.comments = comments;
	}

	public List<UserDto> getZanusers() {
		return zanusers;
	}

	public void setZanusers(List<UserDto> zanusers) {
		this.zanusers = zanusers;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getShareurl() {
		return shareurl;
	}

	public void setShareurl(String shareurl) {
		this.shareurl = shareurl;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
