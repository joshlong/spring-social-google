package org.springframework.social.google.api.mirror.impl;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import java.util.*;
import java.util.regex.Pattern;

interface Property {

	/** can return null if the runtime type doesn't exist. */
	Class<?> getRuntimeClassOfProperty();

	String getPrivacy();

	String getDataType();

	String getVariableName();

	String getPropertyName();
}

/**
 * Auto generate the API client based on JSON schema structures.
 * <p/>
 * <OL> <LI> Simple API properties are mapped as expected using standard JavaBean properties </LI> <LI> Calls to
 * properties of type `Resource` are handle as complex object properties which are replaced by runtime proxies like
 * `Contact`, `Subscription`, etc. </LI> </OL>
 *
 * @author Josh Long
 */
public class MirrorApiGenerator {
	private final String resourceRefRegularExpression = ":\\s+(.*?),";
	private final String[] literals = "datetime,etag,integer,boolean,string".split(",");

	public void parse(Map<String, String> mapOfTypeNamesToResourceDescription) throws Throwable {
		for (String type : mapOfTypeNamesToResourceDescription.keySet()) {
			String resourceDescription =
					  mapOfTypeNamesToResourceDescription.get(type);
			String classForType =
					  handleRepresentationOfResource(type, resourceDescription);
			System.out.println(classForType);

		}
	}

	String handleRepresentationOfResource(String type, String jsonResourceDescription) throws Exception {
		jsonResourceDescription = fixInvalidJson(jsonResourceDescription);
		ObjectMapper mapper = new ObjectMapper();
		JsonFactory factory = mapper.getFactory(); // since 2.1 use mapper.getFactory() instead
		JsonParser jp = factory.createParser(jsonResourceDescription);
		JsonNode actualObj = mapper.readTree(jp);
		Set<Property> javaBeanProperties = new HashSet<Property>();

		if (actualObj != null){
			String variableName;
			Iterator<String> fieldNames = actualObj.fieldNames();
			while (fieldNames.hasNext() && (variableName = fieldNames.next()) != null) {
				JsonNode valueNode = actualObj.get(variableName);
				SimpleProperty simpleProperty = new SimpleProperty(variableName, valueNode.asText());
				javaBeanProperties.add(simpleProperty);
			}
		}


		String classTemplate = "public class " + type + "{ \n" +
		                       " /* fields */\n" +
		                       "%s" +
		                       "\n " +
		                       "/* properties */\n" +
		                       "%s " +
		                       "\n }";


		// properties
		String properties = "";
		String fields = "";
		for (Property property : javaBeanProperties) {
		//	properties += buildPropertyFor(property);
			fields += buildFieldFor(property);
		}

		return String.format(classTemplate, properties, fields);
	}

	String buildFieldFor(Property property) {
		String fieldTemplate = "\tprivate %s %s ; \n";
		String runtimeClassOfProperty =
				  property.getRuntimeClassOfProperty() == null
							 ? property.getDataType() : property.getRuntimeClassOfProperty().getSimpleName();

		return String.format(fieldTemplate, runtimeClassOfProperty, property.getVariableName());
	}

	String buildPropertyFor(Property property) {
		String propertyTemplate = "\tpublic %s get%s(){ return this.%s ;} \n";
		String runtimeClassOfProperty =
				  property.getRuntimeClassOfProperty() == null
							 ? property.getDataType() : property.getRuntimeClassOfProperty().getSimpleName();

		return String.format(propertyTemplate, runtimeClassOfProperty, property.getPropertyName(), property.getVariableName());
	}

	String quoted(String literal) {
		return "\"" + literal + "\"";
	}

	String replaceAll(String input, String token) {
		return input.replaceAll(Pattern.quote(token), (quoted(token)));
	}

	String fixInvalidJson(String input) {

		for (String l : literals) {
			input = replaceAll(input, l);
		}
		input = input.replaceAll(Pattern.quote("\"\""), "\"");
		return input;
	}


}

class SimpleProperty implements Property {
	private String privacy = "private", variableName, propertyName, dataType;
	private Class<?> classOfProperty;
	private Map<String, Class<?>> propertyMap = new HashMap<String, Class<?>>();

