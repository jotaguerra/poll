package com.webcocina.poll.domain;

import javax.persistence.*;

/**
 * Created by joaquinguerra on 01/03/2017.
 */
@Entity
@Table(name = "POLL", uniqueConstraints = {@UniqueConstraint(columnNames = {"site", "name"})})
public class PollImpl {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "ID", length = 10)
	private Integer id;

	@Lob
	@Column(name = "XMLDATA", columnDefinition="CLOB NOT NULL")
	private String xmldata;

	@Column(name = "SITE")
	private String site;

	@Column(name = "NAME")
	private String name;

	public PollImpl() {
	}

	public PollImpl(String xmldata, String site, String name) {
		this.xmldata = xmldata;
		this.site = site;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getXmldata() {
		return xmldata;
	}

	public void setXmldata(String xmldata) {
		this.xmldata = xmldata;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
