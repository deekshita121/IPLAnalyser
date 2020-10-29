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
			String playerName = iplAnalyser.topBattingAvg();
			Assert.assertEquals("MS Dhoni", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenCsvDataShouldReturnTopStrikingRates() {
		try {
			iplAnalyser.loadPlayerRuns(PLAYER_RUNS_DATA);
			String playerName = iplAnalyser.strikingRate();
			Assert.assertEquals("Ishant Sharma", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenCsvDataShouldReturnCricketerOfMax6sand4s() {
		try {
			iplAnalyser.loadPlayerRuns(PLAYER_RUNS_DATA);
			String playerName = iplAnalyser.maxFoursandSixes();
			Assert.assertEquals("Andre Russell", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenCsvDataShouldReturnCricketerOfBestStrikewith6sAnd4s() {
		try {
			iplAnalyser.loadPlayerRuns(PLAYER_RUNS_DATA);
			String playerName = iplAnalyser.bestStrikeRatewith6sAnd4s();
			Assert.assertEquals("Andre Russell", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenCsvDataShouldReturnCricketerOfBestStrikeRatewithGreatAvg() {
		try {
			iplAnalyser.loadPlayerRuns(PLAYER_RUNS_DATA);
			String playerName = iplAnalyser.bestStrikeRatewithGreatAvg();
			Assert.assertEquals("MS Dhoni", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
}
