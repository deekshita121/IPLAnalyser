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

	public String getTopBattingAvg() throws IPLAnalyserException {
		if (runsList == null || runsList.size() == 0) {
			throw new IPLAnalyserException("No Census Data", IPLAnalyserException.Exception.NO_CENSUS_DATA);
		}
		double max = runsList.stream().filter(s -> !s.average.equals("-")).map(s -> Double.parseDouble(s.average))
				.max(Double::compare).get();
		List<PlayerRuns> player = runsList.stream().filter(s -> s.average.equals(Double.toString(max)))
				.collect(Collectors.toList());
		return player.get(0).player;
	}
}
