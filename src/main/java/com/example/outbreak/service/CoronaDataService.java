package com.example.outbreak.service;

import com.example.outbreak.model.CoronaStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaDataService{
    //the raw data link
    //https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv
    private static final String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<CoronaStats> allStats = new ArrayList<>();

    public List<CoronaStats> getAllStats() {
        return allStats;
    }

    @PostConstruct//execute this when execution starts
    @Scheduled(cron = "* * 1 * * *")//second minute hour day month year
//schedule this to be updated every first hour of the day
    public void fetchVirusData() throws IOException, InterruptedException {
        List<CoronaStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        //print out the content in the raw github link for all the corona virus infections
        //System.out.println(httpResponse.body());

        //parse the csv raw data link so that we can have it in a more readable format
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            //loop through the records assigning values to their respective headers
            CoronaStats stats = new CoronaStats();
            stats.setState(record.get("Province/State"));
            stats.setCountry(record.get("Country/Region"));
            //record.size(); to get the last entry as the total cases
            int latestCases = Integer.parseInt(record.get(record.size()-1));
            int prevDayCases = Integer.parseInt(record.get(record.size()-2));
            stats.setLatestTotalCases(latestCases);
            stats.setDiffFromPrevDay(latestCases - prevDayCases);
            //System.out.println(stats);
            newStats.add(stats);
        }
        this.allStats = newStats;
    }

}
