package com.k9sv.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.k9sv.domain.pojo.FeedComment;

public class PhotoZanDto {

	private int type;

	private List<FeedPicDto> photos;

	private List<PushCommentDto> comments;

	public PhotoZanDto(int type, List<FeedPicDto> _feedPicList,
			List<FeedComment> comments) {
		this.setType(type);
		this.setPhotos(_feedPicList);
		this.setComments(this.getComments(comments));
	}

	private List<PushCommentDto> getComments(List<FeedComment> comments) {
		List<PushCommentDto> _comments = new ArrayList<PushCommentDto>();
		if (comments != null && comments.size() > 0) {
			for (FeedComment comment : comments) {
				_comments.add(new PushCommentDto(comment));
			}
		}
		return _comments;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<FeedPicDto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<FeedPicDto> photos) {
		this.photos = photos;
	}

	public List<PushCommentDto> getComments() {
		return comments;
	}

	public void setComments(List<PushCommentDto> comments) {
		this.comments = comments;
	}

}
