package com.kk.team.category.model;

import java.io.Serializable;

import lombok.Value;

import org.springframework.data.annotation.Id;

@Value
public class Category implements Serializable {

	private static final long serialVersionUID = -1916924059221121440L;

	@Id
	private String id;
	private String name;
	private String lang;
	private String path;
}
