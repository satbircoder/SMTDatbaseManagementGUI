package smtdatabaseapp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class SMTAppController  {

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCreateDB;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonDisplay;

    @FXML
    private Button buttonSearch;

    @FXML
    private Button buttonUpdate;

    @FXML
    private TextField custEmail;

    @FXML
    private TextField custEmailUpdate;

    @FXML
    private TextField custID;

    @FXML
    private TextField custIdUpdate;

    @FXML
    private TextField custName;

    @FXML
    private TextField custNameUpdate;

    @FXML
    private TextField custPhnNo;

    @FXML
    private TextField custPhnUpdate;

    @FXML
    private TableColumn<Customer, String> emailTable;

    @FXML
    private TableColumn<Customer, Integer> idTable;

    @FXML
    private TableColumn<Customer, String> mobileTable;

    @FXML
    private TableColumn<Customer, String> nameTable;

    @FXML
    private TableView<Customer> tableCustomer;
    
     @FXML
    private TextField messageBox;
    
    
    @FXML
    void handleAddData(ActionEvent event) {
        if(!"".equals(custName.getText()) && !"".equals(custEmail.getText()) && !"".equals(custPhnNo.getText())){
            try{
           String insertRecord = String.format("INSERT INTO Customer(Customer_Name,Email_Address,Phone_Number) VALUES"
                        + "('%s','%s','%s')",custName.getText(), custEmail.getText(), custPhnNo.getText());
           int count = CustomerInfoUtil.executeUpdate(insertRecord);
           if(count == 0){
                    messageBox.setText("Failed To Add Recorc");
                }
                else{
                    messageBox.setText("Record Has been added Successfully");
                   display();                 
           }
        }
            catch(Exception ex){
                messageBox.setText("Something went wrong please try again"+ex.getMessage());
                }
        }
        else{
            messageBox.setText("All fields are mandatory to fill");
        }
        resetAddDataBoxes();
    }

    @FXML
    void handleCreateDBAction(ActionEvent event) {
        CreateDatabase.createCustomerDB();
        messageBox.setText("Database Has been Created");
    }

    @FXML
    void handleDeleteData(ActionEvent event) {
        if(!"".equals(custID.getText())){
            try{
           String deleteRecord = "DELETE FROM Customer WHERE Customer_ID='"+custID.getText()+"';";
            int count = CustomerInfoUtil.executeUpdate(deleteRecord);
            if(count == 0){
                messageBox.setText("Record is not found to delete");
            }
            else{
                messageBox.setText("Record has been deleted:");
                display();
            }
            }
            catch(Exception ex){
                messageBox.setText("Something Went Wrong Please Try Again");
            }
        }
        else{
            messageBox.setText("Please enter ID number to delete");
        }
        custID.setText("");
    }
    

    @FXML
    void handleDisplayAction(ActionEvent event) {
             display();
              resetUpdateBoxes();
              resetAddDataBoxes();
    }

    @FXML
    void handleSearch(ActionEvent event) {
        if(!"".equals(custID.getText())){
       Customer c1 = null;
       String query = "SELECT * FROM Customer WHERE Customer_ID=" +custID.getText()+";";
       try{
                ResultSet rs = CustomerInfoUtil.executeQuery(query);
                if(rs.next()){
                    c1 = new Customer();
                    c1.setCustomerId(rs.getInt("Customer_ID"));
                    c1.setCustomerName(rs.getString("Customer_Name"));
                    c1.setEmailId(rs.getString("Email_Address"));
                    c1.setCustPhnNo(rs.getString("Phone_Number"));
                    custIdUpdate.setText(Integer.toString(c1.getCustomerId()));
                    custNameUpdate.setText(c1.getCustomerName());
                    custEmailUpdate.setText(c1.getEmailId());
                    custPhnUpdate.setText(c1.getCustPhnNo());
                    messageBox.setText("Searched Id data is shown in the boxes above");
                }
            }
             catch(SQLException ex){
            messageBox.setText("SQL Exception occured in Search Method:" +ex.getMessage());
        }
        }
        else{
            messageBox.setText("Please enter ID number in the field to search");
        }
        custID.setText("");
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        if(!"".equals(custIdUpdate.getText()) && !"".equals(custNameUpdate.getText()) 
                && !"".equals(custEmailUpdate.getText()) && !"".equals(custPhnUpdate.getText())){
            try{
        String insertRecord =               
               """
                UPDATE Customer
                SET
                Customer_Name ='"""+custNameUpdate.getText()+""" 
                ',Email_Address ='"""+custEmailUpdate.getText()+"""
                 ',Phone_Number ='"""+custPhnUpdate.getText()+"""
                '"""
                +"""
                 WHERE Customer_ID = 
                 """+custIdUpdate.getText()+"""
                 ;
                 """;
        int count = CustomerInfoUtil.executeUpdate(insertRecord);
        if(count == 0)
        {
            messageBox.setText("Failed to Update Record ");
        }
        else
        {
            messageBox.setText("New Record has been Updated");
            display();
        } 
            }
            catch(Exception ex){
                messageBox.setText("Something Went Wrong Please Try Again");
            }
        }
        else{
            messageBox.setText("Please select the record or search by Id first to update");
        }
        resetUpdateBoxes();
    }
      @FXML
    void showRowData(MouseEvent event) {
            getSelection();
    }
    public  void display(){
        ObservableList<Customer> customers = FXCollections.observableArrayList(
            getAllRecord()
    );
            idTable.setCellValueFactory(new PropertyValueFactory<>("customerId"));
             nameTable.setCellValueFactory(new PropertyValueFactory<>("customerName"));
             emailTable.setCellValueFactory(new PropertyValueFactory<>("emailId"));
             mobileTable.setCellValueFactory(new PropertyValueFactory<>("custPhnNo"));
             tableCustomer.setItems(customers);
    }
    public void getSelection(){// getting the columns values and display in the appropriate text boxes
        Integer index;
        try{
        index = tableCustomer.getSelectionModel().getSelectedIndex();
        if(index <= -1){
            return;
        }
        custIdUpdate.setText(idTable.getCellData(index).toString());
        custNameUpdate.setText(nameTable.getCellData(index));
        custEmailUpdate.setText(emailTable.getCellData(index));
        custPhnUpdate.setText(mobileTable.getCellData(index));
        }
        catch(Exception ex){
            messageBox.setText("Something Went wrong Please try again"+ex.getMessage());
        }
        }
    public  ArrayList<Customer> getAllRecord(){
        String query = "SELECT * FROM Customer";
        ArrayList<Customer> customers = new ArrayList<>();
        try{
            ResultSet rs = CustomerInfoUtil.executeQuery(query);
            while(rs.next()){
                Customer c1 = new Customer();
                if(rs.getString("Customer_ID") != null && rs.getString("Email_Address") != null 
                        && rs.getString("Phone_Number") != null){
                c1.setCustomerId(rs.getInt("Customer_ID"));
                c1.setCustomerName(rs.getString("Customer_Name"));
                c1.setEmailId(rs.getString("Email_Address"));
                c1.setCustPhnNo(rs.getString("Phone_Number"));
                customers.add(c1);
            }
            }
        }
        catch(SQLException ex){
            messageBox.setText("SQL exception occured in get record method:" +ex.getMessage());
        }
        return customers;
    }
    private void resetAddDataBoxes(){
        custName.setText("");
        custEmail.setText("");
        custPhnNo.setText("");
    }
    private void resetUpdateBoxes(){
        custIdUpdate.setText("");
        custNameUpdate.setText("");
        custPhnUpdate.setText("");
        custEmailUpdate.setText("");
    }
}


