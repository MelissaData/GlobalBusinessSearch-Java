package melissadata.businesssearch.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BusinessSearchOptions {
    private final StringProperty optionSearchType;
    private final StringProperty optionSearchCondition;
    private final StringProperty optionSortBy;
    private final StringProperty optionPage;
    private final StringProperty optionRecordsPerPage;
    private final StringProperty optionReturnAllPages;

    public BusinessSearchOptions() {
        optionSearchType = new SimpleStringProperty("");
        optionSearchCondition = new SimpleStringProperty("");
        optionSortBy = new SimpleStringProperty("");
        optionPage = new SimpleStringProperty("");
        optionRecordsPerPage = new SimpleStringProperty("");
        optionReturnAllPages = new SimpleStringProperty("");
    }

    public String generateOptionString() {
        String optionString = "";

        if(!getOptionSearchType().equals(""))
            optionString += "SearchType:" + getOptionSearchType();
        if(!getOptionSearchCondition().equals("") && !optionString.equals(""))
            optionString += ",SearchCondition:" + getOptionSearchCondition();
        else if(!getOptionSearchCondition().equals("") && optionString.equals(""))
            optionString += "SearchCondition:" + getOptionSearchCondition();

        if(!getOptionSortBy().equals("") && !optionString.equals(""))
            optionString += ",SortBy:" + getOptionSortBy();
        else if(!getOptionSortBy().equals("") && optionString.equals(""))
            optionString += "SortBy:" + getOptionSortBy();

        if(!getOptionPage().equals("") && !optionString.equals(""))
            optionString += ",Page:" + getOptionPage();
        else if(!getOptionPage().equals("") && optionString.equals(""))
            optionString += "Page:" + getOptionPage();

        if(!getOptionRecordsPerPage().equals("") && !optionString.equals(""))
            optionString += ",RecordsPerPage:" + getOptionRecordsPerPage();
        else if(!getOptionRecordsPerPage().equals("") && optionString.equals(""))
            optionString += "RecordsPerPage:" + getOptionRecordsPerPage();

        if(!getOptionReturnAllPages().equals("") && !optionString.equals(""))
            optionString += ",ReturnAllPages:" + getOptionReturnAllPages();
        else if(!getOptionReturnAllPages().equals("") && optionString.equals(""))
            optionString += "ReturnAllPages:" + getOptionReturnAllPages();
        return optionString;
    }

    public String getOptionSearchType() {
        return optionSearchType.get();
    }

    public StringProperty optionSearchTypeProperty() {
        return optionSearchType;
    }

    public void setOptionSearchType(String optionSearchType) {
        this.optionSearchType.set(optionSearchType);
    }

    public String getOptionSearchCondition() {
        return optionSearchCondition.get();
    }

    public StringProperty optionSearchConditionProperty() {
        return optionSearchCondition;
    }

    public void setOptionSearchCondition(String optionSearchCondition) {
        this.optionSearchCondition.set(optionSearchCondition);
    }

    public String getOptionSortBy() {
        return optionSortBy.get();
    }

    public StringProperty optionSortByProperty() {
        return optionSortBy;
    }

    public void setOptionSortBy(String optionSortBy) {
        this.optionSortBy.set(optionSortBy);
    }

    public String getOptionPage() {
        return optionPage.get();
    }

    public StringProperty optionPageProperty() {
        return optionPage;
    }

    public void setOptionPage(String optionPage) {
        this.optionPage.set(optionPage);
    }

    public String getOptionRecordsPerPage() {
        return optionRecordsPerPage.get();
    }

    public StringProperty optionRecordsPerPageProperty() {
        return optionRecordsPerPage;
    }

    public void setOptionRecordsPerPage(String optionRecordsPerPage) {
        this.optionRecordsPerPage.set(optionRecordsPerPage);
    }

    public String getOptionReturnAllPages() {
        return optionReturnAllPages.get();
    }

    public StringProperty optionReturnAllPagesProperty() {
        return optionReturnAllPages;
    }

    public void setOptionReturnAllPages(String optionReturnAllPages) {
        this.optionReturnAllPages.set(optionReturnAllPages);
    }
}
