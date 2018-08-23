package com.cities.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.cities.domain.graph.Graph;
import com.cities.domain.graph.DirectPair;

@Component
public class CityPathFinderImpl implements ICityPathFinder {
	
	@Value("${city.file.path}")
	private String filePath;

	private  Graph<String> graph;
	
	@PostConstruct
	public  void init() throws IOException {
		Set<String> cities = new HashSet<>();
		Set<DirectPair<String>> directCities = new HashSet<>();
			
		Resource resource = new ClassPathResource(filePath);		
		Stream <String> lines = Files.lines(Paths.get(resource.getURI())); 

		lines.forEach(line -> {
			String[] split = line.split(",");
			DirectPair<String> directlyConnectedCities = new DirectPair<>(split[0].trim(), split[1].trim());
			directCities.add(directlyConnectedCities);
			cities.add(directlyConnectedCities.getLeft());
			cities.add(directlyConnectedCities.getRight());
		});
		lines.close();
		
		graph = new Graph<>(new ArrayList<>(cities), directCities);

	}
	
	@Override
	public boolean areCitiesConnected(String origin, String destination) {
		return graph.isPathPresent(origin, destination);

	}
}
