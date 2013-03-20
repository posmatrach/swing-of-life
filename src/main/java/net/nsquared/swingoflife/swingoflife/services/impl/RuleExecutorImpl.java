package net.nsquared.swingoflife.swingoflife.services.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.nsquared.swingoflife.swingoflife.Cell;
import net.nsquared.swingoflife.swingoflife.enums.CellState;
import net.nsquared.swingoflife.swingoflife.services.ConwayRule;
import net.nsquared.swingoflife.swingoflife.services.RuleExecutor;


/**
 * Default rule-set based on {@link CellState}
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 *  
 */
public class RuleExecutorImpl implements RuleExecutor {
	
	private Map<CellState, Set<ConwayRule>> rules;
	
	public RuleExecutorImpl(Map<CellState, Set<ConwayRule>> configuration) {
		this.rules = new HashMap<CellState, Set<ConwayRule>>(configuration);
	}

	public void execute(Cell cell) {
		// TODO Auto-generated method stub
		final Set<ConwayRule> stateRules = rules.get(cell.getState());
		
		for (ConwayRule rule : stateRules) {
			rule.apply(cell);
		}
		
		//TODO  Implement rule engine as chain of command.
	}

}
