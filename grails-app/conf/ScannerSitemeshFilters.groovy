import org.codehaus.groovy.grails.commons.ConfigurationHolder

class ScannerSitemeshFilters {
	Set<Integer> disabledRefresh = []
	
	def filters = {
		/*
		 * The grails sitemesh implementation refreshes its configuration outside the normal scope of
		 * the resource reloading.  If general scanning is disabled (or sitemesh scanning is disabled
		 * specifically) this filter will disable the refresh behavior.
		 */
		
		disableRefreshFilter(controller: '*', action: '*') {
			before = {
				Boolean disableRefresh = (
					ConfigurationHolder.config.grails?.scan?.disable
					|| ConfigurationHolder.config.grails?.scan?.sitemesh?.disable
				)
				
				if(disableRefresh) {
					def sitemeshFactory = servletContext.getAttribute('sitemesh.factory')
					
					if(sitemeshFactory && !disabledRefresh.contains(sitemeshFactory.hashCode())) {
						log.info "Disabling refresh behavior for sitemesh factory ${sitemeshFactory}"
						
						sitemeshFactory.metaClass.refresh = { ->
							long time = System.currentTimeMillis()
							configLastCheck = time;
							
							log.info "inside the overriden refresh method"
							
							if(configFile != null && !configLastCheck) {
								loadConfig()
							}
						}
						disabledRefresh << sitemeshFactory.hashCode()
					}
				}
			}
		}
	}
}
