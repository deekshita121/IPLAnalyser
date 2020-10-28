package com.capgemini.iplanalyser;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest {

	private static final String PLAYER_RUNS_DATA = "C:\\Users\\Lenovo\\git\\IPLAnalyser\\IPLAnalyser\\src\\main\\Resources\\WP DP Data_01 IPL2019FactsheetMostRuns.csv";

	IPLAnalyser iplAnalyser = new IPLAnalyser();

	@Test
	public void givenCsvDataShouldReturnTopBattingAvg() {
		try {
			iplAnalyser.loadPlayerRuns(PLAYER_RUNS_DATA);
			String playerName = iplAnalyser.getTopBattingAvg();
			Assert.assertEquals("MS Dhoni", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
}
