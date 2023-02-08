package com.example.flower_app;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import static com.example.flower_app.DB.*;

public class formOrder implements Initializable {
    @FXML
    private TextField orderId;
    @FXML
    private TextField orderDate;
    @FXML
    private TextField orderTimeDate;
    @FXML
    private TextField orderClientId;
    @FXML
    private TextField orderServiceId;
    @FXML
    private Label orderLabel;
    @FXML
    private Canvas canvas;
    @FXML
    private Label savePdf;

    private String id = "";
    private String date = "";
    private String timeDate = "";
    private String clientId = "";
    private String serviceId = "";

    private Window primaryStage;

    private final String QUERY = "insert into orders(order_id, date_order, time_date, client_id, service_id) values(?, ?, ?, ?, ?)";
    private static final int CANVAS_SIZE = 200;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //Метод для формирования заказа
    public void formOrder() throws SQLException {
        id = orderId.getText();
        date = orderDate.getText();
        timeDate = orderTimeDate.getText();
        clientId = orderClientId.getText();
        serviceId = orderServiceId.getText();
        Connection connection = DriverManager.getConnection(connectionURL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, date);
        preparedStatement.setString(3, timeDate);
        preparedStatement.setString(4, clientId);
        preparedStatement.setString(5, serviceId);
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            orderLabel.setTextFill(Color.web("#a63f3f"));
            orderLabel.setText("Ошибка формирования");
            throw new RuntimeException(e);
        }
        orderLabel.setTextFill(Color.web("#3fa655"));
        orderLabel.setText("Заказ сформирован");

    }

    //Метод для формирования штрих-кода
    public void loadCode(ActionEvent actionEvent) {
        if (orderDate.getText().equals("") || orderTimeDate.getText().equals("")) {
            System.out.println("Введены не все данные");
        } else {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            double mm = (double) Toolkit.getDefaultToolkit().getScreenResolution() / 25.4D;
            System.out.println(mm);
            int[] palks = new int[]{5, 1, 4, 0, 9, 2, 0, 2, 0, 1, 2, 3, 4, 5, 6};
            gc.setFill(Color.BLACK);
            int x0 = 20;
            int y0 = 10;
            double heightPalks = 22.85D * mm;
            double weightOgrPalks = 0.5D * mm;
            double rasstoyanieMegdyPalk = 0.5D * mm;

            gc.fillRect((double) x0, (double) y0, weightOgrPalks, heightPalks + 1.65D * mm);
            int otcydaPalks = (int) ((double) x0 + weightOgrPalks + rasstoyanieMegdyPalk);
            int otcydaZifra = otcydaPalks;
            gc.fillRect((double) otcydaPalks, (double) y0, weightOgrPalks, heightPalks + 1.65D * mm);
            otcydaPalks = (int) ((double) otcydaPalks + weightOgrPalks + rasstoyanieMegdyPalk);
            boolean printSrednyaPalks = false;

            for (int numberPalks = 0; numberPalks < palks.length; ++numberPalks) {
                double shirinaPalks = (double) palks[numberPalks] * 0.15D * mm;
                if (numberPalks == palks.length / 2 && !printSrednyaPalks) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect((double) otcydaPalks, (double) y0, weightOgrPalks, heightPalks + 1.65D * mm);
                    otcydaPalks = (int) ((double) otcydaPalks + weightOgrPalks + rasstoyanieMegdyPalk);

                    gc.fillRect((double) otcydaPalks, (double) y0, weightOgrPalks, heightPalks + 1.65D * mm);
                    otcydaPalks = (int) ((double) otcydaPalks + weightOgrPalks + rasstoyanieMegdyPalk);
                    --numberPalks;
                    printSrednyaPalks = true;

                } else {
                    if (shirinaPalks == 0.0D) {
                        shirinaPalks = 1.35D * mm;
                        gc.setFill(Color.WHITE);
                    } else {
                        gc.setFill(Color.BLACK);
                    }

                    gc.fillRect((double) otcydaPalks, (double) y0, shirinaPalks, heightPalks);

                    otcydaPalks = (int) ((double) otcydaPalks + shirinaPalks + rasstoyanieMegdyPalk);
                }
            }

            gc.fillRect((double) otcydaPalks, (double) y0, weightOgrPalks, heightPalks + 1.65D * mm);
            otcydaPalks = (int) ((double) otcydaPalks + weightOgrPalks + rasstoyanieMegdyPalk);
            gc.fillRect((double) otcydaPalks, (double) y0, weightOgrPalks, heightPalks + 1.65D * mm);
            String timeStamp = new SimpleDateFormat("ddMMyyyyHHmm").format(Calendar.getInstance().getTime());
            String codes = orderDate.getText() + timeStamp + orderTimeDate.getText();
            for (int i = 0; i < 6; i++) {
                int a = (int) (Math.random() * 10);
                codes += a;
            }
            gc.fillText(codes, x0, y0 + heightPalks + 1.65D * mm + 10, 100);

        }
    }

    //Метод для скачивания штрих-кода в pdf формат
    public void pdf() throws URISyntaxException, IOException, DocumentException {

        FileChooser savefile = new FileChooser();
        savefile.setTitle("Save File");

        File file = new File("src/main/resources/shtrih-code.jpeg");

        if (file != null) {
            try {
                WritableImage writableImage = new WritableImage(CANVAS_SIZE, CANVAS_SIZE);
                canvas.snapshot(null, writableImage);

                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Error!");
            }
        }
        Path path1 = Paths.get(ClassLoader.getSystemResource("shtrih-code.jpeg").toURI());


        File file1 = savefile.showSaveDialog(primaryStage);
        Document document1 = new Document();
        PdfWriter.getInstance(document1, new FileOutputStream(String.valueOf(file1)));
        document1.open();

        com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(path1.toAbsolutePath().toString());

        document1.add(image);

        document1.close();
        savePdf.setVisible(true);
    }
}

