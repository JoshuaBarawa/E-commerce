package com.ecomm.app.webconfig;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PaypalConfig {
	
	private String clientId = "AfaiSTWNZ25Pz_0krng2OHRu79ZHuXQqRCjvDMvvrKGRs4M8HiXtCdurbTVVt0MIMQy4wmSzsPRxwHug";
	private String clientSecret = "EPmOuFObk6EC5jiN86osgVtPwlv06qQmOOa2YikLUQ0T1_0QqISYgWjV9ZWG4zCX1Dt28TuyRSSfIEBN";
	private String mode = "sandbox";

	@Bean
	public Map paypalSdkConfig() {
		Map configMap = new HashMap<>();
		configMap.put("mode", mode);
		
		return configMap;
	}

	@Bean
	public OAuthTokenCredential oAuthTokenCredential() {
		return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
	}

	@Bean
	public APIContext apiContext() throws PayPalRESTException {
		APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
		context.setConfigurationMap(paypalSdkConfig());
		return context;
	}

}
	