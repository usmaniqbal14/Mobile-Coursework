package coursework.mobile.mobilecoursework;
// Usman Iqbal - S1425850

import java.util.Date;

/**
 * Created by Usman on 23/03/2018.
 */

public class RssItems {

    String title;
    String link;
    String description;
    private String georsspoint;
    String pubDate;
    private int daysToComplete = 0;
    private Date startDate;
    private Date endDate;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) { this.description = description; }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getGeorssPoint() {
        return georsspoint;
    }

    public void setGeorssPoint(String georssPoint) {
        this.georsspoint = georssPoint;
    }

}
