package com.stalin.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@RestController
public class RepositoryDetailsController {

    @Autowired
    private Environment env;

	@RequestMapping("/")
	public String getRepos() throws IOException {
		GitHub github = new GitHubBuilder().withPassword("stalin.lenin@gmail.com", "XXXXXXXX").build();
		GHRepositorySearchBuilder builder = github.searchRepositories();
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/trends")
	public Map<String, String> getTwitterTrends(@RequestParam("placeid") String trendPlace, @RequestParam("count") String trendCount) {
		String consumerKey = "HpwdfLFZLON0mT8BLWSSYfTxs";// env.getProperty("CONSUMER_KEY");
		String consumerSecret = "0FKwKj0GuPMz50dYTYQ3EQxqHfSyNsxipyfPGgXemMHkfWuR3G";// env.getProperty("CONSUMER_SECRET");
		String accessToken = "19703354-imHt92QNIdQLkRiV5qQXQw5CLoJmZM5stZkJ05796";//env.getProperty("ACCESS_TOKEN");
		String accessTokenSecret = "G1yk87c6rXTd6B4kcKzJlfdsQyKlp0n8YYamya6D2M1eY";//env.getProperty("ACCESS_TOKEN_SECRET");
		System.out.println("consumerKey "+consumerKey+" consumerSecret "+consumerSecret+" accessToken "+accessToken+" accessTokenSecret "+accessTokenSecret);		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		        .setOAuthConsumerKey(consumerKey)
				.setOAuthConsumerSecret(consumerSecret)
				.setOAuthAccessToken(accessToken)
				.setOAuthAccessTokenSecret(accessTokenSecret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		System.out.println("Twitter Factory "+tf);
		Twitter twitter = tf.getInstance();
		System.out.println("Twitter "+twitter);
		Map<String, String> trendDetails = new HashMap<String, String>();
		try {
			Trends trends = twitter.getPlaceTrends(Integer.parseInt(trendPlace));
			System.out.println("After API call");
			int count = 0;
			for (Trend trend : trends.getTrends()) {
				if (count < Integer.parseInt(trendCount)) {
					trendDetails.put(trend.getName(), trend.getURL());
					count++;
				}
			}
		} catch (TwitterException e) {
            trendDetails.put("Twitter Exception", e.getMessage());
		}catch (Exception e) {
            trendDetails.put("Exception", e.getMessage());
		}

		return trendDetails;
	}

}
