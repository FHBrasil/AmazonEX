package br.flieger.storecatalogfeedparser.parser;

/**
 * 
 * @author franthescolly
 *
 */
public class ParseableEntry {
	
	/**
	 * 
	 * @author franthescolly
	 *
	 */
	public enum EntryPart {

		/**
		 * 
		 */
		KEY,

		/**
		 * 
		 */
		VALUE
	}

	/**
	 * 
	 */
	private final String input, output;

	/**
	 * 
	 */
	private final boolean BOMRequired;

	/**
	 * 
	 * @param input
	 * @param output
	 */
	public ParseableEntry(final String input, final String output, final boolean BOMRequired) {

		this.input = input;

		this.output = output;

		this.BOMRequired = BOMRequired;
	}

	/**
	 * @return the input
	 */
	public final String getInput() {
		return input;
	}

	/**
	 * @return the output
	 */
	public final String getOutput() {
		return output;
	}

	/**
	 * @return the requiredBOM
	 */
	public final boolean isBOMRequired() {
		return BOMRequired;
	}

	@Override
	public String toString() {
		return (input + " : " + output + (BOMRequired ? "" : " [NO BOM]"));
	}
}