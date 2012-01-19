Grails Scanner Plugin
===

A plugin to customize grails resource and configuration scanning behavior.  At the time of writing, this allows a developer to disable the automatic resource scanning for Grails 1.3.x environments.

Currently, it has only been tested with Grails Version 1.3.7.

Usage Examples
---


## Disable scanning universally

**grails-app/conf/Config.groovy**

	grails.scan.disable = true

## Disable scanning for the production environment only

**grails-app/conf/Config.groovy**

	environments {
	    production {
	        grails.serverURL = "http://www.changeme.com"
			grails.scan.disable = true
	    }
	    development {
	        grails.serverURL = "http://localhost:8080/${appName}"
	    }
	    test {
	        grails.serverURL = "http://localhost:8080/${appName}"
	    }
	}