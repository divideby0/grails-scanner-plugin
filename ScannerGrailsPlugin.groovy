import org.codehaus.groovy.grails.commons.ConfigurationHolder

class ScannerGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp",
			"grails-app/views/index.gsp"
    ]

    // TODO Fill in these fields
    def author = "Cedric G Hurst II"
    def authorEmail = "cedric@spantree.net"
    def title = "Grails Scanner Plugin"
    def description = '''\\
A plugin to customize grails resource and configuration scanning behavior
'''

    // URL to the plugin's documentation
    def documentation = "https://github.com/divideby0/grails-scanner-plugin"

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before 
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        def disableScanning = ConfigurationHolder.config.grails?.scan?.disable
	
		if(disableScanning) {
			manager.metaClass.startPluginChangeScanner = { ->
				log.info "Ignoring request to start plugin change scanner as it is disabled."
			}
		}
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
