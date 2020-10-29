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
	List<Bowler> bowlersList = new ArrayList<>();

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
	 * 
	 * @param csvFilePath
	 * @throws IPLAnalyserException
	 */
	public void loadBowler(String csvFilePath) throws IPLAnalyserException {

		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			new CSVBuilderFactory();
			ICSVBuilder csvBuilderCustom = CSVBuilderFactory.createCSVBuilder();
			runsList = csvBuilderCustom.getCSVFileList(reader, Bowler.class);
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

	/**
	 * Usecase7
	 * 
	 * @return
	 * @throws IPLAnalyserException
	 */
	public String topBowlingAvg() throws IPLAnalyserException {
		if (bowlersList == null || bowlersList.size() == 0) {
			throw new IPLAnalyserException("No Census Data", IPLAnalyserException.Exception.NO_CENSUS_DATA);
		}
		double max = bowlersList.stream().filter(s -> (s.average != "-")).map(s -> Double.parseDouble(s.average))
				.max(Double::compare).get();
		List<Bowler> player = bowlersList.stream().filter(s -> s.average.equals(Double.toString(max)))
				.collect(Collectors.toList());
		System.out.println(player.get(0).player);
		return player.get(0).player;
	}

	/**
	 * Usecase8
	 * 
	 * @return
	 * @throws IPLAnalyserException
	 */
	public String bowlerStrikeRate() throws IPLAnalyserException {
		if (bowlersList == null || bowlersList.size() == 0) {
			throw new IPLAnalyserException("No Census Data", IPLAnalyserException.Exception.NO_CENSUS_DATA);
		}
		double max = bowlersList.stream().filter(s -> (s.strikeRate > 0)).map(s -> s.strikeRate).max(Double::compare)
				.get();
		List<Bowler> player = bowlersList.stream().filter(s -> s.strikeRate == max).collect(Collectors.toList());
		System.out.println(player.get(0).player);
		return player.get(0).player;
	}

	/**
	 * Usecase9
	 * 
	 * @return
	 * @throws IPLAnalyserException
	 */
	public String bowlerEconomyRate() throws IPLAnalyserException {
		if (bowlersList == null || bowlersList.size() == 0) {
			throw new IPLAnalyserException("No Census Data", IPLAnalyserException.Exception.NO_CENSUS_DATA);
		}
		double maxEconomy = bowlersList.stream().filter(s -> (s.economy > 0)).map(s -> s.economy).max(Double::compare)
				.get();
		List<Bowler> player = bowlersList.stream().filter(s -> s.economy == maxEconomy).collect(Collectors.toList());
		System.out.println(player.get(0).player);
		return player.get(0).player;
	}

	/**
	 * Usecase10
	 * 
	 * @return
	 * @throws IPLAnalyserException
	 */
	public String bowlerBestStrikeRatewith4w5w() throws IPLAnalyserException {
		if (bowlersList == null || bowlersList.size() == 0) {
			throw new IPLAnalyserException("No Census Data", IPLAnalyserException.Exception.NO_CENSUS_DATA);
		}
		int max4wAnd5w = bowlersList.stream().map(s -> s.fourWickets + s.fiveWickets).max(Integer::compare).get();
		double highStrikeRate = bowlersList.stream().filter(s -> s.fourWickets + s.fiveWickets == max4wAnd5w)
				.map(s -> s.strikeRate).max(Double::compare).get();
		List<Bowler> player = bowlersList.stream().filter(s -> s.strikeRate == highStrikeRate)
				.collect(Collectors.toList());
		return player.get(0).player;
	}

	/**
	 * Usecase11
	 * 
	 * @return
	 * @throws IPLAnalyserException
	 */
	public String bowlerGreatAvgwithBestStrikeRate() throws IPLAnalyserException {
		if (bowlersList == null || bowlersList.size() == 0) {
			throw new IPLAnalyserException("No Census Data", IPLAnalyserException.Exception.NO_CENSUS_DATA);
		}

		double highStrikeRate = bowlersList.stream().filter(s -> s.strikeRate > 0).map(s -> s.strikeRate)
				.max(Double::compare).get();
		double maxAvg = bowlersList.stream().filter(s -> s.strikeRate == highStrikeRate)
				.map(s -> Double.parseDouble(s.average)).max(Double::compare).get();
		List<Bowler> player = bowlersList.stream().filter(s -> Double.parseDouble(s.average) == maxAvg)
				.collect(Collectors.toList());
		return player.get(0).player;
	}

	/**
	 * Usecase12
	 * 
	 * @return
	 * @throws IPLAnalyserException
	 */
	public String bowlerWithMaxWicketsAndBestBowlingAvg() throws IPLAnalyserException {
		if (bowlersList == null || bowlersList.size() == 0) {
			throw new IPLAnalyserException("No Census Data", IPLAnalyserException.Exception.NO_CENSUS_DATA);
		}
		int maxWickets = bowlersList.stream().filter(s -> (s.wickets > 0)).map(s -> s.wickets).max(Integer::compare)
				.get();
		double bestAvg = bowlersList.stream().filter(s -> s.wickets == maxWickets)
				.map(s -> Double.parseDouble(s.average)).max(Double::compare).get();
		List<Bowler> player = bowlersList.stream().filter(s -> s.average.equals(Double.toString(bestAvg)))
				.collect(Collectors.toList());
		return player.get(0).player;
	}
	
}
