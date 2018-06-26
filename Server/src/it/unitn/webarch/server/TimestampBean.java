package it.unitn.webarch.server;

/**
 * Project: Client
 * Created by en on 26/10/17.
 */

import it.unitn.webarch.enterprisebean.common.Timestamp;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Stateless
@Remote(Timestamp.class)
public class TimestampBean implements Timestamp, Serializable{
	@Override
	public String getTimestamp(){
		final SimpleDateFormat df = new SimpleDateFormat("dd,MM,yyyy HH:mm:ss");
		final Date date = new Date();
		return df.format(date);
	}
}
