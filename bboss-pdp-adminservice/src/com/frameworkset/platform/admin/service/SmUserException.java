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

package com.frameworkset.platform.admin.service;

/**
 * <p>Title: SmUserException</p> <p>Description: 用户管理管理异常处理类 </p> <p>bboss</p>
 * <p>Copyright (c) 2007</p> @Date 2016-11-29 21:14:30 @author yinbp @version
 * v1.0
 */
public class SmUserException extends RuntimeException {

	public SmUserException() {
		super();
	}
	public SmUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public SmUserException(String message) {
		super(message);
	}

	public SmUserException(Throwable cause) {
		super(cause);
	}

}