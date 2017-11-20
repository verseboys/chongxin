package com.k9sv.domain.pojo;
// Generated 2013-3-5 1:50:15 by Hibernate Tools 3.6.0



/**
 * Category generated by hbm2java
 */
public class Category  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 4055133619362398149L;
	private int id;
     private String name;
     private int pid;
     private String content;
     private int forumId;

    public Category() {
    }

	
    public Category(String name, int pid) {
        this.name = name;
        this.pid = pid;
    }
    public Category(String name, int pid, String content) {
       this.name = name;
       this.pid = pid;
       this.content = content;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public int getPid() {
        return this.pid;
    }
    
    public void setPid(int pid) {
        this.pid = pid;
    }
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }


	public int getForumId() {
		return forumId;
	}


	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
    




}

