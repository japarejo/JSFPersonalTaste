package com.japarejo.personaltaste.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.donut.DonutChartOptions;
import org.primefaces.model.charts.optionconfig.elements.Elements;
import org.primefaces.model.charts.optionconfig.elements.ElementsLine;
import org.primefaces.model.charts.radar.RadarChartDataSet;
import org.primefaces.model.charts.radar.RadarChartModel;
import org.primefaces.model.charts.radar.RadarChartOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.japarejo.personaltaste.model.entities.Artwork;
import com.japarejo.personaltaste.model.entities.ArtworkType;
import com.japarejo.personaltaste.services.ArtworkService;
import com.japarejo.personaltaste.services.UserService;

@Controller("StatisticsController")
@SessionScope
public class StatisticsBackingBean implements Serializable {

	@Autowired
	UserService userService;
	
	@Autowired
	ArtworkService artworkService;

	public DonutChartModel getDonutModel() {
		
		Map<String, Number> rawdata = computeCurrentUserData();
		List<String> types=new ArrayList<>(rawdata.keySet());
		List<Number> values = new ArrayList<>();
		for(String type:types) {
			values.add(rawdata.get(type));
		}
		DonutChartModel donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();        
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        for(String type:types)
        	bgColors.add(randomColor());        
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);        
        data.setLabels(types);

        donutModel.setData(data);
        System.out.println(donutModel.getOptions());
		return donutModel;
		
	}
	
	public RadarChartModel getRadarChartModel() {
		RadarChartModel radarModel = new RadarChartModel();
        ChartData data = new ChartData();

        List<ArtworkType> types=new ArrayList<>(artworkService.getAllArtworkTypes());
        Map<String,Map<String,Number>> usersData=createUsersData();
        List<String> users=new ArrayList<String>(usersData.keySet());
        RadarChartDataSet radarDataSet;
        List<Number> dataVal ;
        Number value;
        for(String user:users) {
        	radarDataSet= new RadarChartDataSet();
        	radarDataSet.setLabel(user);
        	radarDataSet.setFill(false);        	
        	radarDataSet.setBorderColor(randomColor());        
        
        	dataVal = new ArrayList<>();
        	for(ArtworkType type:types) {
        		value=usersData.get(user).get(type.getName());
        		if(value!=null)
        			dataVal.add(value);
        		else
        			dataVal.add(0);
        	}        
        	radarDataSet.setData(dataVal);
        	data.addChartDataSet(radarDataSet);
        }
        
        data.setLabels(types.stream().map(a->a.getName()).collect(Collectors.toList()));

        /* Options */
        RadarChartOptions options = new RadarChartOptions();
        Elements elements = new Elements();
        ElementsLine elementsLine = new ElementsLine();
        elementsLine.setTension(0);
        elementsLine.setBorderWidth(3);
        elements.setLine(elementsLine);
        options.setElements(elements);

        radarModel.setOptions(options);
        radarModel.setData(data);
        return radarModel;
	}

	private Map<String, Map<String, Number>> createUsersData() {
		List<String> users=userService.getUserNames();
		Map<String, Map<String, Number>> result=new HashMap<String, Map<String,Number>>();
		for(String username:users)
			result.put(username,computeUserData(username));
		return result;
	}

	private String randomColor() {
		return "rgb("+(int)(Math.random()*256)+","+(int)(Math.random()*256)+","+(int)(Math.random()*256)+")";
	}

	private DonutChartOptions createOptions() {
		return new DonutChartOptions();
	}

	private Map<String, Number> computeCurrentUserData() {
		return computeUserData(userService.getCurrentUser().getUsername());
	}

	private Map<String, Number> computeUserData(String username) {
		Map<ArtworkType, List<Artwork>> rawdata = artworkService.getFavoritos(username).stream()
				.collect(Collectors.groupingBy(artwork -> artwork.getType()));
		Map<String, Number> data = new HashMap<>();
		for (ArtworkType type : rawdata.keySet()) {
			data.put(type.getName(), rawdata.get(type).size());
		}
		return data;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;				
		return true;
	}


	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8186679962203852390L;
}
