package com.sirma.itt.javacourse.guinetwork.broadcastmulticast;

/**
 * Creates various news object depending on
 * 
 * @author Radoslav
 */
public class NewsFactory {

	private int type;

	/**
	 * Initialises the type.
	 * 
	 * @param type
	 *            The type.
	 */
	public NewsFactory(int type) {
		this.type = type;
	}

	/**
	 * Creates a specific news by given type.
	 * 
	 * @return The newly created instance.
	 * @throws IllegalArgumentException
	 *             If no such news type exsist.
	 */
	public News generate() throws IllegalArgumentException {
		if (type == 1) {
			return new SportNews();
		} else if (type == 2) {
			return new PoliticalNews();
		} else {
			throw new IllegalArgumentException("No such type of news");
		}
	}

}
