package mainSites.urlparser;

/**
 * класс ссылка, имеет 4 параметра,
 * каждый берется из файла(links.txt)
 * по умолчанию дан только период(24часа)
 */
public class Link implements Status{


    private String shortName;
    private String url;
    private String claz;
    private String value;


    private int period = 1000 * 60 * 60;//час
    private int status = STATUS_OK;

    /**
     * сразу после получения готовой ссылки, достаем из нее то, что нужно
     */

    public Link(String shortName, String url, String claz, int period) {
        this.shortName = shortName;
        this.url = url;
        this.claz = claz;
        this.period = period;

        Pars p = new Pars();
        this.value = p.parse(url, claz);
    }

    public Link(String shortName, String url, String claz) {
        this.shortName = shortName;
        this.url = url;
        this.claz = claz;

        Pars p = new Pars();
        this.value = p.parse(url, claz);
    }

    public Link() {
    }


    public void setStatus(int status){
        this.status=status;
    }
    public int getStatus(){
        return this.status;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getShortName() {
        return shortName;
    }

    public String getUrl() {
        return url;
    }

    public String getClaz() {
        return claz;
    }

    public String getValue() {
        return value;
    }

    public int getPeriod() {
        return period;
    }
}
