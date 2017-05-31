package mainSites.urlparser;



public interface Status {
    int STATUS_OK = 1;
    int STATUS_WARN = 2;
    int STATUS_CHECKED = 3;
    void setStatus(int status);
    int getStatus();
}
