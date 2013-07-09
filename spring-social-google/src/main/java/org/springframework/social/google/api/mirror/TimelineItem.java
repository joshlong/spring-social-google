package org.springframework.social.google.api.mirror;

import org.springframework.social.google.api.ApiEntity;

/**
 * Parent type for things which may be managed by a {@link Timeline timeline}, including
 * <em>cards</em> and <EM>bundles</EM> (which are aggregates of cards).
 *
 * @author Josh Long
 */
public  class TimelineItem extends ApiEntity {

 	private String oauthToken;
	private String alt;
	private String fields;
	private String key;
	private Boolean prettyPrint;
	private String quotaUser;
	private String userIp;

	public TimelineItem(String id ){
		super(id);
	}

}
