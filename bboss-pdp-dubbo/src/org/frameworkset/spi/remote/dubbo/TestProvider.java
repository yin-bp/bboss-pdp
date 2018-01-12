package org.frameworkset.spi.remote.dubbo;

public class TestProvider implements TestProviderInf{
  public String test(String hello){
	  return hello;
  }

@Override
public String callHttp() throws Exception {
	// TODO Auto-generated method stub
	return null;
}
}
