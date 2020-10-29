package com.capgemini.iplanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.csvbuilder.BuilderException;
import com.capgemini.csvbuilder.CSVBuilderFactory;
import com.capgemini.csvbuilder.ICSVBuilder;

public class IPLAnalyser {
	List<PlayerRuns> runsList = new ArrayList<>();

	/**
	 * Start
	 * 
	 * @param csvFilePath
	 * @throws IPLAnalyserException
	 */
	public void loadPlayerRuns(String csvFilePath) throws IPLAnalyserException {

		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			new CSVBuilderFactory();
			ICSVBuilder csvBuilderCustom = CSVBuilderFactory.createCSVBuilder();
			runsList = csvBuilderCustom.getCSVFileList(reader, PlayerRuns.class);
		} catch (IOException | RuntimeException e) {
			throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.Exception.INCORRECT_FILE);
			// e.printStackTrace();
		} catch (BuilderException e) {
			throw new IPLAnalyserException(e.getMessage(), e.type.name());
			// e.printStackTrace();
		}

	}

	/**
	 * Usecase1
	 * 
	 * @return
	 * @throws IPLAnalyserException
	 */
	public String topBattingAvg() throws IPLAnalyserException {
		if (runsList == null || runsList.size() == 0) {
			throw new IPLAnalyserException("No Census Data", IPLAnalyserException.Exception.NO_CENSUS_DATA);
		}
		double max = runsList.stream().filter(s -> (s.average != "-")).map(s -> Double.parseDouble(s.average))
				.max(Double::compare).get();
		List<PlayerRuns> player = runsList.stream().filter(s -> s.average.equals(Double.toString(max)))
				.collect(Collectors.toList());
		System.out.println(player.get(0).player);
		return player.get(0).player;
	}

	/**
	 * Usecase2
	 * 
	 * @return
	 * @throws IPLAnalyserException
	 */
	public String strikingRate() throws IPLAnalyserException {
		if (runsList == null || runsList.size() == 0) {
			throw new IPLAnalyserException("No Census Data", IPLAnalyserException.Exception.NO_CENSUS_DATA);
		}
		double max = runsList.stream().filter(s -> (s.strikeRate > 0)).map(s -> s.strikeRate).max(Double::compare)
				.get();
		List<PlayerRuns> player = runsList.stream().filter(s -> s.strikeRate == max).collect(Collectors.toList());
		System.out.println(player.get(0).player);
		return player.get(0).player;
	}

	/**
	 * Usecase3
	 * 
	 * @return
	 * @throws IPLAnalyserException
	 */
	public String maxFoursandSixes() throws IPLAnalyserException {
		if (runsList == null || runsList.size() == 0) {
			throw new IPLAnalyserException("No Census Data", IPLAnalyserException.Exception.NO_CENSUS_DATA);
		}
		int maxSixesAndFours = runsList.stream().map(s -> s.fours + s.sixes).max(Integer::compare).get();
		List<PlayerRuns> player = runsList.stream().filter(s -> s.fours + s.sixes == maxSixesAndFours)
				.collect(Collectors.toList());
		return player.get(0).player;
	}

	/**
	 * Usecase4
	 * 
	 * @return
	 * @throws IPLAnalyserException
	 */
	public String bestStrikeRatewith6sAnd4s() throws IPLAnalyserException {
		if (runsList == null || runsList.size() == 0) {
			throw new IPLAnalyserException("No Census Data", IPLAnalyserException.Exception.NO_CENSUS_DATA);
		}
		int maxSixesAndFours = runsList.stream().map(s -> s.fours + s.sixes).max(Integer::compare).get();
		double highStrikeRate = runsList.stream().filter(s -> s.fours + s.sixes == maxSixesAndFours)
				.map(s -> s.strikeRate).max(Double::compare).get();
		List<PlayerRuns> player = runsList.stream().filter(s -> s.strikeRate == highStrikeRate)
				.collect(Collectors.toList());
		return player.get(0).player;
	}

	/**
	 * Usecase5
	 * 
	 * @return
	 * @throws IPLAnalyserException
	 */
	public String bestStrikeRatewithGreatAvg() throws IPLAnalyserException {
		if (runsList == null || runsList.size() == 0) {
			throw new IPLAnalyserException("No Census Data", IPLAnalyserException.Exception.NO_CENSUS_DATA);
		}
		double maxAvg = runsList.stream().filter(s -> (s.average != "-")).map(s -> Double.parseDouble(s.average))
				.max(Double::compare).get();
		double highStrikeRate = runsList.stream().filter(s -> s.average.equals(Double.toString(maxAvg)))
				.map(s -> s.strikeRate).max(Double::compare).get();
		List<PlayerRuns> player = runsList.stream().filter(s -> s.strikeRate == highStrikeRate)
				.collect(Collectors.toList());
		return player.get(0).player;
	}

	/**
	 * Usecase6
	 * 
	 * @return
	 * @throws IPLAnalyserException
	 */
	public String bestAvgwithMaxRuns() throws IPLAnalyserException {
		if (runsList == null || runsList.size() == 0) {
			throw new IPLAnalyserException("No Census Data", IPLAnalyserException.Exception.NO_CENSUS_DATA);
		}
		int maxRuns = runsList.stream().filter(s -> (s.runs > 0)).map(s -> s.runs).max(Integer::compare).get();
		double bestAvg = runsList.stream().filter(s -> s.runs == maxRuns).map(s -> Double.parseDouble(s.average))
				.max(Double::compare).get();
		List<PlayerRuns> player = runsList.stream().filter(s -> s.average.equals(Double.toString(bestAvg)))
				.collect(Collectors.toList());
		return player.get(0).player;
	}

}
