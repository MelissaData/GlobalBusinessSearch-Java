package melissadata.businesssearch.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import melissadata.businesssearch.model.BusinessSearchTransaction;
import melissadata.businesssearch.model.BusinessSearchOptions;
import melissadata.businesssearch.Main;

public class BusinessSearchController  {

    private Main main;
    private BusinessSearchOptions option;

    private BusinessSearchTransaction Transaction;

    @FXML
    private Button buttonSend;
    @FXML
    private Button buttonClear;
    @FXML
    private TabPane tabPane;
    private final int CONFIGURATION_TAB = 0;
    private final int RESPONSE_TAB = 1;

    @FXML
    private TextField inputLicenseKeyText;
    @FXML
    private TextField inputCompanyText;
    @FXML
    private TextField inputPhoneNumberText;
    @FXML
    private TextField inputWebAddressText;

    @FXML
    private TextField inputStockTickerText;
    @FXML
    private TextField inputAddressLine1Text;
    @FXML
    private TextField inputAddressLine2Text;
    @FXML
    private TextField inputLocalityText;
    @FXML
    private TextField inputAdministrativeAreaText;
    @FXML
    private TextField inputPostalText;
    @FXML
    private TextField inputCountryText;
    @FXML
    private ComboBox<String> inputLocationTypeFilter;

    @FXML
    private ComboBox<String> optionSearchTypeBox;
    @FXML
    private ComboBox<String> optionSearchConditionBox;
    @FXML
    private ComboBox<String> optionSortByBox;
    @FXML
    private ComboBox<String> optionPageBox;
    @FXML
    private ComboBox<String> optionRecordsPerPageBox;
    @FXML
    private ComboBox<String> optionReturnAllPagesBox;

    @FXML
    private CheckBox columnCheckAllColumnsCheckbox;
    @FXML
    private CheckBox columnLocationTypeCheckbox;
    @FXML
    private CheckBox columnPhoneCheckbox;
    @FXML
    private CheckBox columnEINCheckbox;
    @FXML
    private CheckBox columnMoveDateCheckbox;
    @FXML
    private CheckBox columnStockTickerCheckbox;
    @FXML
    private CheckBox columnWebAddressCheckbox;
    @FXML
    private CheckBox columnPreviousAddressCheckbox;

    @FXML
    private TextArea RequestTextArea;
    @FXML
    private TextArea ResponseTextArea;

    @FXML
    private RadioButton jsonResponseFormatRadio;
    @FXML
    private RadioButton xmlResponseFormatRadio;
    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public BusinessSearchController() {
        Transaction = new BusinessSearchTransaction();
        option = new BusinessSearchOptions();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        setupOptionSelections();
        initializeFormatRadioButtons();
        selectColumnCheckbox();
        selectAllColumnAction();
        initializeTextFields();
        sendButtonAction();
        clearButtonAction();
        updateRequestText();
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * //@param mainApp
     */
    public void setMainApp(Main main) {
        this.main = main;
    }

    public void sendButtonAction() {
        buttonSend.setOnAction((event) -> {
            ResponseTextArea.setText(Transaction.processTransaction(RequestTextArea.getText()));
            tabPane.getSelectionModel().select(RESPONSE_TAB);
        });
    }

    public void clearButtonAction(){
        buttonClear.setOnAction((event) -> {
            inputCompanyText.clear();
            inputWebAddressText.clear();
            inputPhoneNumberText.clear();
            inputStockTickerText.clear();
            inputAddressLine1Text.clear();
            inputAddressLine2Text.clear();
            inputLocalityText.clear();
            inputPostalText.clear();
            inputAdministrativeAreaText.clear();
            inputCountryText.clear();
            returnToConfiguration();
        });
    }

    public void initializeTextFields(){
        inputLicenseKeyText.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Transaction.setIdentNumber(newvalue);
            updateRequestText();
            returnToConfiguration();
        });

