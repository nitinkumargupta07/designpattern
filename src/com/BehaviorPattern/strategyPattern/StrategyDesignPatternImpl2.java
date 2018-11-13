package com.BehaviorPattern.strategyPattern;

/**
 * we're calculating monthly interest on different types of bank accounts.
 * Initially, we're only dealing with two account types — current accounts
 * paying 2% interest per annum, and savings accounts paying 4% per annum.
 * <li>https://dzone.com/articles/design-patterns-the-strategy-and-factory-patterns</li>
 * 
 * @author nitin
 *
 */
enum AccountTypes {
	CURRENT, SAVINGS, STANDARD_MONEY_MARKET, HIGH_ROLLER_MONEY_MARKET
}

/**
 * * we're calculating monthly interest on different types of bank accounts.
 * Initially, we're only dealing with two account types — current accounts
 * paying 2% interest per annum, and savings accounts paying 4% per annum.
 *
 */
class InterestCalculator {
	public double calculateInterest(AccountTypes accountType, double accountBalance) {
		switch (accountType) {
		case CURRENT:
			return accountBalance * (0.02 / 12); // Monthly interest rate is annual rate / 12 months.
		case SAVINGS:
			return accountBalance * (0.04 / 12);
		default:
			return 0;
		}
	}
}

/**
 * Our next requirement is to add support for two different money market
 * accounts — a standard money market account paying 5% per annum, and a special
 * "high-roller" money market account that pays 7.5%, but only if the customer
 * maintains a minimum balance of R100 000.00. We modify our calculator
 * accordingly.
 */
class InterestCalculatorUpdated1 {
	public double calculateInterest(AccountTypes accountType, double accountBalance) {
		switch (accountType) {
		case CURRENT:
			return accountBalance * (0.02 / 12); // Monthly interest rate is annual rate / 12 months.
		case SAVINGS:
			return accountBalance * (0.04 / 12);
		case STANDARD_MONEY_MARKET:
			return accountBalance * (0.06 / 12);
		case HIGH_ROLLER_MONEY_MARKET:
			return accountBalance < 100000.00 ? 0 : accountBalance * (0.075 / 12);
		default:
			return 0;
		}
	}
}

interface InterestCalculationStrategy {
	double calculateInterest(double accountBalance); // Note the absence of an access modifier - interface methods are
														// implicitly public.
}

class CurrentAccountInterestCalculation implements InterestCalculationStrategy {
	@Override
	public double calculateInterest(double accountBalance) {
		return accountBalance * (0.02 / 12);
	}
}

class SavingsAccountInterestCalculation implements InterestCalculationStrategy {
	@Override
	public double calculateInterest(double accountBalance) {
		return accountBalance * (0.04 / 12);
	}
}

class MoneyMarketInterestCalculation implements InterestCalculationStrategy {
	@Override
	public double calculateInterest(double accountBalance) {
		return accountBalance * (0.06 / 12);
	}
}

class HighRollerMoneyMarketInterestCalculation implements InterestCalculationStrategy {
	@Override
	public double calculateInterest(double accountBalance) {
		return accountBalance < 100000.00 ? 0 : accountBalance * (0.075 / 12);
	}
}

class NoInterestCalculation implements InterestCalculationStrategy {
	@Override
	public double calculateInterest(double accountBalance) {
		return 0;
	}
}

class InterestCalculationStrategyFactory {
	private final InterestCalculationStrategy currentAccountInterestCalculationStrategy = new CurrentAccountInterestCalculation();
	private final InterestCalculationStrategy savingsAccountInterestCalculationStrategy = new SavingsAccountInterestCalculation();
	private final InterestCalculationStrategy moneyMarketAccountInterestCalculationStrategy = new MoneyMarketInterestCalculation();
	private final InterestCalculationStrategy highRollerMoneyMarketAccountInterestCalculationStrategy = new HighRollerMoneyMarketInterestCalculation();
	private final InterestCalculationStrategy noInterestCalculationStrategy = new NoInterestCalculation();

	// A factory can create a new instance of a class for each request, but since
	// our calculation strategies are stateless,
	// we can hang on to a single instance of each strategy and return that whenever
	// someone asks for it.
	public InterestCalculationStrategy getInterestCalculationStrategy(AccountTypes accountType) {
		switch (accountType) {
		case CURRENT:
			return currentAccountInterestCalculationStrategy;
		case SAVINGS:
			return savingsAccountInterestCalculationStrategy;
		case STANDARD_MONEY_MARKET:
			return moneyMarketAccountInterestCalculationStrategy;
		case HIGH_ROLLER_MONEY_MARKET:
			return highRollerMoneyMarketAccountInterestCalculationStrategy;
		default:
			return noInterestCalculationStrategy;
		}
	}
}

class InterestCalculatorUp1 {
	private final InterestCalculationStrategyFactory interestCalculationStrategyFactory = new InterestCalculationStrategyFactory();

	public double calculateInterest(AccountTypes accountType, double accountBalance) {
		InterestCalculationStrategy interestCalculationStrategy = interestCalculationStrategyFactory
				.getInterestCalculationStrategy(accountType);
		return interestCalculationStrategy.calculateInterest(accountBalance);
	}
}

public class StrategyDesignPatternImpl2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
