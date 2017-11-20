package com.k9sv.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.k9sv.Config2;
import com.k9sv.domain.pojo.Classify;
import com.k9sv.domain.pojo.Feed;
import com.k9sv.domain.pojo.Record;
import com.k9sv.domain.pojo.RecordPic;
import com.k9sv.util.DateUtil;

public class RecordDto {

	private Integer recordid;
	private Integer typeid;
	private String recordtype;
	private String recordtime;
	private Double weight;
	private Double height;
	private String remark;
	private FeedDto feed;
	private String created;
	private int isFeed;
	private String record_time;// 分享宠物详情时用到

	private List<RecordPicDto> photos;

	public RecordDto(Record record, int friendship, int iszan,
			List<FeedCommentDto> feedDtos, List<UserDto> userDtos) {
		Classify classify = record.getClassify();
		if (classify != null) {
			this.typeid = classify.getId();
			this.recordtype = classify.getName();
		}
		if (record.getRecordTime() != null) {
			this.recordtime = DateUtil
					.getFormatDateTime(record.getRecordTime());
			this.record_time = DateUtil.getFormatDateTime(
					record.getRecordTime(), "yyyy-MM-dd HH:mm");
		}
		this.recordid = record.getId();
		this.weight = record.getWeight();
		this.height = record.getHeight();
		this.remark = record.getRemark();
		Feed _feed = record.getFeed();
		if (_feed != null) {
			this.feed = new FeedDto(_feed, friendship, iszan);
			this.feed.setComments(feedDtos);
			this.feed.setZanusers(userDtos);
			this.isFeed = 1;
		}
		this.created = DateUtil.getFormatDateTime(record.getCreated());
		this.photos = this.getRecordPicDtos(Config2.ImgServer,
				record.getRecordPics());
	}

	public List<RecordPicDto> getRecordPicDtos(String server, Set<RecordPic> set) {
		List<RecordPicDto> _photos = new ArrayList<RecordPicDto>();
		if (set != null && set.size() > 0) {
			for (RecordPic pic : set) {
				_photos.add(new RecordPicDto(server, pic));
			}
		}
		return _photos;
	}

	public String getRecordType() {
		return recordtype;
	}

	public void setRecordType(String recordType) {
		this.recordtype = recordType;
	}

	public String getRecordTime() {
		return recordtime;
	}

	public void setRecordTime(String recordTime) {
		this.recordtime = recordTime;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public FeedDto getFeed() {
		return feed;
	}

	public void setFeed(FeedDto feed) {
		this.feed = feed;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public int getIsFeed() {
		return isFeed;
	}

	public void setIsFeed(int isFeed) {
		this.isFeed = isFeed;
	}

	public Integer getRecordid() {
		return recordid;
	}

	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getRecord_time() {
		return record_time;
	}

	public void setRecord_time(String record_time) {
		this.record_time = record_time;
	}

	public String getRecordtype() {
		return recordtype;
	}

	public void setRecordtype(String recordtype) {
		this.recordtype = recordtype;
	}

	public String getRecordtime() {
		return recordtime;
	}

	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}

	public List<RecordPicDto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<RecordPicDto> photos) {
		this.photos = photos;
	}

}
