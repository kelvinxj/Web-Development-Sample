package com.ibm.mdm.testtool;

public class KafkaProcessorArgs {
	private String kafkaIP = "";
	private String requestTopicName = "";
	private String responseTopicName = "";
	private String mdmServerUrl = "";
	public String getKafkaIP() {
		return kafkaIP;
	}
	public void setKafkaIP(String kafkaIP) {
		this.kafkaIP = kafkaIP;
	}
	public String getRequestTopicName() {
		return requestTopicName;
	}
	public void setRequestTopicName(String requestTopicName) {
		this.requestTopicName = requestTopicName;
	}
	public String getResponseTopicName() {
		return responseTopicName;
	}
	public void setResponseTopicName(String responseTopicName) {
		this.responseTopicName = responseTopicName;
	}
	public String getMdmServerUrl() {
		return mdmServerUrl;
	}
	public void setMdmServerUrl(String mdmServerUrl) {
		this.mdmServerUrl = mdmServerUrl;
	}
	public KafkaProcessorArgs(String kafkaIP, String requestTopicName, String responseTopicName, String mdmServerUrl) {
		this.kafkaIP = kafkaIP;
		this.requestTopicName = requestTopicName;
		this.responseTopicName = responseTopicName;
		this.mdmServerUrl = mdmServerUrl;
	}
	
	
}
