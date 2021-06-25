package com.japarejo.personaltaste.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.donut.DonutChartOptions;
import org.primefaces.model.charts.optionconfig.elements.Elements;
import org.primefaces.model.charts.optionconfig.elements.ElementsLine;
import org.primefaces.model.charts.radar.RadarChartDataSet;
import org.primefaces.model.charts.radar.RadarChartModel;
import org.primefaces.model.charts.radar.RadarChartOptions;

import com.japarejo.personaltaste.model.entities.Artwork;
import com.japarejo.personaltaste.model.entities.ArtworkType;
import com.japarejo.personaltaste.model.repositories.FavouritesRepository;

@ManagedBean(name="StatisticsController")
@SessionScoped
public class StatisticsBackingBean implements Serializable {

	@ManagedProperty(value = "#{LoginController}")
	LoginBackingBean login;
	
	@ManagedProperty(value = "#{favouritesRepository}")
	FavouritesRepository favRepo;

	public DonutChartModel donutModel() {
		
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

        List<ArtworkType> types=favRepo.findAllArtworkTypes();
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
		List<String> users=favRepo.getUserNames();
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
		return computeUserData(login.getCurrentUser().getUsername());
	}

	private Map<String, Number> computeUserData(String username) {
		Map<ArtworkType, List<Artwork>> rawdata = favRepo.getFavoritos(username).stream()
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
		result = prime * result + ((favRepo == null) ? 0 : favRepo.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		StatisticsBackingBean other = (StatisticsBackingBean) obj;
		if (favRepo == null) {
			if (other.favRepo != null)
				return false;
		} else if (!favRepo.equals(other.favRepo))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}



	public LoginBackingBean getLogin() {
		return login;
	}

	public void setLogin(LoginBackingBean login) {
		this.login = login;
	}

	public FavouritesRepository getFavRepo() {
		return favRepo;
	}

	public void setFavRepo(FavouritesRepository favRepo) {
		this.favRepo = favRepo;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8186679962203852390L;
}