        inputCompanyText.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Transaction.setCompany(newvalue);
            updateRequestText();
            returnToConfiguration();
        });

        inputWebAddressText.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Transaction.setWebAddress(newvalue);
            updateRequestText();
            returnToConfiguration();
        });

        inputPhoneNumberText.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Transaction.setPhoneNumber(newvalue);
            updateRequestText();
            returnToConfiguration();
        });

        inputStockTickerText.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Transaction.setStockTicker(newvalue);
            updateRequestText();
            returnToConfiguration();

        });

        inputAddressLine1Text.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Transaction.setAddressLine1(newvalue);
            updateRequestText();
            returnToConfiguration();
        });

        inputAddressLine2Text.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Transaction.setAddressLine2(newvalue);
            updateRequestText();
            returnToConfiguration();
        });

        inputLocalityText.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Transaction.setLocality(newvalue);
            updateRequestText();
            returnToConfiguration();
        });

        inputPostalText.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Transaction.setPostal(newvalue);
            updateRequestText();
            returnToConfiguration();
        });

        inputAdministrativeAreaText.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Transaction.setAdministrativeArea(newvalue);
            updateRequestText();
            returnToConfiguration();
        });

        inputCountryText.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Transaction.setCountry(newvalue);
            updateRequestText();
            returnToConfiguration();
        });


    }
    // Define what values each combo box can hold
    private void setupOptionSelections() {
        optionSearchTypeBox.setItems(FXCollections.observableArrayList("", "Auto", "CompanySearch", "AddressSearch", "PhoneSearch"));
        optionSearchConditionBox.setItems(FXCollections.observableArrayList("", "Strict", "Loose", "Progressive"));
        optionSortByBox.setItems(FXCollections.observableArrayList("", "PostalCode-Ascending", "PostalCode-Descending",
                                                                             "Locality-Ascending", "Locality-Descending",
                                                                             "AddressLine1-Ascending", "AddressLine1-Descending",
                                                                             "CompanyName-Ascending", "CompanyName-Descending",
                                                                             "MelissaEnterpriseKey-Ascending", "MelissaEnterpriseKey-Descending",
                                                                             "StockTicker-Ascending", "StockTicker-Descending",
                                                                             "WebAddress-Ascending", "WebAddress-Descending",
                                                                             "MoveDate-Ascending", "MoveDate-Descending",
                                                                             "MelissaAddressKey-Ascending", "MelissaAddressKey-Descending",
                                                                             "MelissaAddressKeyBase-Ascending", "MelissaAddressKeyBase-Descending"));
        optionPageBox.setItems(FXCollections.observableArrayList("", "1", "5", "10", "50"));
        optionRecordsPerPageBox.setItems(FXCollections.observableArrayList("", "1", "5", "10", "50"));
        optionReturnAllPagesBox.setItems(FXCollections.observableArrayList("", "True", "False"));
        inputLocationTypeFilter.setItems(FXCollections.observableArrayList("", "Headquarters", "Branch"));
    }

    public void optionSearchTypeBox() {
        option.setOptionSearchCondition(optionSearchTypeBox.getValue());
        Transaction.setOptions(option);
        updateRequestText();
        returnToConfiguration();
    }
    public void optionSearchConditionBox() {
        option.setOptionSearchCondition(optionSearchConditionBox.getValue());
        Transaction.setOptions(option);
        updateRequestText();
        returnToConfiguration();
    }

    public void optionSortByBox() {
        option.setOptionSortBy(optionSortByBox.getValue());
        Transaction.setOptions(option);
        updateRequestText();
        returnToConfiguration();
    }

    public void optionPageBox() {
        option.setOptionPage(optionPageBox.getValue());
        Transaction.setOptions(option);
        updateRequestText();
        returnToConfiguration();
    }

    public void optionRecordsPerPageBox() {
        option.setOptionRecordsPerPage(optionRecordsPerPageBox.getValue());
        Transaction.setOptions(option);
        updateRequestText();
        returnToConfiguration();
    }

    public void optionReturnAllPagesBox() {
        option.setOptionReturnAllPages(optionReturnAllPagesBox.getValue());
        Transaction.setOptions(option);
        updateRequestText();
        returnToConfiguration();
    }

    public void inputLocationTypeFilter() {
        Transaction.setLocationType(inputLocationTypeFilter.getValue());
        updateRequestText();
        returnToConfiguration();
    }

    private void initializeFormatRadioButtons(){
        jsonResponseFormatRadio.setOnAction((event) -> {
            Transaction.setFormat("JSON");
            xmlResponseFormatRadio.setSelected(false);
            updateRequestText();
        });

        xmlResponseFormatRadio.setOnAction((event) -> {
            Transaction.setFormat("XML");
            jsonResponseFormatRadio.setSelected(false);
            updateRequestText();
        });
    }

    private void returnToConfiguration(){
        if(tabPane.getSelectionModel().getSelectedIndex() != 0)	tabPane.getSelectionModel().select(CONFIGURATION_TAB);

    }

    private void selectAllColumnAction() {
        columnCheckAllColumnsCheckbox.setOnAction((event) -> {
            if(!Transaction.isSelectAllColumns()){
                Transaction.setColumnLocationType(true);
                columnLocationTypeCheckbox.setSelected(true);

                Transaction.setColumnPhone(true);
                columnPhoneCheckbox.setSelected(true);

                Transaction.setColumnEIN(true);
                columnEINCheckbox.setSelected(true);

                Transaction.setColumnMoveDate(true);
                columnMoveDateCheckbox.setSelected(true);

                Transaction.setColumnStockTicker(true);
                columnStockTickerCheckbox.setSelected(true);

                Transaction.setColumnWebAddress(true);
                columnWebAddressCheckbox.setSelected(true);

                Transaction.setColumnPreviousAddress(true);
                columnPreviousAddressCheckbox.setSelected(true);

            } else {
                Transaction.setColumnLocationType(false);
                columnLocationTypeCheckbox.setSelected(false);

                Transaction.setColumnPhone(false);
                columnPhoneCheckbox.setSelected(false);

                Transaction.setColumnEIN(false);
                columnEINCheckbox.setSelected(false);

                Transaction.setColumnMoveDate(false);
                columnMoveDateCheckbox.setSelected(false);

                Transaction.setColumnStockTicker(false);
                columnStockTickerCheckbox.setSelected(false);

                Transaction.setColumnWebAddress(false);
                columnWebAddressCheckbox.setSelected(false);

                Transaction.setColumnPreviousAddress(false);
                columnPreviousAddressCheckbox.setSelected(false);
            }
            Transaction.setSelectAllColumns(columnCheckAllColumnsCheckbox.isSelected());
            updateRequestText();
        });
    }

    private void selectColumnCheckbox() {
        columnLocationTypeCheckbox.setOnAction((event) -> {
            Transaction.setColumnLocationType(columnLocationTypeCheckbox.isSelected());
            updateRequestText();
        });

        columnPhoneCheckbox.setOnAction((event) -> {
            Transaction.setColumnPhone(columnPhoneCheckbox.isSelected());
            updateRequestText();
        });

        columnEINCheckbox.setOnAction((event) -> {
            Transaction.setColumnEIN(columnEINCheckbox.isSelected());
            updateRequestText();
        });

        columnMoveDateCheckbox.setOnAction((event) -> {
            Transaction.setColumnMoveDate(columnMoveDateCheckbox.isSelected());
            updateRequestText();
        });

        columnStockTickerCheckbox.setOnAction((event) -> {
            Transaction.setColumnStockTicker(columnStockTickerCheckbox.isSelected());
            updateRequestText();
        });

        columnWebAddressCheckbox.setOnAction((event) -> {
            Transaction.setColumnWebAddress(columnWebAddressCheckbox.isSelected());
            updateRequestText();
        });

        columnPreviousAddressCheckbox.setOnAction((event) -> {
            Transaction.setColumnPreviousAddress(columnPreviousAddressCheckbox.isSelected());
            updateRequestText();
        });
    }

    private void updateRequestText(){
        RequestTextArea.setText(Transaction.generateRequestString());
    }
}
