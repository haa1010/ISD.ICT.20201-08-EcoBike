package views.screen.payment;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import controller.ResultScreenController;
import entity.BaseEntity;
import entity.bike.Bike;
import entity.db.EcoBikeRental;
import entity.invoice.Invoice;
import entity.station.Station;
import entity.transaction.Card;
import entity.transaction.TransactionInfo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import subsystem.interbank.InterbankSubsystemController;
import utils.Configs;
import views.screen.BaseScreenHandler;


public class PaymentScreenHandler extends BaseScreenHandler {

    @FXML
    private Label pageTitle;

    @FXML
    private TextField cardCode;

    @FXML
    private TextField owner;

    @FXML
    private TextField dateExpired;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button submitBtn;

    @FXML
    private PasswordField cvvCode;

    @FXML
    private ImageView home;

    private Invoice invoice;

    private Card card;

    /**
     * This constructor use when pay for renting bike
     *
     * @author hangtt
     */
    public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice) throws IOException {
        super(stage, screenPath);
        this.invoice = invoice;
        this.card = null;

        home.setOnMouseClicked(event -> {
            try {
                backToHome();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        submitBtn.setOnMouseClicked(event -> {
            try {
                this.submitToPay();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }


    /**
     * This constructor use when pay for returning bike
     *
     * @author hangtt
     */
    public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice, Card card) throws IOException {
        super(stage, screenPath);
    	this.invoice = invoice;
        this.card = card;
        
        setCardInfo(card);	
        
        home.setOnMouseClicked(event -> {
            try {
                backToHomeAfterRent(this.invoice.getOrder());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        submitBtn.setOnMouseClicked(event -> {
            try {
                this.submitToPay();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }
    
    void setCardInfo(Card card) {
        this.cardCode.setText(card.getCardCode());
        this.owner.setText(card.getOwner());
        this.dateExpired.setText(card.getDateExpired());
    }

    void submitToPay() throws IOException, SQLException {
        this.card = new Card(this.cardCode.getText(), this.owner.getText(), this.cvvCode.getText(), this.dateExpired.getText());

        InterbankSubsystemController interbank = new InterbankSubsystemController();
        TransactionInfo transactionResult = null;
        
        // payment controller
        if (this.invoice.getContents().contains("Refund")) {
            transactionResult = interbank.refund(this.card, Math.abs(this.invoice.getAmount()), this.invoice.getContents());
        } else
            transactionResult = interbank.payOrder(this.card, this.invoice.getAmount(), this.invoice.getContents());

        if (!transactionResult.getErrorCode().equals("00")) {
            displayTransactionError(transactionResult.getErrorCode(), this.invoice.getOrder(), this.invoice.getAmount(), this.invoice.getContents());
        } else {
            if (this.invoice.getContents().contains("deposit")) {
                moveToSuccessfulDepositScreen(this.invoice, transactionResult, this.card);
            } else {
            	moveToSuccessfulTransactionScreen(this.invoice, transactionResult, this.card);
            }
            
        }
    }

    @FXML
    void backToPreviousScreen(MouseEvent event) {
        this.getPreviousScreen().show();
    }
    
    void moveToSuccessfulDepositScreen(Invoice invoice, TransactionInfo transactionResult, Card card) throws SQLException, IOException {
    	ResultScreenHandler resultScreenHandler = null;
        BaseEntity.updateDB(1, invoice.getOrder().getRentedBike());
        //this.invoice.getOrder().getRentedBike().setRenting(false);
        invoice.newInvoiceDB();
        transactionResult.newTransactionDB(invoice.getId(), card);
        resultScreenHandler = new ResultScreenHandler(stage, Configs.RESULT_SCREEN_PATH, new ResultScreenController(), transactionResult, invoice.getOrder());
        resultScreenHandler.show();
    }
    
    void moveToSuccessfulTransactionScreen(Invoice invoice, TransactionInfo transactionResult, Card card) throws SQLException, IOException {
    	ResultScreenHandler resultScreenHandler = null;
        BaseEntity.updateDB(0, invoice.getOrder().getRentedBike());
        invoice.newInvoiceDB();
        // update db bike table, station col
        int bikeID = invoice.getOrder().getRentedBike().getId();
        int stationID = invoice.getOrder().getRentedBike().getStation().getId();
        Statement stm = EcoBikeRental.getConnection().createStatement();
        stm.executeUpdate(" update " + "Bike" + " set" + " "
                + " stationID " + "= " + Integer.toString(stationID)
                + " where id = " + Integer.toString(bikeID) + " ;");
        
        transactionResult.newTransactionDB(invoice.getId(), card);
        resultScreenHandler = new ResultScreenHandler(stage, Configs.RESULT_SCREEN_PATH, new ResultScreenController(), transactionResult);
        resultScreenHandler.show();
    }

}