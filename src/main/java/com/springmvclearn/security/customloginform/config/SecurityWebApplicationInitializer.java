package com.springmvclearn.security.customloginform.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*
	- Spring security provides support for security initialisation
	- your security code is used to initialise the servlet container
	- special class to register the spring security filters ( AbstractSecurityWebApplicationInitializer )
	- If this class is not there then spring security won't be registered.
 */
public class SecurityWebApplicationInitializer 
						extends AbstractSecurityWebApplicationInitializer {

}
