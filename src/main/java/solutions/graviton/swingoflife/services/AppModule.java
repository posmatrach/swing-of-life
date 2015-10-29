package solutions.graviton.swingoflife.services;

import java.util.List;

import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.services.ChainBuilder;

import solutions.graviton.swingoflife.services.impl.OverPopulationRule;
import solutions.graviton.swingoflife.services.impl.PreservationRule;
import solutions.graviton.swingoflife.services.impl.PropertiesImpl;
import solutions.graviton.swingoflife.services.impl.ReproductionRule;
import solutions.graviton.swingoflife.services.impl.UnderPopulationRule;

/**
 * Convenience class for Tapestry IoC configuration.
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 * 
 */
public class AppModule
{
	/**
	 * Provides interface-to-implementation bindings for Tapestry IoC
	 * 
	 * @param binder
	 */
	public static void bind(ServiceBinder binder)
	{
		binder.bind(Properties.class, PropertiesImpl.class);
	}
	
	public static ConwayRule buildDefaultRuleExecutor(List<ConwayRule> commands, @InjectService("ChainBuilder") ChainBuilder chainBuilder )
	{
		return chainBuilder.build(ConwayRule.class, commands);
	}
	
	public static void contributeDefaultRuleExecutor(OrderedConfiguration<ConwayRule> configuration)
	{
		configuration.add("reproductionRule", new ReproductionRule());
		configuration.add("preservationRule", new PreservationRule());
		configuration.add("underPopulationRule", new UnderPopulationRule());
		configuration.add("overPopulationRule", new OverPopulationRule());
	}

}
