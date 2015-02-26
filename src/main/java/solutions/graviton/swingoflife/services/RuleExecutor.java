package solutions.graviton.swingoflife.services;

import solutions.graviton.swingoflife.Cell;

/**
 * Rule engine for Game of Life. Visitor style decoupling of rule execution and the "universe" building blocks.
 * Allows potential rule variations, runtime rule-system swapping, etc.
 * 
 * @author Sean (nenad.natoshevic@gmail.com)
 *
 */
public interface RuleExecutor
{
	void execute(Cell cell);
}
