package net.nsquared.playground.swingoflife.services;

import net.nsquared.playground.swingoflife.Cell;

/**
 * Rule for the Game of Life, used by {@link RuleExecutor}.
 * Multiple implementations can be available, accessible via mapped configurations to various rule engines.
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 *
 */
public interface ConwayRule {
	
	void apply(Cell cell);

}
