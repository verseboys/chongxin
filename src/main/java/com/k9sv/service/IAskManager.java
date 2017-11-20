package com.k9sv.service;

import java.util.List;

import com.k9sv.domain.pojo.Account;
import com.k9sv.domain.pojo.Answer;
import com.k9sv.domain.pojo.Ask;

public interface IAskManager extends IBaseManager {
	
	Ask getAsk(int id);

	List<Ask> loadAsk(int deleted,int page,int size);
	
	List<Ask> loadAsk(int uid,int deleted,int page,int size);
	
	List<Answer> loadAnswer(int askId,int page,int size);
	
	void ask(Ask ask);
	
	void answer(Answer answer);
	
	void deleteAnswer(int answerId);

	int deleteAnswer(int aid, Account _account);

	int deleteAsk(int aid, Account _account);

	void saveOrUpdateAsk(Ask _ask);

	void saveOrUpdateAnswer(Answer _answer);
	/**
	 * 问题条数
	 * @param title
	 * @return
	 */
	Integer getCount(String title);
	/**
	 * 问题列表
	 * @param title
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<Ask> getAsks(String title, Integer pageNum, Integer numPerPage);
	/**
	 * 回答条数
	 * @param title
	 * @param id
	 * @return
	 */
	Integer getCount(String title, Integer id);
	/**
	 * 回答列表
	 * @param title
	 * @param id
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	List<Answer> getAnswers(String title, Integer id, Integer pageNum,
			Integer numPerPage);
	
}
