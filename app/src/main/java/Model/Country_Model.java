package Model;

public class Country_Model {

    String flag;
    String country;
    String cases;
    String Active;
    String Death;
    String recovered;
    String Today_death;
    String Today_recovered;
    String Today_positive;

    public Country_Model(String country, String cases, String active, String death,
                         String recovered, String today_death, String today_recovered, String today_positive, String flag) {
        this.country = country;
        this.cases = cases;
        this.Active = active;
        this.Death = death;
        this.recovered = recovered;
        this.Today_death = today_death;
        this.Today_recovered = today_recovered;
        this.Today_positive = today_positive;
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public String getCountry() {
        return country;
    }

    public String getCases() {
        return cases;
    }

    public String getActive() {
        return Active;
    }

    public String getDeath() {
        return Death;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getToday_death() {
        return Today_death;
    }

    public String getToday_recovered() {
        return Today_recovered;
    }

    public String getToday_positive() {
        return Today_positive;
    }
}