	public SimpleProperty(String variableName, String dataType) {
		initPropertyMap();

		this.variableName = variableName;
		this.dataType = dataType;

		// special case for booleans
		if (this.variableName.startsWith("is")){
			this.variableName = this.variableName.substring(2);

			if (Character.isUpperCase(this.variableName.charAt(0))){
				this.variableName = (this.variableName.charAt(0) + "").toLowerCase() +
				                    this.variableName.substring(1);
			}
		}

		this.propertyName = propertyNameForVariableName(this.variableName);

		this.classOfProperty = this.propertyMap.containsKey(this.dataType) ? this.propertyMap.get(this.dataType) : null;

	}

	private void initPropertyMap() {
		propertyMap.put("datetime", Date.class);
		propertyMap.put("etag", String.class);
		propertyMap.put("integer", int.class);
		propertyMap.put("boolean", boolean.class);
		propertyMap.put("string", String.class);
	}

	public Class<?> getRuntimeClassOfProperty() {
		return this.classOfProperty;
	}

	@Override
	public String toString() {
		return "{ variableName:" + this.variableName +
		       ", dataType:" + this.dataType +
		       ", propertyName:" + this.propertyName +
		       ",classOfProperty: " + this.classOfProperty + "}";
	}

	public String getPrivacy() {
		return privacy;
	}

	public String getVariableName() {
		return variableName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String getDataType() {
		return dataType;
	}

	private String propertyNameForVariableName(String variableName) {
		return (variableName.charAt(0) + "").toUpperCase() + variableName.substring(1);
	}
}

class Main {


	public static void main(String args[]) throws Throwable {
		String timelineResourceDescription = "" +
		                                     "{\n" +
		                                     "  \"kind\": \"mirror#timelineItem\",\n" +
		                                     "  \"id\": string,\n" +
		                                     "  \"sourceItemId\": string,\n" +
		                                     "  \"canonicalUrl\": string,\n" +
		                                     "  \"bundleId\": string,\n" +
		                                     "  \"isBundleCover\": boolean,\n" +
		                                     "  \"selfLink\": string,\n" +
		                                     "  \"created\": datetime,\n" +
		                                     "  \"updated\": datetime,\n" +
		                                     "  \"displayTime\": datetime,\n" +
		                                     "  \"isPinned\": boolean,\n" +
		                                     "  \"pinScore\": integer,\n" +
		                                     "  \"isDeleted\": boolean,\n" +
		                                     "  \"etag\": etag,\n" +
		                                     "  \"creator\": \"contacts Resource\",\n" +
		                                     "  \"recipients\": [\n" +
		                                     "    \"contacts Resource\"\n" +
		                                     "  ],\n" +
		                                     "  \"inReplyTo\": string,\n" +
		                                     "  \"title\": string,\n" +
		                                     "  \"text\": string,\n" +
		                                     "  \"html\": string,\n" +
		                                     "  \"htmlPages\": [\n" +
		                                     "    string\n" +
		                                     "  ],\n" +
		                                     "  \"speakableText\": string,\n" +
		                                     "  \"attachments\": [\n" +
		                                     "    \"timeline.attachments Resource\"\n" +
		                                     "  ],\n" +
		                                     "  \"location\": \"locations Resource\",\n" +
		                                     "  \"menuItems\": [\n" +
		                                     "    {\n" +
		                                     "      \"id\": string,\n" +
		                                     "      \"action\": string,\n" +
		                                     "      \"values\": [\n" +
		                                     "        {\n" +
		                                     "          \"state\": string,\n" +
		                                     "          \"displayName\": string,\n" +
		                                     "          \"iconUrl\": string\n" +
		                                     "        }\n" +
		                                     "      ],\n" +
		                                     "      \"removeWhenSelected\": boolean\n" +
		                                     "    }\n" +
		                                     "  ],\n" +
		                                     "  \"notification\": {\n" +
		                                     "    \"level\": string,\n" +
		                                     "    \"deliveryTime\": datetime\n" +
		                                     "  }\n" +
		                                     "}";

		Map<String, String> mapOfTypesToTheirResourceDescription = new HashMap<String, String>();
		mapOfTypesToTheirResourceDescription.put("TimelineItem", timelineResourceDescription);

		MirrorApiGenerator mirrorApiGenerator = new MirrorApiGenerator();
		mirrorApiGenerator.parse(mapOfTypesToTheirResourceDescription);


	}


}
