
// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'app.admin.security.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'app.admin.security.UserRole'
grails.plugin.springsecurity.authority.className = 'app.admin.security.Role'

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],

	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],

	[pattern: '/index',          access: ['ROLE_ADMIN', 'ROLE_OPERATOR']],
	[pattern: '/index.gsp',      access: ['ROLE_ADMIN', 'ROLE_OPERATOR']],
	[pattern: '/securityInfo/*', access: ['ROLE_ADMIN']],

	[pattern: '/quartz/**', 	 access: ['ROLE_ADMIN']],
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

//http://grails-plugins.github.io/grails-spring-security-core/v3/index.html#channelSecurity
//grails.plugin.springsecurity.secureChannel.definition = [
//		[pattern: '/assets/**',       access: 'ANY_CHANNEL'],
//		[pattern: '/**/js/**',       access: 'ANY_CHANNEL'],
//		[pattern: '/**/css/**',       access: 'ANY_CHANNEL'],
//		[pattern: '/**/images/**',       access: 'ANY_CHANNEL'],
//		[pattern: '/**/favicon.ico',       access: 'ANY_CHANNEL'],
//		[pattern: '/**', access: 'REQUIRES_SECURE_CHANNEL']
//]

//grails.plugin.springsecurity.secureChannel.useHeaderCheckChannelSecurity = true

// https://grails-fields-plugin.github.io/grails-fields/guide/performance.html
environments {
	development {
		grails.plugin.fields.disableLookupCache = true
	}
}

// http://grails-plugins.github.io/grails-quartz/guide/configuration.html
quartz {
	autoStartup = true
	jdbcStore = false
}
environments {
	test {
		quartz {
			autoStartup = false
		}
	}
}

// microservice config

grails.microservice.customer.config = [
	customerId: "43356",
	apiKey: "AKIAIOSFODNN7EXAMPLE",
	apiSecret: "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY"
]

