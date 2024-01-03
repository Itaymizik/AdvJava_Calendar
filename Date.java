// Itay Mizikov ; ID: 315541615 ; 20/5/23
package q2;

import java.io.Serializable;

/**
 * The Date class represents a date with day, month, and year values. It
 * implements the Serializable interface to support serialization.
 */
public class Date implements Serializable {

	private int day;
	private int month;
	private int year;

	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	/**
	 * Computes the hash code of this Date object.
	 *
	 * @return the computed hash code value
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	/**
	 * Checks if this Date object is equal to the specified object.
	 *
	 * @param obj the object to compare
	 * @return true if the objects are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	/**
	 * Returns a string representation of this Date object. The format of the string
	 * is "dd/mm/yyyy".
	 *
	 * @return the string representation of the Date object
	 */
	@Override
	public String toString() {
		return ((day < 10 ? "0" : "") + day) + "/" + ((month < 10 ? "0" : "") + month) + "/" + year;
	}
}
