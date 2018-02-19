package app;

import app.exceptions.EmptyFavsException;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


import java.util.List;

public class TwitterApi{


    PropertiesReader reader = new PropertiesReader();


    public String twitter(){

        ConfigurationBuilder cf = new ConfigurationBuilder();
        cf.setDebugEnabled(true)
                .setOAuthConsumerKey(reader.getConsumerKey())
                .setOAuthConsumerSecret(reader.getConsumerSecret())
                .setOAuthAccessToken(reader.getAccessToken())
                .setOAuthAccessTokenSecret(reader.getAccessTokenSecret());
        TwitterFactory tf= new TwitterFactory(cf.build());
        Twitter twitter = tf.getInstance();
        try {
            twitter.getHomeTimeline();

            List<Status> status = twitter.getFavorites();
            if(status.isEmpty()) throw new EmptyFavsException(" No Favourites ");
            String check;
            String last="";
            for(Status st : status)
            {

                check=st.getUser().getName()+" :    "+st.getText();
                last=last+"\n"+check;

            }

            return last;
        } catch (TwitterException e) {
            GUI.log.error("Twitter Exception!");
        }
        catch(EmptyFavsException e)
        {
            GUI.log.error("Empty favourites exception!", e);
            return "There is no favourites to show...";
        }
        return null;



    }

}
