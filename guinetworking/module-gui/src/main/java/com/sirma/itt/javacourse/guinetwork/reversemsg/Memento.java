package com.sirma.itt.javacourse.guinetwork.reversemsg;

import java.util.ArrayList;

/**
 * Hold a list of saved states.
 * 
 * @author Radoslav
 */
public class Memento<T> {

	private ArrayList<T> savedData = new ArrayList<T>();
	private int currentPos = 0;

	/**
	 * Inserts state into the saved states list.
	 * 
	 * @param state
	 *            The state.
	 */
	public void save(T state) {
		savedData.add(state);
		currentPos = savedData.size() - 1;
	}

	/**
	 * Decrease the current position value and return the previous state.
	 * 
	 * @return The previous state.
	 * @throws IndexOutOfBoundsException
	 *             If no previous state is available.
	 */
	public T undo() throws IndexOutOfBoundsException {
		if (currentPos > 0) {
			currentPos--;
			return savedData.get(currentPos);
		} else {
			throw new IndexOutOfBoundsException();
		}

	}

	/**
	 * Increase the current position value and return the next state if the
	 * counter has been gone down.
	 * 
	 * @return The state.
	 * @throws IndexOutOfBoundsException
	 *             If no next state is available.
	 */
	public T redo() throws IndexOutOfBoundsException {
		if (currentPos < savedData.size() - 1) {
			currentPos++;
			return savedData.get(currentPos);
		} else {
			throw new IndexOutOfBoundsException();
		}

	}

}
