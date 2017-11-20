package com.k9sv.schedule;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ForumSchedule {

	protected transient final Logger log = Logger.getLogger(ForumSchedule.class);

	private void cleanForumToday() {
		/*
		 * List<Forum> _list = forumManager.getForums(); for(Forum f : _list){
		 * f.setTodayTopics(0); forumManager.update(f); }
		 */
	}

	// CRON表达式 含义
	// "0 0 12 * * ?" 每天中午十二点触发
	// "0 15 10 ? * *" 每天早上10：15触发
	// "0 15 10 * * ?" 每天早上10：15触发
	// "0 15 10 * * ? *" 每天早上10：15触发
	// "0 15 10 * * ? 2005" 2005年的每天早上10：15触发
	// "0 * 14 * * ?" 每天从下午2点开始到2点59分每分钟一次触发
	// "0 0/5 14 * * ?" 每天从下午2点开始到2：55分结束每5分钟一次触发
	// "0 0/5 14,18 * * ?" 每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
	// "0 0-5 14 * * ?" 每天14:00至14:05每分钟一次触发
	// "0 10,44 14 ? 3 WED" 三月的每周三的14：10和14：44触发
	// "0 15 10 ? * MON-FRI" 每个周一、周二、周三、周四、周五的10：15触发
	// @Scheduled(cron="0 14 14 * * ?"
	@Scheduled(cron = "0 01 0 * * ?")
	public void scheduleRun() {

		cleanForumToday();
		log.info("更新论坛信息完成.........");
	}
	/**
	 * @Scheduled(fixedRate = 1000) public void scheduleMethod() { if (runTag) {
	 *                      log.info("初始化城市,分类,资源信息........."); setCity();
	 *                      setcategory(); setSecurity();
	 *                      log.info("初始化城市,分类,资源信息完成........."); } runTag =
	 *                      false; }
	 * @Scheduled(fixedRate =1000*60*5) public void securityScheduleRun(){
	 *                      log.info("加载权限信息........."); setSecurity();
	 *                      log.info("加载权限信息完成........."); }
	 * 
	 */

}
