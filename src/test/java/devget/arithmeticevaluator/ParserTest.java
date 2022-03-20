package devget.arithmeticevaluator;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class ParserTest {

	@Test
	public void parser() {
		Expression exp = new Parser().parse("1234");
		assertEquals(1234, exp.evaluate(null));

		exp = new Parser().parse("a");
		assertEquals(1, exp.evaluate(new HashMap<Character, Integer>() {
			{
				put('a', 1);
			}
		}));

		exp = new Parser().parse("1+1+2");
		assertEquals(4, exp.evaluate(null));
		
		exp = new Parser().parse("b+a+2");
		assertEquals(36, exp.evaluate(new HashMap<Character, Integer>() {
			{
				put('a', 1);
				put('b', 33);
			}
		}));

		exp = new Parser().parse("(b+a)+2");
		assertEquals(36, exp.evaluate(new HashMap<Character, Integer>() {
			{
				put('a', 1);
				put('b', 33);
			}
		}));
		
		exp = new Parser().parse("b+(a+2)");
		assertEquals(36, exp.evaluate(new HashMap<Character, Integer>() {
			{
				put('a', 1);
				put('b', 33);
			}
		}));
		
		exp = new Parser().parse("b-(a+2)");
		assertEquals(30, exp.evaluate(new HashMap<Character, Integer>() {
			{
				put('a', 1);
				put('b', 33);
			}
		}));
		
		exp = new Parser().parse("b*(a+2)");
		assertEquals(99, exp.evaluate(new HashMap<Character, Integer>() {
			{
				put('a', 1);
				put('b', 33);
			}
		}));

		exp = new Parser().parse("b/(a+2)");
		assertEquals(11, exp.evaluate(new HashMap<Character, Integer>() {
			{
				put('a', 1);
				put('b', 33);
			}
		}));
		
		exp = new Parser().parse("-(b/(a+2))");
		assertEquals(-11, exp.evaluate(new HashMap<Character, Integer>() {
			{
				put('a', 1);
				put('b', 33);
			}
		}));

		exp = new Parser().parse("abs(-(b/(a+2)))");
		assertEquals(11, exp.evaluate(new HashMap<Character, Integer>() {
			{
				put('a', 1);
				put('b', 33);
			}
		}));

	}
}
