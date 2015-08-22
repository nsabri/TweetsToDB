/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getTweets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
import methods.tweetsToSql;

/**
 *
 * @author nSabri
 */
public class GetTW {

    public static tweetsToSql tweetsToSql = new tweetsToSql();

    public static void main(String[] args) {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("your consumer key");
        cb.setOAuthConsumerSecret("your consumer secret");
        cb.setOAuthAccessToken("your access token");
        cb.setOAuthAccessTokenSecret("your access token secret");

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build())
                .getInstance();

        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStatus(Status status) {
                User user = status.getUser();
                String name = user.getName();
                String description = user.getDescription();
                String text = status.getText();
                
                System.out.println("::::::::::::::::tweets received::::::::::");
                System.out.println("name :" + name);
                System.out.println("description:" + description);
                System.out.println("text :" + text);
                
                

                try {
                    tweetsToSql.add(name, description, text);
                } catch (SQLException ex) {
                    Logger.getLogger(GetTW.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(":::::::::::::wrote to the database::::::");

            }

            @Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStallWarning(StallWarning arg0) {
                // TODO Auto-generated method stub

            }

        };

        FilterQuery fq = new FilterQuery();
        String keywords[] = {"iphone"};
        fq.track(keywords);
        twitterStream.addListener(listener);
        twitterStream.filter(fq);

    }

}
