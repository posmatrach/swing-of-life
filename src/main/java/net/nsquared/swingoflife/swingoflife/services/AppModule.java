package net.nsquared.swingoflife.swingoflife.services;

import net.nsquared.swingoflife.swingoflife.enums.CellState;

import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;

/**
 * Convenience class for Tapestry IoC configuration.
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 *
 */
public class AppModule {
	
	public static void bind(ServiceBinder binder) {
		
	}
	
	@Contribute(RuleExecutor.class)
	public static void provideConwayRules(MappedConfiguration<CellState, ConwayRule> configuration) {
		
	}

}
