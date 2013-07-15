package org.springframework.social.google.api.mirror;

import java.util.Date;

/**
 *
 * Locations represent a place in the geographic world, and are used to communicate location data
 * about a given {@link TimelineItem timeline item}.
 * <PRE>{@code
 * { "kind": "mirror#location", "id": string, "timestamp": datetime, "latitude": double, "longitude": double, "accuracy": double, "displayName": string, "address": string }
 * }
 * </PRE>
 *
 *
 * @author Josh Long
 */
public class Location extends MirrorApiEntity {
	private static final String KIND = "mirror#location";
	private Date timestamp;
	private double latitude;
	private double longitude;
	private double accuracy;
	private String displayName;
	private String address;

	public Location(String id, Date timestamp, double latitude, double longitude, double accuracy, String displayName, String address) {

		this(id);
		this.timestamp = timestamp;
		this.accuracy = accuracy;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.displayName = displayName;
	}

	public Location(String id) {
		super(id, KIND);
	}

	protected Location() {
		super();
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getAddress() {
		return address;
	}


}
        