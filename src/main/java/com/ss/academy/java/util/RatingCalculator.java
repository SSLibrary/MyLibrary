package com.ss.academy.java.util;

import java.text.DecimalFormat;
import java.util.List;

import com.ss.academy.java.model.rating.Rating;

public class RatingCalculator {
	public static double calculate(List<Rating> bookRatings) {
		double result = 0;
		int bookRatingsCount = bookRatings.size();

		for (Rating rating : bookRatings) {
			result += Double.valueOf(rating.getRatingValue());
		}

		result = result / bookRatingsCount;

		return RoundTo2Decimals(result);
	}

	private static double RoundTo2Decimals(double val) {
		DecimalFormat df2 = new DecimalFormat("#.##");
		return Double.valueOf(df2.format(val));
	}
}
