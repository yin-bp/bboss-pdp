package org.frameworkset.spi.remote.dubbo;

public interface TestProviderInf {
	public String test(String hello);
	public String callHttp() throws Exception;
}
