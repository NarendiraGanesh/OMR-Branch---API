package com.omrbranch.day2.pojo;

public class UserRole {

	private int id;
    private String role;
    private String name;
    private String status;
    private String created_at;
    private String updated_at;
    private Pivot pivot;
    
    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public Pivot getPivot() {
		return pivot;
	}

	public void setPivot(Pivot pivot) {
		this.pivot = pivot;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}
	
	
}
