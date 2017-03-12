/**
 *  Copyright 2008-2010 biaoping.yin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.frameworkset.platform.application.entity;

/**
 * <p>Title: Application</p> <p>Description: 应用管理管理服务实体类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-11-24 15:35:15 @author yinbp @version
 * v1.0
 */
public class Application implements java.io.Serializable {
	/**
	 * 流水号
	 */
	private String appId;
	/**
	 * 应用编码
	 */
	private String appCode;
	/**
	 * 应用名称
	 */
	private String appName;
	/**
	 * 应用口令
	 */
	private String appSecret;
	/**
	 * 应用口令明文
	 */
	private String appSecretText;
	private String certAlgorithm = "RSA";
	public String getCertAlgorithm() {
		return certAlgorithm;
	}
	public void setCertAlgorithm(String certAlgorithm) {
		this.certAlgorithm = certAlgorithm;
	}
	/**
	 * 凭证有效期
	 */
	private long ticketlivetimes;
	private String publicKey;
	private String privateKey;
	public Application() {
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecretText(String appSecretText) {
		this.appSecretText = appSecretText;
	}

	public String getAppSecretText() {
		return appSecretText;
	}

	public void setTicketlivetimes(long ticketlivetimes) {
		this.ticketlivetimes = ticketlivetimes;
	}

	public long getTicketlivetimes() {
		return ticketlivetimes;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

}