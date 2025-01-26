package org.unibl.etf.mdp.workwithorders.sources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.unibl.etf.mdp.source.Property;
import org.unibl.etf.mdp.workwithorders.model.OrderFF;
import org.unibl.etf.mdp.workwithorders.model.ProductAndAmount;

import com.google.gson.Gson;

public class OrderData {

	private Integer serialNumber = 0;
	private static final String FORDER;
	private static Property property;
	private static Logger logger;
	private static final String DATA_TIME_FORMAT;
	static {
		property = Property.getProperty();
		logger = property.getLogger();
		DATA_TIME_FORMAT = property.getDATA_TIME_FORMAT_FOR_FILE();
		FORDER = property.getFOLDER_ORDERS();
	}
	
	public void store(String s) {
		int ser;
		String date = new SimpleDateFormat(DATA_TIME_FORMAT).format(new Date());
		System.out.println();
		synchronized(serialNumber) {
			ser = ++serialNumber;
		}
		Gson gson = new Gson();
		OrderFF order = gson.fromJson(s, OrderFF.class);
		try {
			System.out.println(s);
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(FORDER + File.separator + date + " " + ser + ".txt"))), true);
			pw.println("Status: " + order.getStatus());
			pw.println("e-mail: " + order.getOrder().getEmail());
			pw.println("Products: ");
			for(ProductAndAmount pa : order.getOrder().getProducts()) {
				pw.println(String.format("\t[%s] %10s #%.2f", pa.getProduct().getBarcode(), pa.getProduct().getName(), pa.getAmount()));
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.severe(e.getMessage());
		}
	}
}
